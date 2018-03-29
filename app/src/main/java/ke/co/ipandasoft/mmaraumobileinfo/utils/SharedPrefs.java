package ke.co.ipandasoft.mmaraumobileinfo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import ke.co.ipandasoft.mmaraumobileinfo.R;

/**
 * Created by Kevin Gitonga on 3/9/2018.
 */

public class SharedPrefs {

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences prefs;

    public static final int MAX_OPEN_COUNTER = 10;

    public static final String FCM_PREF_KEY = "GCM_PREF_KEY";
    public static final  boolean IS_FIRST_RUN=true;
    public static final String SERVER_FLAG_KEY = "SERVER_FLAG_KEY";
    public static final String userEmail="user_email";
    public static final String userId="user_id";
    public static final String userPassword="user_password";



    public static boolean isFirstRun() {
        return IS_FIRST_RUN;
    }

    public  void  setIsFirstRun(boolean isFirstRun){
        sharedPreferences.edit().putBoolean("isFirstRun",isFirstRun).apply();

    }

    public boolean getIsFirstRun() {
        return sharedPreferences.getBoolean("isFirstRun", IS_FIRST_RUN);
    }

    public  void  setUserEmail(String userEmail){
        sharedPreferences.edit().putString("user_email",userEmail).apply();

    }

    public  void  setUserId(String userId){
        sharedPreferences.edit().putString("user_id",userId).apply();

    }


    public  void  setUserPassword(String userPassword){
        sharedPreferences.edit().putString("user_password",userPassword).apply();

    }


    public SharedPrefs(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("MAIN_PREF", Context.MODE_PRIVATE);
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void ClearPrefs(Context context){
        sharedPreferences = context.getSharedPreferences("MAIN_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();


    }

    public void setGCMRegId(String gcmRegId) {
        sharedPreferences.edit().putString(FCM_PREF_KEY, gcmRegId).apply();
    }

    public String getGCMRegId() {
        return sharedPreferences.getString(FCM_PREF_KEY, null);
    }

    public boolean isGcmRegIdEmpty() {
        return TextUtils.isEmpty(getGCMRegId());
    }

    public String getUserEmail() {
        return sharedPreferences.getString(userEmail,null);
    }

    public String getUserId() {
        return sharedPreferences.getString(userId,null);
    }

    public String getUserPassword() {
        return sharedPreferences.getString(userPassword,null);
    }
}
