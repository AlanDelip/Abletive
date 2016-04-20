package abletive.presentation.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.HashMap;

import abletive.presentation.tasks.ImageTask;
import abletive.presentation.tasks.PostTask;
import abletive.presentation.widget.VideoEnabledWebChromeClient;
import abletive.presentation.widget.VideoEnabledWebView;
import abletive.vo.PostVO;
import alandelip.abletivedemo.R;
import jp.wasabeef.blurry.Blurry;

public class PostActivity extends AppCompatActivity {

    private static final String ARG_DATAMAP = "datamap";
    private HashMap<String, String> data;
    private VideoEnabledWebView webView;
    private PostVO postVO;
    private MaterialRefreshLayout materialRefreshLayout;
    private String id, title, author, views, thumbUrl, comments, url;
    private Toolbar toolbar;
    private TextView postInfoText;
    private VideoEnabledWebChromeClient webChromeClient;

    /**
     * @param context 启动活动的上下文
     */
    public static void newInstance(Context context, HashMap<String, String> data) {
        Intent intent = new Intent(context, PostActivity.class);
        intent.putExtra(ARG_DATAMAP, data);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.in_from_bottom, R.anim.out_to_top);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        //从intent中获取数据
        data = (HashMap<String, String>) getIntent().getSerializableExtra(ARG_DATAMAP);
        url = data.get("url");
        id = data.get("id");
        title = data.get("title");
        author = data.get("author");
        views = data.get("views");
        thumbUrl = data.get("thumbUrl");
        comments = data.get("comments");

        initToolbar();
        initWebview();
        initText();
        initRefreshLayout();

        fetchContent();
    }

    /**
     * 初始化刷新布局
     */
    private void initRefreshLayout() {
        materialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                fetchContent();
            }
        });
    }

    /**
     * 初始化工具栏
     */
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ImageView toolbarBackground = (ImageView) findViewById(R.id.toolbar_background);
        ImageView toolbarImage = (ImageView) findViewById(R.id.toolbar_navigation);
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_text);
        toolbarText.setText(title);
        ImageTask imageTask = new ImageTask(thumbUrl);
        imageTask.setImageTaskCallBack(new ImageTask.ImageTaskCallBack() {
            @Override
            public void setImage(Bitmap image) {
                toolbarBackground.setImageBitmap(image);
                Blurry.with(PostActivity.this)
                        .radius(20)
                        .sampling(8)
                        .async()
                        .capture(toolbarBackground)
                        .into(toolbarBackground);
            }

        });
        imageTask.execute();
    }

    /**
     * 初始化浏览器
     */
    private void initWebview() {
        webView = (VideoEnabledWebView) findViewById(R.id.webview);
        //支持js
//        WebSettings settings = webView.getSettings();
//        settings.setJavaScriptEnabled(true);
//        settings.setDefaultTextEncodingName("utf-8");
//        settings.setDisplayZoomControls(false);
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);

        ViewGroup webViewBackground = (ViewGroup) findViewById(R.id.webview_background);

        webChromeClient = new VideoEnabledWebChromeClient(webView, webViewBackground, null, webView) // See all available constructors...
        {
            // Subscribe to standard events, such as onProgressChanged()...
            @Override
            public void onProgressChanged(WebView view, int progress) {
                // Your code...
            }
        };

        webChromeClient.setOnToggledFullscreen(new VideoEnabledWebChromeClient.ToggledFullscreenCallback() {
            @Override
            public void toggledFullscreen(boolean fullscreen) {
                // Your code to handle the full-screen change, for example showing and hiding the title bar. Example:
                if (fullscreen) {
                    Log.d("Abletive", "toggledFullscreen: ");
                    WindowManager.LayoutParams attrs = getWindow().getAttributes();
                    attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    attrs.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    getWindow().setAttributes(attrs);
                    if (android.os.Build.VERSION.SDK_INT >= 14) {
                        //noinspection all
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                    }
                } else {
                    WindowManager.LayoutParams attrs = getWindow().getAttributes();
                    attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    attrs.flags &= ~WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    getWindow().setAttributes(attrs);
                    if (android.os.Build.VERSION.SDK_INT >= 14) {
                        //noinspection all
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    }
                }
            }
        });
        webView.setWebChromeClient(webChromeClient);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    /**
     * 初始化文字内容
     */
    private void initText() {
        final EditText mCommentText = (EditText) findViewById(R.id.comment_area);
        TextView mCommentButton = (TextView) findViewById(R.id.comment_confirm);
        mCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentContent = mCommentText.getText().toString();
                //TODO 发表评论并且刷新
            }
        });
        mCommentText.clearFocus();

        postInfoText = (TextView) findViewById(R.id.post_info);

    }

    /**
     * 获取文章内容（HTML），并显示
     */
    private void fetchContent() {
        PostTask postTask = new PostTask(id, "");
        postTask.setPostTaskCallBack(new PostTask.PostTaskCallBack() {
            @Override
            public void setPost(PostVO postVO) {
                PostActivity.this.postVO = postVO;
//                webView.loadData(postVO.getContentHtml(), "text/html;charset=utf-8", null);
                webView.loadUrl("http://www.youku.com");
                postInfoText.setText("点赞:" + postVO.getLikesNum() + "    收藏:" + postVO.getCollectsNum());
                materialRefreshLayout.finishRefresh();
            }
        });
        postTask.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // Notify the VideoEnabledWebChromeClient, and handle it ourselves if it doesn't handle it
        if (!webChromeClient.onBackPressed()) {
            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                // Standard back button implementation (for example this could close the app)
                super.onBackPressed();
                overridePendingTransition(R.anim.in_from_top, R.anim.out_to_bottom);
            }
        }
    }

}
