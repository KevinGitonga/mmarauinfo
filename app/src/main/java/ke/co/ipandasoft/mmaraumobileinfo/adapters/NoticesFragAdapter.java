package ke.co.ipandasoft.mmaraumobileinfo.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ke.co.ipandasoft.mmaraumobileinfo.NoticeDetails;
import ke.co.ipandasoft.mmaraumobileinfo.R;
import ke.co.ipandasoft.mmaraumobileinfo.config.Config;
import ke.co.ipandasoft.mmaraumobileinfo.models.Noticeboard;

/**
 * Created by Kevin Gitonga on 2/17/2018.
 */

public class NoticesFragAdapter extends RecyclerView.Adapter<NoticesFragAdapter.NoticesModel> {

    private List<Noticeboard> noticeboardList;
    private Activity activity;
    private int lastPosition=-1;

    public NoticesFragAdapter(Activity activity, List<Noticeboard> noticeboardList) {
        this.noticeboardList=noticeboardList;
        this.activity=activity;
    }

    @Override
    public NoticesModel onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewHolder= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notice_item, null, false);
        viewHolder.setLayoutParams(new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new NoticesModel(viewHolder);
    }

    @Override
    public void onBindViewHolder(NoticesModel holder, int position) {
        Noticeboard noticeboard=noticeboardList.get(position);
        holder.noticeType.setText(noticeboard.getNoticeType());
        String noticeDate=noticeboard.getOnDate();
        String finalDate=noticeDate.substring(0,10);
        holder.noticeonDate.setText(finalDate);
        holder.noticeTitle.setText(noticeboard.getNoticeDescription());
        if (TextUtils.isEmpty( noticeboard.getNoticeImage())){
            Picasso.with(activity).load(R.drawable.image_placeholder).into(holder.noticeImageView);
        }else {
            String imageRoute=noticeboard.getNoticeImage();
            String imageLogoPath= Config.NOTICE_IMAGE_ROOT_URL+ imageRoute;
            String url = Html.fromHtml(imageLogoPath).toString();
            Picasso.with(activity).load(url).placeholder(R.drawable.image_placeholder).into(holder.noticeImageView);
        }


        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(activity, R.anim.item_animation_fall_down);
            holder.cardView.startAnimation(animation);
            lastPosition = position;
        }

    }

    @Override
    public int getItemCount()
    {
        return noticeboardList.size();
    }

    class NoticesModel extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView noticeImageView;
        private TextView noticeTitle;
        private TextView noticeonDate;
        private TextView noticeType;
        private CardView cardView;

        AssetManager assetManager = activity.getApplicationContext().getAssets();
        Typeface montserrat_regular = Typeface.createFromAsset(assetManager, "fonts/Montserrat-Regular.ttf");

        public NoticesModel(View itemView) {
            super(itemView);
            this.cardView=itemView.findViewById(R.id.cardView);
            this.noticeImageView=itemView.findViewById(R.id.notice_image);
            this.noticeTitle=itemView.findViewById(R.id.notice_title);
            noticeTitle.setTypeface(montserrat_regular);
            this.noticeonDate=itemView.findViewById(R.id.notice_date);
            noticeonDate.setTypeface(montserrat_regular);
            this.noticeType=itemView.findViewById(R.id.notice_type);
            noticeType.setTypeface(montserrat_regular);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String noticeTitle=noticeboardList.get(getAdapterPosition()).getNoticeType();
            String noticeDescription=noticeboardList.get(getAdapterPosition()).getNoticeDescription();
            String noticeImage=noticeboardList.get(getAdapterPosition()).getNoticeImage();
            String noticeStart=noticeboardList.get(getAdapterPosition()).getOnDate();


            Intent intent = new Intent(activity, NoticeDetails.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Config.INTENT_CONTENT_TITLE,noticeTitle);
            intent.putExtra(Config.INTENT_CONTENT_DESCRIPTION,noticeDescription);
            intent.putExtra(Config.INTENT_CONTENT_IMAGE_URL,noticeImage);
            intent.putExtra(Config.INTENT_CONTENT_START_DATE,noticeStart);
            activity.startActivity(intent);

            ((Activity) activity).overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        }
    }
}
