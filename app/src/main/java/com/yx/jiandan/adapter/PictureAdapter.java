package com.yx.jiandan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Y on 2016/9/20.
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {


    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class PictureViewHolder extends RecyclerView.ViewHolder{



        public PictureViewHolder(View itemView) {
            super(itemView);
        }
    }

}
