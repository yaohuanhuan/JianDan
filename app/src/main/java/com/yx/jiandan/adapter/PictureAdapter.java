package com.yx.jiandan.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.yx.jiandan.R;
import com.yx.jiandan.callback.LoadFinishCallBack;
import com.yx.jiandan.model.Comments;
import com.yx.jiandan.model.Picture;
import com.yx.jiandan.ui.activity.ImageDetailActivity;
import com.yx.jiandan.ui.base.BaseActivity;
import com.yx.jiandan.ui.imageload.ImageLoadProxy;
import com.yx.jiandan.view.ShowMaxImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Y on 2016/9/20.
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {

    public ArrayList<String> list;
    public Activity activity;
    private int page;
    private String TAG = "pic";
    private LoadFinishCallBack mLoadFinisCallBack;
    private Boolean mIsSister;

    public PictureAdapter(Activity activity, LoadFinishCallBack loadFinishCallBack,Boolean isSister) {
        this.activity = activity;
        mLoadFinisCallBack = loadFinishCallBack;
        list = new ArrayList<>();
        mIsSister = isSister;

    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pic, parent, false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PictureViewHolder holder, final int position) {
        holder.progress.setProgress(0);
        holder.progress.setVisibility(View.VISIBLE);
        ImageLoadProxy.displayImageList(list.get(position), holder.img, R.mipmap.ic_loading_large, new
                SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        super.onLoadingComplete(imageUri, view, loadedImage);
                        holder.progress.setVisibility(View.GONE);
                    }
                }, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String s, View view, int i, int i1) {
                holder.progress.setProgress((int) (i * 100f / i1));
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ImageDetailActivity.class);
                intent.putExtra(BaseActivity.DATA_IMAGE_URL,list.get(position));
                if (list.get(position).endsWith(".gif")){
                    intent.putExtra(BaseActivity.DATA_IS_NEED_WEBVIEW, true);
                }
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void loadFirst() {
        page = 1;
        loadDataByNetworkType();
    }

    public void loadNextPage() {
        page++;
        loadDataByNetworkType();
    }

    private void loadDataByNetworkType() {
        loadData();
    }

    private void loadData() {
        OkHttpClient client = new OkHttpClient();
        String url = mIsSister ?
                "http://jandan.net/?oxwlxojflwblxbsapi=jandan.get_ooxx_comments&page="
                :
                "http://jandan.net/?oxwlxojflwblxbsapi=jandan.get_pic_comments&page=";
        Request request = new Request.Builder().url(url+page).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject obj = new JSONObject(s);
                    Picture picture = new Picture(obj);
                    JSONArray commentArray = picture.getComments();
                    for (int i = 0;i < commentArray.length();i++){
                        JSONObject obj2 = new JSONObject(commentArray.get(i).toString());
                        Comments comments = new Comments(obj2);
                        JSONArray picArray = comments.getPics();
                        for (int j = 0; j < picArray.length(); j++){
                            list.add(picArray.get(j).toString());
                        }
                    }
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            notifyDataSetChanged();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public static class PictureViewHolder extends RecyclerView.ViewHolder {

        public ImageView img_gif;
        public ProgressBar progress;
        public ShowMaxImageView img;

        public PictureViewHolder(View itemView) {
            super(itemView);
            img_gif = (ImageView) itemView.findViewById(R.id.img_gif);
            progress = (ProgressBar) itemView.findViewById(R.id.progress);
            img = (ShowMaxImageView) itemView.findViewById(R.id.img);
        }
    }

}
