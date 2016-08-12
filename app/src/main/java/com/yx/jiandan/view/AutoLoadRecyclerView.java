package com.yx.jiandan.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yx.jiandan.callback.LoadFinishCallBack;
import com.yx.jiandan.callback.LoadMoreListener;
import com.yx.jiandan.ui.imageload.ImageLoadProxy;

/**
 * Created by Y on 2016/8/9.
 */
public class AutoLoadRecyclerView extends RecyclerView implements LoadFinishCallBack {

    private String TAG = "AutoLoadRecyclerView";

    private LoadMoreListener loadMoreListener;
    private boolean isLoadingMore;

    public AutoLoadRecyclerView(Context context) {
        super(context);
    }

    public AutoLoadRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoLoadRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        isLoadingMore = false;
        addOnScrollListener(new AutoLoadScrollListener(null, true, true));
    }

    /**
     *
     * @param pauseOnScroll
     * 手指贴在屏幕上滑动时是否暂停图片加载
     * @param pauseOnFling
     * 手指贴着屏幕快速推动后离开是否继续加载图片
     */

    public void setOnPauseListenerParams(boolean pauseOnScroll, boolean pauseOnFling) {
        addOnScrollListener(new AutoLoadScrollListener(ImageLoadProxy.getImageLoader(), pauseOnScroll, pauseOnFling));
    }

    /**
     * 滑动自动加载更多的监听
     * @param loadMoreListener
     */

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    @Override
    public void loadFinish(Object obj) {
        isLoadingMore = false;
    }

    private class AutoLoadScrollListener extends OnScrollListener{

        private ImageLoader imageLoader;
        private final boolean pauseOnScroll;
        private final boolean pauseOnFling;

        public AutoLoadScrollListener(ImageLoader imageLoader, boolean pauseOnScroll, boolean pauseOnFling) {
            super();
            this.pauseOnScroll = pauseOnScroll;
            this.pauseOnFling = pauseOnFling;
            this.imageLoader = imageLoader;
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (imageLoader != null) {
                switch (newState) {
                    case SCROLL_STATE_IDLE:
                        imageLoader.resume();
                        break;
                    case SCROLL_STATE_DRAGGING:
                        if (pauseOnScroll) {
                            imageLoader.pause();
                        } else {
                            imageLoader.resume();
                        }
                        break;
                    case SCROLL_STATE_SETTLING:
                        if (pauseOnFling) {
                            imageLoader.pause();
                        } else {
                            imageLoader.resume();
                        }
                        break;
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            //由于GridLayoutManager是LinearLayoutManager子类，所以也适用
            if (getLayoutManager() instanceof LinearLayoutManager) {
                int lastVisibleItem = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = AutoLoadRecyclerView.this.getAdapter().getItemCount();

                //有回调接口，并且不是加载状态，并且剩下2个item，并且向下滑动，则自动加载
                if (loadMoreListener != null && !isLoadingMore && lastVisibleItem >= totalItemCount -
                        2 && dy > 0) {
                    loadMoreListener.loadMore();
                    isLoadingMore = true;
                }
            }
        }
    }

}
