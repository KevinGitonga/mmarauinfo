package ke.co.ipandasoft.mmaraumobileinfo.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.List;

import ke.co.ipandasoft.mmaraumobileinfo.R;
import ke.co.ipandasoft.mmaraumobileinfo.models.Inquiry;

/**
 * Created by Kevin Gitonga on 3/3/2018.
 */

public class InquiriesAdapter extends RecyclerView.Adapter<InquiriesAdapter.InquiriesModel> {
    Activity activity;
    List<Inquiry> inquiryList;

    public InquiriesAdapter(Activity activity, List<Inquiry> inquiryList) {
        this.inquiryList=inquiryList;
        this.activity=activity;

    }

    @Override
    public InquiriesModel onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inquiry_item, parent, false);
        return new InquiriesAdapter.InquiriesModel(view);
    }

    @Override
    public void onBindViewHolder(InquiriesModel holder, int position) {
        Inquiry inquiry=inquiryList.get(position);
        holder.inquiryTitle.setText(inquiry.getSubject());
        holder.inquiryDate.setText(inquiry.getOn_date());
        holder.inquiryDescription.setText(inquiry.getMessage());
        String replyText=inquiry.getReply();
        if (TextUtils.isEmpty(replyText)){
            holder.inquiryReply.setText("This Query has not yet been replied too");
        }else {
           String rawReply=replyText;
            String title = TextUtils.substring(rawReply, 0,0);
            holder.inquiryReply.setHtml(replyText);
        }

    }

    @Override
    public int getItemCount() {
        int x;
        if (inquiryList !=null && !inquiryList.isEmpty()){
            x=inquiryList.size();
        }else {
            x=0;
        }
        return x;
    }

    class InquiriesModel extends RecyclerView.ViewHolder {
        private TextView inquiryTitle;
        private TextView inquiryDate;
        private TextView inquiryDescription;
        private HtmlTextView inquiryReply;


        public InquiriesModel(View itemView) {
            super(itemView);
            inquiryTitle=itemView.findViewById(R.id.inquiry_title);
            inquiryDate=itemView.findViewById(R.id.inquiry_date);
            inquiryDescription=itemView.findViewById(R.id.inquiry_description);
            inquiryReply=itemView.findViewById(R.id.inquiry_reply);

        }
    }
}
