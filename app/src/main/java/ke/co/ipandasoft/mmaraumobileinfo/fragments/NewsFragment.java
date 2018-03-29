package ke.co.ipandasoft.mmaraumobileinfo.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import ke.co.ipandasoft.mmaraumobileinfo.R;
import ke.co.ipandasoft.mmaraumobileinfo.adapters.NewsFragAdapter;
import ke.co.ipandasoft.mmaraumobileinfo.config.Config;
import ke.co.ipandasoft.mmaraumobileinfo.config.Utils;
import ke.co.ipandasoft.mmaraumobileinfo.models.NewsCallback;
import ke.co.ipandasoft.mmaraumobileinfo.models.NewsStructure;
import ke.co.ipandasoft.mmaraumobileinfo.rest.ApiClient;
import ke.co.ipandasoft.mmaraumobileinfo.rest.RestInterface;
import okhttp3.OkHttpClient;
import retrofit2.Call;

/**
 * Created by Kevin Gitonga on 2/13/2018.
 */

public class NewsFragment extends Fragment implements  SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NewsFragAdapter newsFragAdapter;
    private ProgressBar progressBar;
    private View view;
    private ArrayList<NewsStructure> articleStructure = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    private Parcelable listState;

    public static NewsFragment getInstance() {
        NewsFragment fragment = new NewsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        swipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        renderView();
        if (Utils.IsNeton(getActivity())){
            fetchNews();
        }else {
            Utils.MakeToast(getActivity());
        }

        return view;

    }


    private void fetchNews(){
        swipeRefreshLayout.setRefreshing(true);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(60, TimeUnit.SECONDS);
        httpClient.connectTimeout(60, TimeUnit.SECONDS);


        RestInterface request = ApiClient.getClient(httpClient).create(RestInterface.class);
        Log.e("fetching dtaaaaaaa","fetchnginghvbn");

        Call<NewsCallback> call = request.getHeadlines("new-scientist",Config.API_KEY);
        call.enqueue(new retrofit2.Callback<NewsCallback>() {
            @Override
            public void onResponse(Call<NewsCallback> call, retrofit2.Response<NewsCallback> response) {
                if (response.isSuccessful() && response.body().getArticles() != null) {

                    if (!articleStructure.isEmpty()) {
                        articleStructure.clear();
                    }
                    Log.e("data from news servers",response.body().getStatus().toString());
                    articleStructure = response.body().getArticles();
                    newsFragAdapter=new NewsFragAdapter(getActivity(),articleStructure);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                    layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setAdapter(newsFragAdapter);
                    swipeRefreshLayout.setRefreshing(false);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<NewsCallback> call, Throwable throwable) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getContext(), "Error fetching Data", Toast.LENGTH_SHORT).show();

            }
        });

}


    private void renderView() {
       recyclerView=view.findViewById(R.id.news_fragment_recycler);
        progressBar=view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);


    }

    @Override
    public void onRefresh() {

        if (!Utils.IsNeton(getActivity())) {
            Utils.MakeToast(getActivity());
            swipeRefreshLayout.setRefreshing(false);
        } else {
                fetchNews();
            }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        layoutManager = new LinearLayoutManager(getActivity());
        listState=layoutManager.onSaveInstanceState();
        outState.putParcelable(Config.RECYCLER_STATE_KEY, listState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable(Config.RECYCLER_STATE_KEY);

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        layoutManager = new LinearLayoutManager(getActivity());
        if (listState != null) {
            layoutManager.onRestoreInstanceState(listState);
        }
    }
}
