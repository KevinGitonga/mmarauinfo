package ke.co.ipandasoft.mmaraumobileinfo;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ke.co.ipandasoft.mmaraumobileinfo.config.Config;

/**
 * Created by Kevin Gitonga on 2/19/2018.
 */

public class ArticleActivity extends AppCompatActivity{

    private String URL;
    private Typeface montserrat_regular;
    private Typeface montserrat_semiBold;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        toolbar = (Toolbar) findViewById(R.id.toolbar_article);
        setSupportActionBar(toolbar);
        floatingButton();
        assetManager();
        receiveFromDataAdapter(montserrat_regular, montserrat_semiBold);
        buttonLinktoFullArticle(montserrat_regular);
    }

    private void assetManager() {
        AssetManager assetManager = this.getApplicationContext().getAssets();
        montserrat_regular = Typeface.createFromAsset(assetManager, "fonts/Montserrat-Regular.ttf");
        montserrat_semiBold = Typeface.createFromAsset(assetManager, "fonts/Montserrat-SemiBold.ttf");
    }

    private void buttonLinktoFullArticle(Typeface montserrat_regular) {
        Button linkToFullArticle = findViewById(R.id.article_button);
        linkToFullArticle.setTypeface(montserrat_regular);
        linkToFullArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebViewActivity();
            }
        });
    }

    private void openWebViewActivity() {
        Intent browserIntent = new Intent(ArticleActivity.this, WebViewActivity.class);
        browserIntent.putExtra(Config.INTENT_URL, URL);
        startActivity(browserIntent);
        this.overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    private void receiveFromDataAdapter(Typeface montserrat_regular, Typeface montserrat_semiBold) {
        String headLine = getIntent().getStringExtra(Config.INTENT_HEADLINE);
        String description = getIntent().getStringExtra(Config.INTENT_DESCRIPTION);
        String date = getIntent().getStringExtra(Config.INTENT_DATE);
        String imgURL = getIntent().getStringExtra(Config.INTENT_IMG_URL);
        URL = getIntent().getStringExtra(Config.INTENT_ARTICLE_URL);

        TextView content_Headline = findViewById(R.id.content_Headline);
        content_Headline.setText(headLine);
        content_Headline.setTypeface(montserrat_semiBold);
        getSupportActionBar().setTitle(headLine);
        TextView content_Description = findViewById(R.id.content_Description);
        content_Description.setText(description);
        content_Description.setTypeface(montserrat_regular);

        ImageView collapsingImage = findViewById(R.id.collapsingImage);
        Picasso.with(this)
                .load(imgURL)
                .into(collapsingImage);
    }



    private void floatingButton() {
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this Info! Shared from Mmarau Mobile Info App" + Uri.parse(URL));
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
