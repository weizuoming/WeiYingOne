package com.example.dell.wy_one.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.dell.wy_one.R;

public class PlayActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        webView = findViewById(R.id.web_view);
        Intent intent = getIntent();
        String shareURL = intent.getStringExtra("shareURL");
        if (shareURL != null) {
            webView.loadUrl(shareURL);

            //webview一系列设置
            webView.setWebViewClient(new WebViewClient());//在当前应用打开,而不是去浏览器
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
        }

    }
}
