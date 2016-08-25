package com.yx.jiandan.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


import com.yx.jiandan.R;
import com.yx.jiandan.bean.FreshNews;
import com.yx.jiandan.okhttp.OkHttpCallback;
import com.yx.jiandan.okhttp.OkHttpProxy;
import com.yx.jiandan.okhttp.parser.FreshNewsDetailParser;
import com.yx.jiandan.ui.base.BaseFragment;
import com.yx.jiandan.utils.String2TimeUtil;

/**
 * Created by Y on 2016/8/22.
 */
public class FreshNewsDetailFragment extends BaseFragment{

    private String TAG = "FreshNewsDetailFragment";

    private WebView webView;

    private FreshNews freshNews;

    public FreshNewsDetailFragment() {
    }

    public static FreshNewsDetailFragment getInstance(FreshNews freshNews) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DATA_FRESH_NEWS, freshNews);
        FreshNewsDetailFragment fragment = new FreshNewsDetailFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fresh_news_detail,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        webView = (WebView) getView().findViewById(R.id.webView);
        freshNews = (FreshNews) getArguments().getSerializable(DATA_FRESH_NEWS);
        webView.getSettings().setJavaScriptEnabled(true);

        OkHttpProxy.get(FreshNews.getUrlFreshNewsDetail(freshNews.getmId()), this, new OkHttpCallback<String>(new FreshNewsDetailParser()) {

            @Override
            public void onSuccess(int code, String s) {
                webView.loadDataWithBaseURL("", getHtml(freshNews, s), "text/html", "utf-8", "");
            }

            @Override
            public void onFailure(int code, String msg) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (webView != null) {
            webView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (webView != null) {
            webView.onPause();

        }
    }

    private static String getHtml(FreshNews freshNews, String content) {
        final StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html dir=\"ltr\" lang=\"zh\">");
        sb.append("<head>");
        sb.append("<meta name=\"viewport\" content=\"width=100%; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;\" />");
        sb.append("<link rel=\"stylesheet\" href='file:///android_asset/style.css' type=\"text/css\" media=\"screen\" />");
        sb.append("</head>");
        sb.append("<body style=\"padding:0px 8px 8px 8px;\">");
        sb.append("<div id=\"pagewrapper\">");
        sb.append("<div id=\"mainwrapper\" class=\"clearfix\">");
        sb.append("<div id=\"maincontent\">");
        sb.append("<div class=\"post\">");
        sb.append("<div class=\"posthit\">");
        sb.append("<div class=\"postinfo\">");
        sb.append("<h2 class=\"thetitle\">");
        sb.append("<a>");
        sb.append(freshNews.getTitle());
        sb.append("</a>");
        sb.append("</h2>");
        sb.append(freshNews.getAuthor().getName() + " @ " + String2TimeUtil
                .dateString2GoodExperienceFormat(freshNews.getDate()));
        sb.append("</div>");
        sb.append("<div class=\"entry\">");
        sb.append(content);
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

}
