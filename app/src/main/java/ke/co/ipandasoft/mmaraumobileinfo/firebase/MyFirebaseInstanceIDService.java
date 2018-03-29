package ke.co.ipandasoft.mmaraumobileinfo.firebase;


import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import ke.co.ipandasoft.mmaraumobileinfo.config.Config;
import ke.co.ipandasoft.mmaraumobileinfo.models.FcmCallback;
import ke.co.ipandasoft.mmaraumobileinfo.rest.RestInterface;
import ke.co.ipandasoft.mmaraumobileinfo.utils.SharedPrefs;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Kevin Gitonga on 3/7/2018.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIdService";
    private static final String TOPIC_GLOBAL = "global";
    private RestInterface finalRestInterface;
    private  SharedPrefs sharedPrefs;

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        sharedPrefs=new SharedPrefs(getApplicationContext());
        Log.e(TAG, "Refreshed tokenxxx: " + refreshedToken);
        registerDevice(refreshedToken);

    }


    private void registerDevice(String fcmToken) {
        Log.e("fcm_id_from_firebase", "fcmRegIdxxx: " + fcmToken.toString());
        sharedPrefs.setGCMRegId(fcmToken);
        String userEmail=sharedPrefs.getUserEmail();
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(Config.REST_ROOT).build();
        finalRestInterface = adapter.create(RestInterface.class);
        finalRestInterface.RegisterFcm(userEmail,fcmToken, new Callback<FcmCallback>() {
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

