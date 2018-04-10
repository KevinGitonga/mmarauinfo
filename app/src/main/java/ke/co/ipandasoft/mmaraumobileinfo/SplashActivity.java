package ke.co.ipandasoft.mmaraumobileinfo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import ke.co.ipandasoft.mmaraumobileinfo.config.Config;
import ke.co.ipandasoft.mmaraumobileinfo.config.Utils;
import ke.co.ipandasoft.mmaraumobileinfo.models.FcmCallback;
import ke.co.ipandasoft.mmaraumobileinfo.rest.RestInterface;
import ke.co.ipandasoft.mmaraumobileinfo.utils.SharedPrefs;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SplashActivity extends AppCompatActivity {

    private SharedPrefs sharedPrefs;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            ObtainFcmReg();
        }
    };
    private RestInterface finalRestInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPrefs=new SharedPrefs(SplashActivity.this);
        if (IsFirstRun() && DataNotAvailable() && Utils.IsNeton(this)){
            WriteData();
            registerDeviceToServer();
            InitLogin();
        }else if(!IsFirstRun() && DataNotAvailable() && Utils.IsNeton(this)){
            InitLogin();
        }else if(!IsFirstRun() && !DataNotAvailable() && Utils.IsNeton(this)){
            ObtainFcmReg();
            InitMain();
        }else {
            Utils.MakeToast(this);
        }
    }

    private void ObtainFcmReg() {
        String token = FirebaseInstanceId.getInstance().getToken();
        if (TextUtils.isEmpty(token)) {
            handler.removeCallbacks(runnable);
            handler.postDelayed(runnable, 10 * 1000);
        } else {
            sendRegistrationToServer(token);
        }
    }

    private void registerDeviceToServer() {
        String os_version=Utils.getVersionName(SplashActivity.this);
        String device_name=Utils.getDeviceName();
        String deviceSerial= Utils.getDeviceSerial(getApplicationContext());
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(Config.REST_ROOT).build();
        RestInterface finalRestInterface = adapter.create(RestInterface.class);
        finalRestInterface.RegisterDevice(device_name,os_version,deviceSerial, new Callback<FcmCallback>() {
            @Override
            public void success(FcmCallback fcmCallback, Response response) {
                Log.e("success_fcm","successful_fcm");
                Log.e("fcm_data_reply",fcmCallback.getResponse().toString());
                Toast.makeText(SplashActivity.this, "Device registered to Servers", Toast.LENGTH_SHORT).show();
                InitLogin();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Registration failed",error.getMessage());
                InitLogin();

            }
        });
    }

    private boolean DataNotAvailable() {
        if (sharedPrefs.getUserEmail() == null){
            return true;
        }else {
            return false;
        }

    }

    private void InitMain() {
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }, 2500);
    }

    private boolean IsFirstRun() {
        if (sharedPrefs.getIsFirstRun()){
            return true;
        }else{
            return false;
        }

    }

    private void WriteData() {
        sharedPrefs.setIsFirstRun(false);
    }

    private void InitLogin() {

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                }, 1500);
    }

    private void sendRegistrationToServer(String token) {
        Log.e("fcm_id_from_firebase", "fcmRegIdxxx: " + token.toString());
        sharedPrefs.setGCMRegId(token);
        String userEmail=sharedPrefs.getUserEmail();
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(Config.REST_ROOT).build();
        finalRestInterface = adapter.create(RestInterface.class);
        finalRestInterface.RegisterFcm(userEmail,token, new Callback<FcmCallback>() {
            @Override
            public void success(FcmCallback fcmCallback, Response response) {
                Log.e("success_fcm","successful_fcm");
                Log.e("fcm_data_reply",fcmCallback.getResponse().toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("failed_fcm",error.getMessage());

            }
        });
    }
}
