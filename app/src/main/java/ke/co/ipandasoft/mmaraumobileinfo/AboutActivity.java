package ke.co.ipandasoft.mmaraumobileinfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import ke.co.ipandasoft.mmaraumobileinfo.config.Config;

public class AboutActivity extends AppCompatActivity {
    TextView webText;
    TextView rateUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.about_us_title);
        PrivacyPolicy();
        OtherApps();
    }

    private void OtherApps() {
        webText = (TextView) findViewById(R.id.more_apps);
        webText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(Config.developerWebsite));
                    startActivity(intent);
                }catch (Exception e){


                }
            }
        });
    }

    private void PrivacyPolicy() {
        webText = (TextView) findViewById(R.id.privacy_policy);
        webText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(Config.webRootUrl +"/privacy.html"));
                    startActivity(intent);
                }catch (Exception e){


                }
            }
        });
    }
    }

