package com.example.medicalsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BannerWebView2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_web_view2);
        WebView webView2 = (WebView)findViewById(R.id.web_view2);
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.setWebViewClient(new WebViewClient());
        webView2.loadUrl("https://www.toutiao.com/a6969465871869821473/?log_from=ac6cb9cddf02d_1622978075165");
    }
}