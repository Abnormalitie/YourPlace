package lavraken.yourplace;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    DatabaseReference mConditionRef = mRootReference.child("condition");

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

        mConditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String text = dataSnapshot.getValue(String.class);
                //bookDataView.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        bookSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mConditionRef.setValue("Foggy");
                //mConditionRef.push().setValue("pushtest");
                String date = bookDayField.getText()+"-"+bookMonthField.getText();
                String time = bookTimeField.getText().toString();
                String name = bookNameField.getText().toString();
                String procedure = bookProcedureField.getText().toString();

                mConditionRef.child(date).child(time).setValue(procedure,name);
            }
        });
    }
}
