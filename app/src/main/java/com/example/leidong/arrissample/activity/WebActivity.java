package com.example.leidong.arrissample.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.leidong.arrissample.R;
import com.example.leidong.arrissample.constants.Constants;

/**
 * Created by leidong on 2017/6/1
 */

public class WebActivity extends Activity {
    private static final String TAG = "WebActivity";

    private WebView webView;
    private String url;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        initWidgets();

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }


    /**
     * 获取组件
     */
    private void initWidgets() {
        url = getIntent().getStringExtra(Constants.URL);
        Log.d(TAG, url+"<<<>>>");
        webView = (WebView) findViewById(R.id.webView);
    }

}
