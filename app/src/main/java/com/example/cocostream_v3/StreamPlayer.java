package com.example.cocostream_v3;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class StreamPlayer extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_streamplayer);

        String videoYoutube = getIntent().getStringExtra("videoYoutube");
        String canalTwitch = getIntent().getStringExtra("canalTwitch");
        String url_iframe = "https://cocostream-twitch-server.netlify.app/funcional.html?channelTW=" + canalTwitch + "&channelYT=" + videoYoutube;


        webview = findViewById(R.id.webview);
        webview.clearCache(false);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
        String final_url_iframe = url_iframe;
        webview.loadUrl(final_url_iframe);

        webview.requestFocus();
        webview.setKeepScreenOn(true);

    }

}