package ke.co.ipandasoft.mmaraumobileinfo.config;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

import ke.co.ipandasoft.mmaraumobileinfo.R;

/**
 * Created by Kevin Gitonga on 3/6/2018.
 */

public class Utils {

    public static final boolean IsNeton(Activity context) {

        ConnectivityManager connMan = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connMan.getActiveNetworkInfo();
        if (netInfo !=null && netInfo.isConnectedOrConnecting() ) {
            return true;
        } else {
            return false;
        }
    }



    public static void MakeToast(Activity activity) {
        Toast.makeText(activity, "No network connectivity", Toast.LENGTH_SHORT).show();
    }

    public static final void ShareApp(Activity mainActivity) {

        String str = mainActivity.getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);


        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out this app for Maasai Mara University :https://play.google.com/store/apps/details?id="+str);
        sendIntent.setType("text/plain");
        mainActivity.startActivity(Intent.createChooser(sendIntent,"Share Mmarau Mobile Info with friends"));
    }
    public static final void sendEmail(Activity activity) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto: kevinjones4199@gmail.com"));
        activity.startActivity(Intent.createChooser(emailIntent, "Request Help"));
    }

    public static String getVersionName(Context ctx) {
        try {
            PackageManager manager = ctx.getPackageManager();
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            return ctx.getString(R.string.version) + " " + info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "Unknown";
        }
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        } else {
            return manufacturer + " " + model;
        }
    }


    public static String getDeviceSerial(Context context){
        String phoneID = Build.SERIAL;
        try {
            phoneID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {}

        return  phoneID;
    }
}
