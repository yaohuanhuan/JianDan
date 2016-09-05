package com.yx.jiandan.ui.fragment;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yx.jiandan.R;
import com.yx.jiandan.adapter.FreshNewsAdapter;
import com.yx.jiandan.callback.LoadFinishCallBack;
import com.yx.jiandan.callback.LoadMoreListener;
import com.yx.jiandan.view.AutoLoadRecyclerView;

import java.util.ArrayList;


public class FreshNewsFragment extends Fragment {

    private String TAG = "FresNews";

    private AutoLoadRecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FreshNewsAdapter freshNewsAdapter;
    private LoadFinishCallBack mLoadFinisCallBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fresh_news, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (AutoLoadRecyclerView) getView().findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setOnPauseListenerParams(false, true);
        freshNewsAdapter = new FreshNewsAdapter(getActivity(),recyclerView);
        recyclerView.setAdapter(freshNewsAdapter);
        freshNewsAdapter.loadFirst();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                freshNewsAdapter.loadFirst();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        mLoadFinisCallBack = recyclerView;

        recyclerView.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void loadMore() {
                freshNewsAdapter.loadNextPage();
            }
        });
    }

}
