package ke.co.ipandasoft.mmaraumobileinfo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import ke.co.ipandasoft.mmaraumobileinfo.adapters.InquiriesAdapter;
import ke.co.ipandasoft.mmaraumobileinfo.config.Config;
import ke.co.ipandasoft.mmaraumobileinfo.config.Utils;
import ke.co.ipandasoft.mmaraumobileinfo.models.InquiriesCallback;
import ke.co.ipandasoft.mmaraumobileinfo.models.Inquiry;
import ke.co.ipandasoft.mmaraumobileinfo.rest.RestInterface;
import ke.co.ipandasoft.mmaraumobileinfo.utils.SharedPrefs;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class InquiriesActivity extends AppCompatActivity implements  SwipeRefreshLayout.OnRefreshListener{
    Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RestInterface finalRestInterface;
    private List<Inquiry> inquiryList;
    private InquiriesAdapter inquiriesAdapter;
    private LinearLayoutManager linearLayoutManager;
    private SharedPrefs sharedPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiries);
        sharedPrefs=new SharedPrefs(InquiriesActivity.this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Inquiries");
        setUpFloater();
        renderView();
        initRefresher();
        fetchInquiries();
    }


    private void setUpFloater() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Write a new Inquiry", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent=new Intent(InquiriesActivity.this,CreateInquiry.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initRefresher() {
        swipeRefreshLayout = findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void fetchInquiries() {
        swipeRefreshLayout.setRefreshing(true);
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(Config.REST_ROOT).build();
        finalRestInterface = adapter.create(RestInterface.class);
        String studentId;
        String student_Id=sharedPrefs.getUserId();
        Log.e("data from shered prefs",student_Id);
        if (student_Id != null){
            studentId=student_Id;
        }else {
            return;
        }
        int studentIdNo=Integer.valueOf(studentId);
        finalRestInterface.GetInquiries(studentId,new Callback<InquiriesCallback>() {
            @Override
            public void success(InquiriesCallback inquiriesCallback, Response response) {
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
                InquiriesActivity.this.inquiryList=inquiriesCallback.getData();
                if (inquiriesCallback.getData().size() >= 1){
                    Toast.makeText(InquiriesActivity.this, "Data loaded from servers", Toast.LENGTH_SHORT).show();
                for (int i=0; i<inquiryList.size(); i++){
                    Inquiry inquiry=inquiryList.get(i);
                    Log.e("Inquiries data",inquiry.getMessage());
                }
                inquiriesAdapter=new InquiriesAdapter(InquiriesActivity.this,inquiryList);
                recyclerView.setAdapter(inquiriesAdapter);

                        }else {
                    Toast.makeText(InquiriesActivity.this,"You don't have inquiries yet, click on icon below to Inquire",Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("youve got error",error.getMessage());
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }

    private void renderView() {
        recyclerView=findViewById(R.id.inquiries_recycler);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        this.linearLayoutManager=new LinearLayoutManager(InquiriesActivity.this);
        inquiriesAdapter=new InquiriesAdapter(InquiriesActivity.this,inquiryList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(inquiriesAdapter);




    }

    @Override
    public void onRefresh() {
        if (Utils.IsNeton(InquiriesActivity.this )){
            Utils.MakeToast(InquiriesActivity.this);
            swipeRefreshLayout.setRefreshing(false);

        } else
         {
            fetchInquiries();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
