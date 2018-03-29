package ke.co.ipandasoft.mmaraumobileinfo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import ke.co.ipandasoft.mmaraumobileinfo.config.Config;
import ke.co.ipandasoft.mmaraumobileinfo.models.RegisterCallback;
import ke.co.ipandasoft.mmaraumobileinfo.rest.RestInterface;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @Bind(R.id.adm_no) EditText admText;
    @Bind(R.id.input_email) EditText emailText;
    @Bind(R.id.input_password) EditText passwordText;
    @Bind(R.id.input_password_confirm) EditText reEnterPasswordText;
    @Bind(R.id.btn_signup) Button signupButton;
    @Bind(R.id.link_login) TextView loginLink;
    private RestInterface finalRestInterfacerestInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gotoLogin();
            }
        });
    }


    public void signup() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String adm_no = admText.getText().toString();
        final String student_email = emailText.getText().toString();
        final String student_password = passwordText.getText().toString();
        String reEnterPassword = reEnterPasswordText.getText().toString();
        Log.e("signup_data",student_email+student_password+adm_no);

        // TODO: Implement your own signup logic here.

        //making object of RestAdapter
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(Config.REST_ROOT).build();

        //Creating Rest Services
        finalRestInterfacerestInterface = adapter.create(RestInterface.class);
        finalRestInterfacerestInterface.SignUp(adm_no, student_email, student_password, new Callback<RegisterCallback>() {
            @Override
            public void success(RegisterCallback registerCallback, Response response) {
                progressDialog.dismiss();
                gotoLogin();

            }

            @Override
            public void failure(RetrofitError error) {
                onSignupFailed();

            }
        });

    }



    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Registration failed", Toast.LENGTH_LONG).show();

        signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = admText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String reEnterPassword = reEnterPasswordText.getText().toString();

        if (name.isEmpty()) {
            admText.setError("at least 3 characters");
            valid = false;
        } else {
            admText.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }


        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            reEnterPasswordText.setError(null);
        }

        return valid;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent start = new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(start);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    private void gotoLogin() {
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }



}
