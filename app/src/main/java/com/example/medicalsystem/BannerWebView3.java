package com.example.medicalsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BannerWebView3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_web_view3);
        WebView webView3 = (WebView)findViewById(R.id.web_view3);
        webView3.getSettings().setJavaScriptEnabled(true);
        webView3.setWebViewClient(new WebViewClient());
        webView3.loadUrl("https://www.toutiao.com/a6969869488837771812/?log_from=537002b88b654_1622977092471");
    }
}