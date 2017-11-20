package lavraken.yourplace;

import android.app.Application;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Palle on 18/11/2017.
 */

public class FragmentLogIn extends Fragment {

    //For LogIn
    private EditText logInEmailField;
    private EditText logInPasswordField;

    private Button logInButton;

    private FirebaseAuth logInAuth;
    private View fragment_login;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logInAuth = FirebaseAuth.getInstance();

        logInEmailField = (EditText) view.findViewById(R.id.LogInEmail);
        logInPasswordField = (EditText) view.findViewById(R.id.LogInPassword);
        logInButton = (Button) view.findViewById(R.id.LogInButton);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startSignIn();
            }
        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    private void startSignIn() {
        String email = logInEmailField.getText().toString();
        String password = logInPasswordField.getText().toString();

        logInAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(!task.isSuccessful()){
                    Toast.makeText(getActivity(), "SignInProblem", Toast.LENGTH_LONG).show();

                    //getContext
                }
            }
        });
    }
}
