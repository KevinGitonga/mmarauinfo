package ke.co.ipandasoft.mmaraumobileinfo.firebase;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import ke.co.ipandasoft.mmaraumobileinfo.config.Config;
import ke.co.ipandasoft.mmaraumobileinfo.rest.RestInterface;

/**
 * Created by Kevin Gitonga on 3/8/2018.
 */

public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    private static final String[] TOPICS = {"global"};
    public SharedPreferences settings;
    private RestInterface finalRestInterface;


    public RegistrationIntentService()
    {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        settings = getSharedPreferences(Config.PREF_NAME, 0);
        try {
            String token = FirebaseInstanceId.getInstance().getToken();

            // [END get_token]
            Log.e(TAG, "GCM Registration Token: " + token);

            // TODO: Implement this method to send any registration to your app's servers.
            //sendRegistrationToServer(token);




        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
            // If an exception happens while fetching the new token or updating our registration data
            // on a third-party server, this ensures that we'll attempt the update at a later time.
        }
        // Notify UI that registration has completed, so the progress indicator can be hidden.

    }


    private void sendRegistrationToServer(String token) {

    }
}
