package ke.co.ipandasoft.mmaraumobileinfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;



public class AtribsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atribs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CreateCards();
    }

    private void CreateCards() {
        CardView cardViewLibrary1 = findViewById(R.id.cardView1);
        CardView cardViewLibrary2 = findViewById(R.id.cardView2);
        CardView cardViewLibrary3 = findViewById(R.id.cardView3);
        CardView cardViewLibrary4 = findViewById(R.id.cardView4);
        CardView cardViewLibrary5 = findViewById(R.id.cardView5);
        CardView cardViewLibrary6 = findViewById(R.id.cardView6);

        cardViewLibrary1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/square/retrofit"));
                startActivity(browserIntent);
            }
        });

        cardViewLibrary2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/square/picasso"));
                startActivity(browserIntent);
            }
        });

        cardViewLibrary3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/square/okhttp"));
                startActivity(browserIntent);
            }
        });

        cardViewLibrary4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/google/gson"));
                startActivity(browserIntent);
            }
        });
        cardViewLibrary5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/yarolegovich/LovelyDialog"));
                startActivity(browserIntent);

            }
        });

        cardViewLibrary6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/PrivacyApps/html-textview"));
                startActivity(browserIntent);

            }
        });
    }


}
