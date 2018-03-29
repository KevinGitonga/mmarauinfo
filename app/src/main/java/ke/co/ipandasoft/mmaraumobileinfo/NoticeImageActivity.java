package ke.co.ipandasoft.mmaraumobileinfo;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.UUID;

import ke.co.ipandasoft.mmaraumobileinfo.config.Config;

public class NoticeImageActivity extends AppCompatActivity {
    private String imageUrl;
    private Button saveImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpUi();
        getIntentData();
        setImage();
    }

    private void setUpUi() {
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else {
            View decorView = getWindow().getDecorView();
            // Hide Status Bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    private void setImage() {
        ImageView imageView=findViewById(R.id.info_image);
        saveImage=findViewById(R.id.save_image);
        String imageName= String.valueOf(System.currentTimeMillis());
        Picasso.with(this).load(imageUrl).into(imageView);

    }

    private void downloadImage(String imageUrl) {
        File direct = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/Download/"+ getString(R.string.app_name) + "/");

        String randomName = "storysaver+"+ UUID.randomUUID().toString().substring(0, 10);

        if (!direct.exists()) {
            direct.mkdirs();
        }
        DownloadManager mgr = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(imageUrl);
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);
        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle(randomName+".jpg")
                .setDescription("Downloading")
                .setDestinationInExternalPublicDir("/Download/Story Saver Insta",randomName+".jpg");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        Toast.makeText(this, "Download Started", Toast.LENGTH_SHORT).show();
        mgr.enqueue(request);
    }

    private void getIntentData() {

        imageUrl = getIntent().getStringExtra(Config.INTENT_CONTENT_IMAGE_URL);
    }

}
