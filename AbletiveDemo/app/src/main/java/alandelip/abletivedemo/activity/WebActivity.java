package alandelip.abletivedemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import alandelip.abletivedemo.R;

public class WebActivity extends AppCompatActivity {


    /**
     * @param context 启动活动的上下文
     * @param url     文章网址
     */
    public static void actionStart(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.in_from_bottom, R.anim.out_to_top);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);


        String url = getIntent().getStringExtra("url");

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_top, R.anim.out_to_bottom);
    }
}
