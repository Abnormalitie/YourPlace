package lavraken.yourplace;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Palle on 18/11/2017.
 */

public class FragmentBook extends Fragment {

    private EditText bookDateField;
    private EditText bookTimeField;
    private TextView bookDataView;

    private Button bookSubmitButton;

    DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mConditionRef = mRootReference.child("condition");

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bookDateField = (EditText) view.findViewById(R.id.bookDateField);
        bookTimeField = (EditText) view.findViewById(R.id.bookTimeField);
        bookDataView = (TextView) view.findViewById(R.id.bookDataView);

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
                String text = dataSnapshot.getValue(String.class);
                bookDataView.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        bookSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
