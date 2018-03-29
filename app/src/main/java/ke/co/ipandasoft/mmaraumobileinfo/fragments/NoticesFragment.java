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

import ke.co.ipandasoft.mmaraumobileinfo.R;
import ke.co.ipandasoft.mmaraumobileinfo.adapters.NoticesFragAdapter;
import ke.co.ipandasoft.mmaraumobileinfo.config.Config;
import ke.co.ipandasoft.mmaraumobileinfo.config.Utils;
import ke.co.ipandasoft.mmaraumobileinfo.models.Noticeboard;
import ke.co.ipandasoft.mmaraumobileinfo.models.NoticesCallback;
import ke.co.ipandasoft.mmaraumobileinfo.rest.RestInterface;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Kevin Gitonga on 2/13/2018.
 */

public class NoticesFragment extends Fragment implements  SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NoticesFragAdapter noticesFragAdapter;
    private ProgressBar progressBar;
    private Parcelable listState;
    private View view;
    private RestInterface finalRestInterface;
    ArrayList<Noticeboard> noticesArrayList= new ArrayList<Noticeboard>();

    public static NoticesFragment getInstance() {
        NoticesFragment fragment = new NoticesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notices, container, false);
        renderView();
        initRefresher();
        if (Utils.IsNeton(getActivity())){
            fetchNotices();
        }else {
            Utils.MakeToast(getActivity());
        }

        return view;

    }




    private void initRefresher() {
       swipeRefreshLayout=view.findViewById(R.id.refresh_layout);
       swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void fetchNotices() {
        swipeRefreshLayout.setRefreshing(true);
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(Config.REST_ROOT).build();

        //Creating Rest Services
        finalRestInterface = adapter.create(RestInterface.class);
        finalRestInterface.GetNotices(new Callback<NoticesCallback>() {
            @Override
            public void success(NoticesCallback noticesCallback, Response response) {
                progressBar.setVisibility(View.GONE);
                Log.e("notices response",noticesCallback.getStatus().toString());
                    if (!noticesArrayList.isEmpty()) {
                            noticesArrayList.clear();

                        }
                        noticesArrayList= (ArrayList<Noticeboard>) noticesCallback.getNoticeboard();

                        noticesFragAdapter=new NoticesFragAdapter(getActivity(),noticesArrayList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                        recyclerView.setAdapter(noticesFragAdapter);
                        swipeRefreshLayout.setRefreshing(false);


            }

            @Override
            public void failure(RetrofitError error) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getContext(), "Error Fetching Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void renderView() {
        recyclerView=view.findViewById(R.id.notices_fragment_recycler);
        progressBar=view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);


    }

    @Override
    public void onRefresh() {
        if (!Utils.IsNeton(getActivity())) {
            Utils.MakeToast(getActivity());
            swipeRefreshLayout.setRefreshing(false);

        } else {
            fetchNotices();
        }
    }


    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);

        try {
            listState = recyclerView.getLayoutManager().onSaveInstanceState();
            bundle.putParcelable(Config.RECYCLER_STATE_KEY, listState);
        }catch (NullPointerException nul){

        }


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
        if (listState != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

}
