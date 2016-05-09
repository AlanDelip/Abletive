package abletive.presentation.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.HashMap;

import abletive.businesslogic.blutil.UserData;
import abletive.presentation.tasks.CollectTask;
import abletive.presentation.tasks.CommentTask;
import abletive.presentation.tasks.ImageTask;
import abletive.presentation.tasks.LikeTask;
import abletive.presentation.tasks.PostTask;
import abletive.presentation.widget.RoundImageView;
import abletive.vo.PostVO;
import alandelip.abletivedemo.R;
import im.delight.android.webview.AdvancedWebView;
import jp.wasabeef.blurry.Blurry;

public class PostActivity extends AppCompatActivity {
    private static final String TAG = "Abletive";
    private static final String ARG_DATAMAP = "datamap";
    private AdvancedWebView mWebView;
    private MaterialRefreshLayout materialRefreshLayout;
    private String id, title, author, views, thumbUrl, comments, url;
    private Toolbar toolbar;
    private EditText mCommentText;
    private TextView postInfoText;
    private View bottomView;
    private UserData userData;

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
        HashMap<String, String> data = (HashMap<String, String>) getIntent().getSerializableExtra(ARG_DATAMAP);
        url = data.get("url");
        id = data.get("id");
        title = data.get("title");
        author = data.get("author");
        views = data.get("views");
        thumbUrl = data.get("thumbUrl");
        comments = data.get("comments");
        userData = UserData.getInstance();

        initToolbar();
        initWebview();
        initText();
        initRefreshLayout();
        initFloatingButton();

        fetchContent();
    }

    /**
     * 初始化FloatingButton
     */
    private void initFloatingButton() {
        final FloatingActionMenu fabMenu = (FloatingActionMenu) findViewById(R.id.fab_menu);
        FloatingActionButton fabCommentList = (FloatingActionButton) findViewById(R.id.comment_list);
        fabCommentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转评论列表
                CommentListActivity.newInstance(PostActivity.this, id);
                fabMenu.close(true);
            }
        });
        FloatingActionButton fabLike = (FloatingActionButton) findViewById(R.id.like);
        fabLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //文章点赞
                if (userData.isLogin()) {
                    new LikeTask(PostActivity.this, id, userData.getUserID()).execute();
                    fabMenu.close(true);
                } else {
                    Toast.makeText(PostActivity.this, "请先登录~", Toast.LENGTH_SHORT).show();
                }

            }
        });
        FloatingActionButton fabCollect = (FloatingActionButton) findViewById(R.id.collect);
        fabCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //收藏文章
                if (userData.isLogin()) {
                    new CollectTask(PostActivity.this, userData.getUserID(), id, "add").execute();
                    fabMenu.close(true);
                } else {
                    Toast.makeText(PostActivity.this, "请先登录~", Toast.LENGTH_SHORT).show();
                }
            }
        });
        FloatingActionButton fabShare = (FloatingActionButton) findViewById(R.id.share);
        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 分享
                Toast.makeText(PostActivity.this, "分享功能即将开放，敬请期待！", Toast.LENGTH_SHORT).show();
            }
        });
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
        ImageView toolbarImage = (ImageView) findViewById(R.id.toolbar_navigation);
        final TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title),
                toolbarAuthor = (TextView) findViewById(R.id.toolbar_author);

        toolbarTitle.setText(title);
        toolbarAuthor.setText(author);

        ImageTask imageTask = new ImageTask(thumbUrl);
        imageTask.setImageTaskCallBack(new ImageTask.ImageTaskCallBack() {
            @Override
            public void setImage(Bitmap image) {
                if (image != null) {
                    Palette palette = Palette.from(image).generate();
                    Palette.Swatch muteSwatch = palette.getDarkMutedSwatch();
                    if (muteSwatch != null) {
                        toolbar.setBackgroundColor(muteSwatch.getRgb());
                        toolbarTitle.setTextColor(muteSwatch.getTitleTextColor());
                        toolbarAuthor.setTextColor(muteSwatch.getBodyTextColor());
                    }
                }
            }
        });
        imageTask.execute();

        bottomView = findViewById(R.id.bottom);
    }

    /**
     * 初始化浏览器
     */
    private void initWebview() {
        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        final ImageView webViewBackGround = (ImageView) findViewById(R.id.webview_background);
        ImageTask imageTask = new ImageTask(thumbUrl);
        imageTask.setImageTaskCallBack(new ImageTask.ImageTaskCallBack() {
            @Override
            public void setImage(Bitmap image) {
                webViewBackGround.setImageBitmap(image);
                Blurry.with(PostActivity.this)
                        .radius(20)
                        .sampling(8)
                        .async()
                        .capture(webViewBackGround)
                        .into(webViewBackGround);
            }

        });
        imageTask.execute();

    }

    /**
     * 初始化文字内容
     */
    private void initText() {
        mCommentText = (EditText) findViewById(R.id.comment_area);
        postInfoText = (TextView) findViewById(R.id.post_info);
        TextView mCommentButton = (TextView) findViewById(R.id.comment_confirm);
        mCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentContent = mCommentText.getText().toString();
                //发表评论
                if (userData.isLogin()) {
                    CommentTask commentTask = new CommentTask(PostActivity.this,
                            userData.getUserID(), id, "0", commentContent,
                            userData.getUserVO().getEmail());
                    commentTask.execute();
                    //清空评论
                    mCommentText.setText("");
                } else {
                    Toast.makeText(PostActivity.this, "请先登录~", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 获取文章内容（HTML），并显示
     */
    private void fetchContent() {
        PostTask postTask = new PostTask(id, UserData.getInstance().getCookie());
        postTask.setPostTaskCallBack(new PostTask.PostTaskCallBack() {
            @Override
            public void setPost(final PostVO postVO) {
//                mWebView.loadData(postVO.getContentHtml(), "text/html;charset=utf-8", null);
                mWebView.loadUrl(url);
                String postInfo = "点赞:" + postVO.getLikesNum() + "     收藏:" + postVO.getCollectsNum();
                postInfoText.setText(postInfo);
                materialRefreshLayout.finishRefresh();

                //加载作者头像
                RoundImageView authorAvatar = (RoundImageView) findViewById(R.id.toolbar_navigation);
                MainActivity.IMAGE_CACHE.get(postVO.getAuthorAvatarUrl(), authorAvatar);
                authorAvatar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (userData.isLogin()) {
                            PersonalPageActivity.newInstance(PostActivity.this, postVO.getAuthorID());
                        } else {
                            Toast.makeText(PostActivity.this, "请先登录~", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        postTask.execute();
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
            bottomView.setVisibility(View.GONE);
            toolbar.setVisibility(View.GONE);
            Toast.makeText(PostActivity.this, getString(R.string.landscape), Toast.LENGTH_SHORT).show();
        } else {
            bottomView.setVisibility(View.VISIBLE);
            toolbar.setVisibility(View.VISIBLE);
            Toast.makeText(PostActivity.this, getString(R.string.portrait), Toast.LENGTH_SHORT).show();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    @Override
    protected void onPause() {
        mWebView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
    }
}
