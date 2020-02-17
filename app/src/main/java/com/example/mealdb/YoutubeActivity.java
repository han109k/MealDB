package com.example.mealdb;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class YoutubeActivity extends AppCompatActivity {

    private static final String TAG = "YoutubeActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        Log.d(TAG, "onCreate: started");

        if(getIntent().hasExtra("url")){
            String url = getIntent().getStringExtra("url");

            WebView webView = findViewById(R.id.webview);
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            webView.loadUrl(url);
        } else {
            Log.d(TAG, "onCreate: error");
        }
    }
}
