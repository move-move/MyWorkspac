package cn.asiatravel.com.webviewtest.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.asiatravel.com.webviewtest.BaseFragment;
import cn.asiatravel.com.webviewtest.R;

/**
 * Created by lslMac on 15/11/25.
 */
public class ServiceFragment extends BaseFragment {
    protected View view;
    protected WebView mwebview;
//    private static final String DEFAULT_URL="http://10.2.21.231:8080/user.html";
    private static final String DEFAULT_URL="http://";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx=getActivity();
        getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_base,container,false);
        mwebview= (WebView) view.findViewById(R.id.webview);
        WebSettings settings = mwebview.getSettings();
        settings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);//设置默认缩放级别
        settings.setDefaultTextEncodingName("utf-8");
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        settings.setSupportZoom(true);
        //webview支持html的js
        settings.setJavaScriptEnabled(true);
        //设置单列显示:LayoutAlgorithm布局的算法
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        mwebview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                String js = " document.getElementById('menu').style.display = 'none';";
                mwebview.loadUrl("javascript:"+js);

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });


        mwebview.loadUrl(DEFAULT_URL);
        return view;

    }
}
