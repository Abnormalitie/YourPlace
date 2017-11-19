package lavraken.yourplace;

import android.app.Application;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Palle on 18/11/2017.
 */

public class FragmentLogIn extends Fragment {

    //For LogIn
    public EditText logInEmailField;
    public EditText logInPasswordField;
    public Button logInButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setContentView(R.layout.fragment_log_in);

        logInEmailField = (EditText) findViewById(R.id.logInEmail);
        logInPasswordField = (EditText) findViewById(R.id.logInPassword);
        logInButton = (Button) findViewById(R.id.LogInButton);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }
}
