package com.yx.jiandan.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.yx.jiandan.R;
import com.yx.jiandan.bean.Author;
import com.yx.jiandan.bean.CustomFields;
import com.yx.jiandan.bean.FreshNews;
import com.yx.jiandan.bean.Tags;
import com.yx.jiandan.callback.LoadFinishCallBack;
import com.yx.jiandan.db.manager.AuthorManageer;
import com.yx.jiandan.db.manager.CustomFieldsManager;
import com.yx.jiandan.db.manager.FreshNewsManager;
import com.yx.jiandan.db.manager.TagsManager;
import com.yx.jiandan.gen.AuthorDao;
import com.yx.jiandan.gen.CustomFieldsDao;
import com.yx.jiandan.gen.DaoMaster;
import com.yx.jiandan.gen.DaoSession;
import com.yx.jiandan.gen.FreshNewsDao;
import com.yx.jiandan.gen.TagsDao;
import com.yx.jiandan.okhttp.OkHttpCallback;
import com.yx.jiandan.okhttp.OkHttpProxy;
import com.yx.jiandan.okhttp.parser.FreshNewsParser;
import com.yx.jiandan.ui.activity.FreshNewsDetailActivity;
import com.yx.jiandan.ui.imageload.ImageLoadProxy;


import java.util.ArrayList;


/**
 * Created by Y on 2016/8/15.
 */
public class FreshNewsAdapter extends RecyclerView.Adapter<FreshNewsAdapter.FreshNewsViewHolder> {

    private ArrayList<FreshNews> mFreshNews;
    private DisplayImageOptions options;
    private LoadFinishCallBack mLoadFinisCallBack;
    private int page;

    Activity mActivity;

    public FreshNewsAdapter(Activity activity,LoadFinishCallBack loadFinisCallBack) {
        this.mActivity = activity;
        mFreshNews = new ArrayList<>();
        this.mLoadFinisCallBack = loadFinisCallBack;
    }

    @Override
    public FreshNewsAdapter.FreshNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fresh_news_small, parent, false);
        FreshNewsAdapter.FreshNewsViewHolder holder = new FreshNewsAdapter.FreshNewsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FreshNewsViewHolder holder, final int position) {
        holder.tv_title.setText(mFreshNews.get(position).getTitle());
        holder.tv_name.setText(mFreshNews.get(position).getId() + "");
        holder.tv_date.setText(mFreshNews.get(position).getDate());
        options = ImageLoadProxy.getOptions4PictureList(R.mipmap.ic_loading_small);
        ImageLoadProxy.displayImage(mFreshNews.get(position).getCustomFields().getThumb_m(), holder.iv_pic, options);
        holder.ll_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toDetailActivity(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFreshNews.size();
    }

    private void toDetailActivity(int position) {
        Intent intent = new Intent(mActivity, FreshNewsDetailActivity.class);
        intent.putExtra(FreshNewsDetailActivity.DATA_FRESH_NEWS, mFreshNews);
        intent.putExtra(FreshNewsDetailActivity.DATA_POSITION, position);
        mActivity.startActivity(intent);
    }

    public void loadFirst() {
        page = 1;
        loadDate();
    }

    public void loadNextPage() {
        page++;
        loadDate();
    }
    public void loadDate() {
        OkHttpProxy.get(FreshNews.getUrlFreshNews(page), new OkHttpCallback<ArrayList<FreshNews>>(new FreshNewsParser()) {
            @Override
            public void onSuccess(int code, final ArrayList<FreshNews> freshNewses) {
                mLoadFinisCallBack.loadFinish(null);
                mFreshNews.addAll(freshNewses);
                notifyDataSetChanged();
                final FreshNewsManager freshNewsManager = new FreshNewsManager();
                final AuthorManageer authorManageer = new AuthorManageer();
                final CustomFieldsManager customFieldsManager = new CustomFieldsManager();
                final TagsManager tagsManager = new TagsManager();

                if (page == 1){
                    freshNewsManager.deleteAll();
                    customFieldsManager.deleteAll();
                    tagsManager.deleteAll();
                    authorManageer.deleteAll();
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (FreshNews freshNews : freshNewses){
                            FreshNews mFreshNews = freshNews;
                            mFreshNews.setPage(page);
                            freshNewsManager.insert(mFreshNews);
                            Author author = mFreshNews.getAuthor();
                            author.setAuthorId(mFreshNews.getPrimarykey());
                            authorManageer.insert(author);
                            CustomFields customFields = mFreshNews.getCustomFields();
                            customFields.setCustomFieldsId(mFreshNews.getPrimarykey());
                            customFieldsManager.insert(customFields);
                            Tags tags = mFreshNews.getTags();
                            tags.setTagsId(mFreshNews.getPrimarykey());
                            tagsManager.insert(tags);
                        }

                    }
                }).start();




            }

            @Override
            public void onFailure(int code, String msg) {
                mLoadFinisCallBack.loadFinish(null);
            }
        });
    }

    public static class FreshNewsViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_name;
        TextView tv_date;
        ImageView iv_pic;
        LinearLayout ll_content;

        public FreshNewsViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            iv_pic = (ImageView) itemView.findViewById(R.id.iv_pic);
            ll_content = (LinearLayout) itemView.findViewById(R.id.ll_content);
        }
    }

}
