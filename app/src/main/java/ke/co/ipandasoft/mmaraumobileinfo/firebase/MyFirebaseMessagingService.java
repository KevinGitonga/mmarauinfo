package ke.co.ipandasoft.mmaraumobileinfo.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import ke.co.ipandasoft.mmaraumobileinfo.MainActivity;
import ke.co.ipandasoft.mmaraumobileinfo.R;
import ke.co.ipandasoft.mmaraumobileinfo.utils.SharedPrefs;

/**
 * Created by Kevin Gitonga on 3/7/2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private SharedPrefs sharedPref;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        sharedPref = new SharedPrefs(this);
        Log.e("receiving_data",remoteMessage.getFrom());
        Log.e("notification_from_ser",remoteMessage.getNotification().getBody());
        String notification_data=remoteMessage.getNotification().getBody();
        Log.e("notification_from_ser",notification_data);
        ShowNotification(remoteMessage.getNotification().getBody());

    }

    private void ShowNotification(String messageBody) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_info_white_24dp)
                .setContentTitle("Mmarau Mobile Info")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody))
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notificationBuilder.setPriority(android.app.Notification.PRIORITY_HIGH);
        }
        notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody));

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        int unique_id = (int) System.currentTimeMillis();
        notificationManager.notify(unique_id, notificationBuilder.build());
    }




}
