package lavraken.yourplace;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.content.Context;

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

    private ArrayAdapter<CharSequence> catAdapter;
    private ArrayAdapter<CharSequence> procAdapter;
    private Spinner category;
    private Spinner procedures;
    private TextView bookPriceView;
    private TextView bookDuration;
    private boolean initialLock = true;

    private Button bookSubmitButton;


    DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mBookingRef = mRootReference.child("User");

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //boolean initialLock = true;

        bookDayField = (EditText) view.findViewById(R.id.bookDayField);
        bookMonthField = (EditText) view.findViewById(R.id.bookMonthField);
        bookTimeField = (EditText) view.findViewById(R.id.bookTimeField);
        bookNameField = (EditText) view.findViewById(R.id.bookNameField);
        bookPriceView = (TextView) view.findViewById(R.id.bookPrice);
        bookDuration = (TextView) view.findViewById(R.id.bookDuration);

        bookSubmitButton = (Button) view.findViewById(R.id.bookSubmitButton);
        category = (Spinner) view.findViewById(R.id.categorySpinner);
        procedures = (Spinner) view.findViewById(R.id.procedureSpinner);

        catAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.category_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        category.setAdapter(catAdapter);

        category.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int currentItem = -1;

                if(currentItem == i){
                    return; //do nothing
                }
                else
                {
                    switch (category.getSelectedItem().toString()) {
                        case "Massage":
                            procAdapter = ArrayAdapter.createFromResource(getContext(), R.array.massage_array, android.R.layout.simple_spinner_item);
                            procAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            procedures.setAdapter(procAdapter);
                            break;
                        case "Facial":
                            procAdapter = ArrayAdapter.createFromResource(getContext(), R.array.facial_array, android.R.layout.simple_spinner_item);
                            procAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            procedures.setAdapter(procAdapter);
                            break;
                        case "Wax":
                            procAdapter = ArrayAdapter.createFromResource(getContext(), R.array.wax_array, android.R.layout.simple_spinner_item);
                            procAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            procedures.setAdapter(procAdapter);
                        default:
                            procAdapter = ArrayAdapter.createFromResource(getContext(), R.array.massage_array, android.R.layout.simple_spinner_item);
                            procAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            procedures.setAdapter(procAdapter);
                            break;
                    }
                }
                currentItem = i;
            }

            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

                if (!initialLock){
                    //Log.v("SOOOOW data", "firebase: "+ dataSnapshot.getValue());
                    Toast.makeText(getActivity(), "booking made succesfully", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        bookSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initialLock = false;
                String date = bookDayField.getText()+"-"+bookMonthField.getText();
                String time = bookTimeField.getText().toString();
                String name = bookNameField.getText().toString();
                String procedure = procedures.getSelectedItem().toString();

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

                }
                bookPriceView.setText("    150");
                bookDuration.setText("  30min");
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

        //Log.d("HEREEEEEEEEEEEEEEEE", " "+dateDayMonth[0]);

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
