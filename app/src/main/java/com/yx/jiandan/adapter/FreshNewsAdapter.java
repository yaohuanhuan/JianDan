package com.yx.jiandan.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.yx.jiandan.R;
import com.yx.jiandan.bean.FreshNews;
import com.yx.jiandan.okhttp.OkHttpCallback;
import com.yx.jiandan.okhttp.OkHttpProxy;
import com.yx.jiandan.okhttp.parser.FreshNewsParser;
import com.yx.jiandan.ui.imageload.ImageLoadProxy;


import java.util.ArrayList;


/**
 * Created by Y on 2016/8/15.
 */
public class FreshNewsAdapter extends RecyclerView.Adapter<FreshNewsAdapter.FreshNewsViewHolder> {

    private ArrayList<FreshNews> mFreshNews;
    private DisplayImageOptions options;

    Activity activity;

    public FreshNewsAdapter(Activity activity) {
        this.activity = activity;
        mFreshNews = new ArrayList<>();
    }

    @Override
    public FreshNewsAdapter.FreshNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fresh_news_small, parent, false);
        FreshNewsAdapter.FreshNewsViewHolder holder = new FreshNewsAdapter.FreshNewsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FreshNewsViewHolder holder, int position) {
        holder.tv_title.setText(mFreshNews.get(position).getTitle());
        holder.tv_name.setText(mFreshNews.get(position).getId() + "");
        holder.tv_date.setText(mFreshNews.get(position).getDate());
        options = ImageLoadProxy.getOptions4PictureList(R.mipmap.ic_loading_small);
        ImageLoadProxy.displayImage(mFreshNews.get(position).getCustomFields().getThumb_m(), holder.iv_pic, options);
    }

    @Override
    public int getItemCount() {
        return mFreshNews.size();
    }

    public void loadDate() {
        OkHttpProxy.get(FreshNews.getUrlFreshNews(1), new OkHttpCallback<ArrayList<FreshNews>>(new FreshNewsParser()) {

            @Override
            public void onSuccess(int code, ArrayList<FreshNews> freshNewses) {
                mFreshNews.addAll(freshNewses);
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(int code, String msg) {

            }
        });
    }

    public static class FreshNewsViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_name;
        TextView tv_date;
        ImageView iv_pic;

        public FreshNewsViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            iv_pic = (ImageView) itemView.findViewById(R.id.iv_pic);
        }
    }

}
