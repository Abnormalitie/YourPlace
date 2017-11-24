package lavraken.yourplace;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by Palle on 18/11/2017.
 */

public class FragmentBook extends Fragment {

    private EditText bookDayField;
    private EditText bookMonthField;
    private EditText bookTimeField;
    private EditText bookNameField;
    private EditText bookProcedureField;

    private TextView bookPriceView;

    private Button bookSubmitButton;


    DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mBookingRef = mRootReference.child("User");

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bookDayField = (EditText) view.findViewById(R.id.bookDayField);
        bookMonthField = (EditText) view.findViewById(R.id.bookMonthField);
        bookTimeField = (EditText) view.findViewById(R.id.bookTimeField);
        bookNameField = (EditText) view.findViewById(R.id.bookNameField);
        bookProcedureField = (EditText) view.findViewById(R.id.bookProcedureField);
        bookPriceView = (TextView) view.findViewById(R.id.bookPriceView);

        bookSubmitButton = (Button) view.findViewById(R.id.bookSubmitButton);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        mBookingRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //dataSnapshot.getValue(String.class);
                Log.d("SOOOOW data", dataSnapshot.getValue().toString());
                Toast.makeText(getActivity(), "booking made succesfully", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        bookSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String date = bookDayField.getText()+"-"+bookMonthField.getText();
                String time = bookTimeField.getText().toString();
                String name = bookNameField.getText().toString();
                String procedure = bookProcedureField.getText().toString();

                Boolean submitLegit = true;

                submitLegit = checkDate(date);
                if(!submitLegit){
                    Toast.makeText(getActivity(), "day must be in range 1-31 and month 1-12", Toast.LENGTH_LONG).show();
                    return;
                }

                submitLegit = checkTime(time);
                if(!submitLegit){
                    Toast.makeText(getActivity(), "time must in format \"mm:hh\", reservation hours are between 8:00 and 18:59", Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(date) || TextUtils.isEmpty(time) || TextUtils.isEmpty(name) || TextUtils.isEmpty(procedure)){
                    Toast.makeText(getActivity(), "Please make sure all field are filled", Toast.LENGTH_LONG).show();

                } else {




                    //logInAuth.signInWithEmailAndPassword()


                }

                mBookingRef.child(date).child(time).setValue(procedure,name);

                //Log.d("TAG", "HEREEEEEEEEEEEEEEEE");
                //Log.d("TAG", mBookingRef.child("Legio").child("Password").removeValue());
                //mBookingRef.child("Legio").child("Password").removeValue();
                //mBookingRef.child("Legio").getKey();
            }

        });
    }

    public boolean checkDate(String date){

        int day;
        int month;
        String[] dateDayMonth;

        int[] month31 = {1,3,5,7,8,10,12};
        int[] month30 = {4,6,9,11};

        dateDayMonth = date.split("-");
        day = Integer.parseInt(dateDayMonth[0]);
        month = Integer.parseInt(dateDayMonth[1]);

        if((month < 0) || (month > 12 )){
            return false;
        }
        if((day < 0) || (day > 31 )){
            return false;
        }

        Log.d("HEREEEEEEEEEEEEEEEE", " "+dateDayMonth[0]);

        return true;
    }

    public boolean checkTime(String time){

        int minutes;
        int hours;
        String[]timeMinuteHour;

        timeMinuteHour = time.split(":");

        minutes = Integer.parseInt(timeMinuteHour[1]);
        hours = Integer.parseInt(timeMinuteHour[0]);

        if((minutes < 0) || (minutes > 59 )){
            return false;
        }
        if((hours < 8) || (hours > 18 )){
            return false;
        }

        return true;
    }
}
