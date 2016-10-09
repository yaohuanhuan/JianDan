package com.yx.jiandan.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yx.jiandan.R;
import com.yx.jiandan.adapter.PictureAdapter;
import com.yx.jiandan.callback.LoadMoreListener;
import com.yx.jiandan.ui.activity.MainActivity;
import com.yx.jiandan.view.AutoLoadRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SisterFragment extends Fragment {

    private String TAG = "SisterFragment" ;

    private AutoLoadRecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PictureAdapter pictureAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_picture, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (AutoLoadRecyclerView) getView().findViewById(R.id.recycler_view_picture);
        pictureAdapter = new PictureAdapter(getActivity(),recyclerView,true);
        swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pictureAdapter.loadFirst();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void loadMore() {
                pictureAdapter.loadNextPage();
            }
        });
        recyclerView.setOnPauseListenerParams(false, true);
        recyclerView.setAdapter(pictureAdapter);
        pictureAdapter.loadFirst();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.base_toolbar_menu2, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(getActivity(), "无聊图1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item2:
                Toast.makeText(getActivity(), "无聊图2", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }


}
