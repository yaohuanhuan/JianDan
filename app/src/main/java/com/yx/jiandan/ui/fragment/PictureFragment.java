package com.yx.jiandan.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yx.jiandan.R;
import com.yx.jiandan.adapter.PictureAdapter;
import com.yx.jiandan.eventbus.MessageEvent;
import com.yx.jiandan.model.Comments;
import com.yx.jiandan.model.Picture;
import com.yx.jiandan.ui.activity.MainActivity;
import com.yx.jiandan.view.AutoLoadRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class PictureFragment extends Fragment {

    private String TAG = "PictureFragment" ;

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
        pictureAdapter = new PictureAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
