package ke.co.ipandasoft.mmaraumobileinfo.adapters;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ke.co.ipandasoft.mmaraumobileinfo.ArticleActivity;
import ke.co.ipandasoft.mmaraumobileinfo.R;
import ke.co.ipandasoft.mmaraumobileinfo.config.Config;
import ke.co.ipandasoft.mmaraumobileinfo.models.NewsStructure;

/**
 * Created by Kevin Gitonga on 2/18/2018.
 */

public class NewsFragAdapter extends RecyclerView.Adapter<NewsFragAdapter.ViewHolder>{

    private ArrayList<NewsStructure> articles;
    private Activity mContext;
    private int lastPosition = -1;

    public NewsFragAdapter(Activity mContext, ArrayList<NewsStructure> articles) {
        this.mContext = mContext;
        this.articles = articles;
    }

    /*
    ** inflating the cardView.
    **/
    @Override
    public NewsFragAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsFragAdapter.ViewHolder holder, int position) {

        String title = articles.get(position).getTitle();
        if (title.endsWith("- Times of India")) {
            title = title.replace("- Times of India", "");
        } else if(title.endsWith(" - Firstpost")) {
            title = title.replace(" - Firstpost", "");
        }

        holder.tv_card_main_title.setText(title);

        Picasso.with(mContext).load(articles.get(position).getUrlToImage())
                .into(holder.img_card_main);

        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.item_animation_fall_down);
            holder.cardView.startAnimation(animation);
            lastPosition = position;
        }
    }

    /*
    ** Last parameter for binding the articles in OnBindViewHolder.
    **/
    @Override
    public int getItemCount() {
        return articles.size();
    }

    /*
    ** ViewHolder class which holds the different views in the recyclerView .
    **/
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_card_main_title;
        private ImageView img_card_main;
        private CardView cardView;

        AssetManager assetManager = mContext.getApplicationContext().getAssets();
        Typeface montserrat_regular = Typeface.createFromAsset(assetManager, "fonts/Montserrat-Regular.ttf");

        public ViewHolder(View view) {
            super(view);
            tv_card_main_title = view.findViewById(R.id.tv_card_main_title);
            tv_card_main_title.setTypeface(montserrat_regular);
            img_card_main = view.findViewById(R.id.img_card_main);
            cardView = view.findViewById(R.id.card_row);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String headLine = articles.get(getAdapterPosition()).getTitle();
            if (headLine.endsWith(" - Times of India")) {
                headLine = headLine.replace(" - Times of India", "");
            } else if(headLine.endsWith(" - Firstpost")) {
                headLine = headLine.replace(" - Firstpost", "");
            }
            String description = articles.get(getAdapterPosition()).getDescription();
            String date = articles.get(getAdapterPosition()).getPublishedAt();
            String imgURL = articles.get(getAdapterPosition()).getUrlToImage();
            String URL = articles.get(getAdapterPosition()).getUrl();

            Intent intent = new Intent(mContext, ArticleActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            intent.putExtra(Config.INTENT_HEADLINE, headLine);
            intent.putExtra(Config.INTENT_DESCRIPTION, description);
            intent.putExtra(Config.INTENT_DATE, date);
            intent.putExtra(Config.INTENT_IMG_URL, imgURL);
            intent.putExtra(Config.INTENT_ARTICLE_URL, URL);

            mContext.startActivity(intent);

            ((Activity) mContext).overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }
    }
}
