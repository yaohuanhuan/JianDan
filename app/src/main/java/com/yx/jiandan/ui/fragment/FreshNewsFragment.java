package com.yx.jiandan.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yx.jiandan.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class FreshNewsFragment extends Fragment {

    public static final String URL_FRESH_NEWS = "http://jandan.net/?oxwlxojflwblxbsapi=get_recent_posts&include=url," +
            "date,tags,author,title,comment_count,custom_fields&custom_fields=thumb_c,views&dev=1&page=1";

    private RecyclerView recyclerView;
    private List<String> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        list.add("11111");
        list.add("2222");
        list.add("3333");
        list.add("44444");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fresh_news, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new FreshNewsAdapter());

        return view;
    }

    public class FreshNewsAdapter extends RecyclerView.Adapter<FreshNewsViewHolder> {

        @Override
        public FreshNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            FreshNewsViewHolder holder = new FreshNewsViewHolder(LayoutInflater.from(
                    getContext()).inflate(R.layout.item_fresh_news_small, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(FreshNewsViewHolder holder, int position) {
            holder.tv.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public static class FreshNewsViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public FreshNewsViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
