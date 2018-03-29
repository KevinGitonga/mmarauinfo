package ke.co.ipandasoft.mmaraumobileinfo.adapters;

import android.app.Activity;
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

import ke.co.ipandasoft.mmaraumobileinfo.R;
import ke.co.ipandasoft.mmaraumobileinfo.SingleInfo;
import ke.co.ipandasoft.mmaraumobileinfo.config.Config;
import ke.co.ipandasoft.mmaraumobileinfo.models.Event;

/**
 * Created by Kevin Gitonga on 2/21/2018.
 */

public class EventsFragAdapter extends RecyclerView.Adapter<EventsFragAdapter.EventsViewHolder> {
    Activity activity;
    List<Event> eventList;
    private int lastPosition=-1;


    public EventsFragAdapter(Activity activity, List<Event> eventList) {
        this.activity=activity;
        this.eventList=eventList;
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new EventsFragAdapter.EventsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {
        Event event=eventList.get(position);
        String eventTitle=event.getEvent_description();
        holder.event_title.setText(event.getEvent_description());
        String eventDate=event.getEvent_start();
        String appendData="Start:";
        String finalStartDate=appendData+eventDate;
        holder.event_start_date.setText(finalStartDate);
        String eventData=event.getEvent_start();
        String appendEndData="End:";
        String finalEndDate=appendEndData+eventData;
        holder.event_start_date.setText(finalStartDate);
        holder.event_end_date.setText(finalEndDate);

        if (TextUtils.isEmpty( event.getEvent_image())){
            Picasso.with(activity).load(R.drawable.image_placeholder).into(holder.event_icon);
        }else {
            String imageRoute=event.getEvent_image();
            String imageLogoPath= Config.EVENT_IMAGE_ROOT_URL+ imageRoute;
            String url = Html.fromHtml(imageLogoPath).toString();
            Picasso.with(activity).load(url).placeholder(R.drawable.image_placeholder).into(holder.event_icon);
        }

        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(activity, R.anim.item_animation_fall_down);
            holder.cardView.startAnimation(animation);
            lastPosition = position;
        }

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class EventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView event_title;
        private ImageView event_icon;
        private TextView event_start_date;
        private TextView event_end_date;
        private CardView cardView;

        AssetManager assetManager = activity.getApplicationContext().getAssets();
        Typeface montserrat_regular = Typeface.createFromAsset(assetManager, "fonts/Montserrat-Regular.ttf");


        public EventsViewHolder(View itemView) {
            super(itemView);
            this.cardView=itemView.findViewById(R.id.cardView);
            this.event_title=itemView.findViewById(R.id.event_title);
            event_title.setTypeface(montserrat_regular);
            this.event_icon=itemView.findViewById(R.id.event_image);
            this.event_start_date=itemView.findViewById(R.id.event_start_date);
            event_start_date.setTypeface(montserrat_regular);
            this.event_end_date=itemView.findViewById(R.id.event_end_date);
            event_end_date.setTypeface(montserrat_regular);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String eventTitle=eventList.get(getAdapterPosition()).getEvent_title();
            String eventDescription=eventList.get(getAdapterPosition()).getEvent_description();
            String eventImage=eventList.get(getAdapterPosition()).getEvent_image();
            String eventStart=eventList.get(getAdapterPosition()).getEvent_start();
            String eventEnd=eventList.get(getAdapterPosition()).getEvent_end();

            Intent intent = new Intent(activity, SingleInfo.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Config.INTENT_CONTENT_TITLE,eventTitle);
            intent.putExtra(Config.INTENT_CONTENT_DESCRIPTION,eventDescription);
            intent.putExtra(Config.INTENT_CONTENT_IMAGE_URL,eventImage);
            intent.putExtra(Config.INTENT_CONTENT_START_DATE,eventStart);
            intent.putExtra(Config.INTENT_CONTENT_END_DATE,eventEnd);
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        }
    }
}
