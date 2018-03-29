package ke.co.ipandasoft.mmaraumobileinfo;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ke.co.ipandasoft.mmaraumobileinfo.config.Config;

public class NoticeDetails extends AppCompatActivity {

    private String textInfo;
    private Typeface montserrat_regular;
    private Typeface montserrat_semiBold;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_details);
        toolbar = findViewById(R.id.toolbar_article);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        floatingButton();
        assetManager();
        receiveFromDataAdapter(montserrat_regular, montserrat_semiBold);

    }



    private void assetManager() {
        AssetManager assetManager = this.getApplicationContext().getAssets();
        montserrat_regular = Typeface.createFromAsset(assetManager, "fonts/Montserrat-Regular.ttf");
        montserrat_semiBold = Typeface.createFromAsset(assetManager, "fonts/Montserrat-SemiBold.ttf");
    }

    private void receiveFromDataAdapter(Typeface montserrat_regular, Typeface montserrat_semiBold) {
        String noticeTitle=getIntent().getStringExtra(Config.INTENT_CONTENT_TITLE);
        String noticeDescription=getIntent().getStringExtra(Config.INTENT_CONTENT_DESCRIPTION);
        String noticeImage=getIntent().getStringExtra(Config.INTENT_CONTENT_IMAGE_URL);
        String noticeStart=getIntent().getStringExtra(Config.INTENT_CONTENT_START_DATE);
        Log.e("data_from_intent",noticeTitle+"   " + noticeDescription);
        textInfo=noticeTitle+"\n"+noticeDescription;



        TextView content_title;
        content_title = (TextView) findViewById(R.id.item_title);
        content_title.setText(noticeTitle);
        content_title.setTypeface(montserrat_semiBold);

        getSupportActionBar().setTitle(noticeTitle);



        TextView content_Description = findViewById(R.id.item_description);
        content_Description.setText(noticeDescription);
        content_Description.setTypeface(montserrat_regular);

        TextView content_start_date = findViewById(R.id.item_create_date);
        String appendStartData="Created On:";
        content_start_date.setText(appendStartData+noticeStart);
        content_start_date.setTypeface(montserrat_regular);


        ImageView collapsingImage = findViewById(R.id.collapsingImage);
        String imageLogoPath= Config.NOTICE_IMAGE_ROOT_URL+ noticeImage;
        final String url = Html.fromHtml(imageLogoPath).toString();
        Picasso.with(this).load(url).placeholder(R.drawable.image_placeholder).into(collapsingImage);

    }



    private void floatingButton() {
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this Info! Shared from Mmarau Mobile Info App"+textInfo );
                startActivity(Intent.createChooser(shareIntent, "Share with"));
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            /*
            * Override the Up/Home Button
            * */
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

}
