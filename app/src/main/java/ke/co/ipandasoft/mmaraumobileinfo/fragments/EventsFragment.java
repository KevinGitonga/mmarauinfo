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

import java.util.ArrayList;

import ke.co.ipandasoft.mmaraumobileinfo.R;
import ke.co.ipandasoft.mmaraumobileinfo.adapters.EventsFragAdapter;
import ke.co.ipandasoft.mmaraumobileinfo.config.Config;
import ke.co.ipandasoft.mmaraumobileinfo.config.Utils;
import ke.co.ipandasoft.mmaraumobileinfo.models.Event;
import ke.co.ipandasoft.mmaraumobileinfo.models.EventsCallback;
import ke.co.ipandasoft.mmaraumobileinfo.rest.RestInterface;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Kevin Gitonga on 2/13/2018.
 */

public class EventsFragment extends Fragment implements  SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private EventsFragAdapter eventsFragAdapter;
    private ProgressBar progressBar;

    private View view;
    private RestInterface finalRestInterface;
    ArrayList<Event> eventsArrayList=new ArrayList<Event>();
    private Parcelable listState;

    public static EventsFragment getInstance() {
        EventsFragment fragment = new EventsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.events_fragment, container, false);
        renderView();
        initRefresher();
        if (Utils.IsNeton(getActivity())){
            fetchEvents();
        }else {
            Utils.MakeToast(getActivity());
        }

        return view;

    }

    private void initRefresher() {
        swipeRefreshLayout=view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void fetchEvents() {
        swipeRefreshLayout.setRefreshing(true);
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(Config.REST_ROOT).build();
        finalRestInterface = adapter.create(RestInterface.class);
        finalRestInterface.GetEvents(new Callback<EventsCallback>() {
            @Override
            public void success(EventsCallback eventsCallback, Response response) {
                progressBar.setVisibility(View.GONE);
                Log.e("events response",eventsCallback.getStatus().toString());
                 if (!eventsArrayList.isEmpty()) {
                     eventsArrayList.clear();
                 }
                 eventsArrayList = (ArrayList<Event>) eventsCallback.getEvents();
                 recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                 eventsFragAdapter = new EventsFragAdapter(getActivity(), eventsArrayList);
                 recyclerView.setAdapter(eventsFragAdapter);
                 swipeRefreshLayout.setRefreshing(false);


            }

            @Override
            public void failure(RetrofitError error) {
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }

    private void renderView() {
        recyclerView=view.findViewById(R.id.events_fragment_recycler);
        progressBar=view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);


    }

    @Override
    public void onRefresh() {
        if (!Utils.IsNeton(getActivity())) {
            Utils.MakeToast(getActivity());
            swipeRefreshLayout.setRefreshing(false);

        }else
         {
            fetchEvents();
        }

    }


    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        try {
            listState = recyclerView.getLayoutManager().onSaveInstanceState();
            bundle.putParcelable(Config.RECYCLER_STATE_KEY, listState);
        }catch (NullPointerException n){

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
