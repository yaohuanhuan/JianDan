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
import com.yx.jiandan.bean.Posts;
import com.yx.jiandan.ui.imageload.ImageLoadProxy;

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
 * Created by Y on 2016/8/15.
 */
public class FreshNewsAdapter extends RecyclerView.Adapter<FreshNewsAdapter.FreshNewsViewHolder> {

    public static final String URL_FRESH_NEWS = "http://jandan.net/?oxwlxojflwblxbsapi=get_recent_posts&include=url," +
            "date,tags,author,title,comment_count,custom_fields&custom_fields=thumb_c,views&dev=1&page=1";

    private ArrayList<Posts> mPost;
    private DisplayImageOptions options;

    Activity activity;

    public FreshNewsAdapter(Activity activity) {
        this.activity = activity;
        mPost = new ArrayList<>();
    }

    @Override
    public FreshNewsAdapter.FreshNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fresh_news_small, parent, false);
        FreshNewsAdapter.FreshNewsViewHolder holder = new FreshNewsAdapter.FreshNewsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FreshNewsViewHolder holder, int position) {
        holder.tv_title.setText(mPost.get(position).getTitle());
        holder.tv_name.setText(mPost.get(position).getId() + "");
        holder.tv_date.setText(mPost.get(position).getDate());
        options = ImageLoadProxy.getOptions4PictureList(R.mipmap.ic_loading_small);
        ImageLoadProxy.displayImage(mPost.get(position).getThumb_c(), holder.iv_pic, options);
    }

    @Override
    public int getItemCount() {
        return mPost.size();
    }

    public void loadDate() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(URL_FRESH_NEWS).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int code = response.code();
                String s = response.body().string();
                try {
                    Log.e("test", "结果码 == "+code);
                    JSONObject object = new JSONObject(s);
                    FreshNews freshNews = new FreshNews(object);
                    JSONArray array = freshNews.getPosts();
                    for (int i = 0; i < array.length(); i++) {
                        Posts post = new Posts(array.getJSONObject(i));
                        mPost.add(post);
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
