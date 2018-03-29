package ke.co.ipandasoft.mmaraumobileinfo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ke.co.ipandasoft.mmaraumobileinfo.config.Config;
import ke.co.ipandasoft.mmaraumobileinfo.config.Utils;
import ke.co.ipandasoft.mmaraumobileinfo.models.LoginCallback;
import ke.co.ipandasoft.mmaraumobileinfo.rest.RestInterface;
import ke.co.ipandasoft.mmaraumobileinfo.utils.SharedPrefs;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CreateInquiry extends AppCompatActivity {

    private EditText mainInquiry;
    private EditText inquirySubject;
    private TextInputLayout inquirySubjectLayout;
    private TextInputLayout mainInquiryLayout;
    private Button sendButton;
    private ProgressDialog progressDialog;
    private RestInterface finalRestInterface;
    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_inquiry);
        initView();
        initListeners();
    }

    private void initListeners() {
        mainInquiry.addTextChangedListener(new SupportTextWatcher(this.mainInquiry));
        inquirySubject.addTextChangedListener(new SupportTextWatcher(this.inquirySubject));
    }

    private void initView() {
        mainInquiry=findViewById(R.id.main_inquiry);
        inquirySubject=findViewById(R.id.inquiry_subject);
        inquirySubjectLayout=findViewById(R.id.inquiry_subject_layout);
        mainInquiryLayout=findViewById(R.id.main_inquiry_layout);
        sendButton=findViewById(R.id.send_inquiry_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog=new ProgressDialog(CreateInquiry.this);
                progressDialog.setMessage("sending Inquiry");
                progressDialog.setCancelable(false);
                progressDialog.show();
                if (Utils.IsNeton(CreateInquiry.this)){
                    sendInquiry();
                }else {
                    Utils.MakeToast(CreateInquiry.this);
                    progressDialog.dismiss();
                }

            }
        });
    }



    private void sendInquiry() {
        String studentId;
        sharedPrefs=new SharedPrefs(CreateInquiry.this);
        studentId=sharedPrefs.getUserId();
        if (studentId == null){
            studentId= String.valueOf(22);
        }
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(Config.REST_ROOT).build();
        finalRestInterface = adapter.create(RestInterface.class);
        finalRestInterface.SendInquiry(inquirySubject.getText().toString(), mainInquiry.getText().toString(), studentId, new Callback<LoginCallback>() {
            @Override
            public void success(LoginCallback loginCallback, Response response) {
                Toast.makeText(CreateInquiry.this,"Inquiry sent, swipe down to refresh!!!!",Toast.LENGTH_SHORT);
                progressDialog.dismiss();
                CreateInquiry.this.finish();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(CreateInquiry.this, "Error Occured!!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private class SupportTextWatcher implements TextWatcher {
        private View view;
        private SupportTextWatcher(View view) {
            this.view = view;
        }
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.inquiry_subject:
                    validatName();
                    break;
                case R.id.main_inquiry :
                    validatMessage();
                    break;
            }
        }
    }

    private boolean validatName() {
        if (inquirySubject.getText().toString().trim().isEmpty() || inquirySubject.getText().length()  < 3 ) {
            inquirySubject.setError(getString(R.string.error_short_value));
            return false;
        } else {
            inquirySubjectLayout.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validatMessage() {
        if (mainInquiry.getText().toString().trim().isEmpty() || mainInquiry.getText().length()  < 3 ) {
            mainInquiryLayout.setError(getString(R.string.error_short_value));
            return false;
        } else {
            mainInquiryLayout.setErrorEnabled(false);
        }

        return true;
    }
}
