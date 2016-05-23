package abletive.presentation.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXWebpageObject;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;
import java.util.HashMap;

import abletive.businesslogic.blutil.StaticID;
import abletive.businesslogic.blutil.UserData;
import abletive.businesslogic.blutil.UserTransformer;
import abletive.presentation.tasks.CollectTask;
import abletive.presentation.tasks.CommentTask;
import abletive.presentation.tasks.ImageTask;
import abletive.presentation.tasks.LikeTask;
import abletive.presentation.tasks.PostTask;
import abletive.presentation.widget.RoundImageView;
import abletive.presentation.widget.ShareListItemAdapter;
import abletive.vo.PostVO;
import abletive.vo.ShareVO;
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
    private DialogPlus shareDialog;
    /**
     * 腾讯QQ实例
     */
    private Tencent mTencent;
    /**
     * 腾讯微信实例
     */
    private IWXAPI mWeChat;
    private BaseIUIListener iUiListener;

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
        thumbUrl = UserTransformer.transfer(thumbUrl);
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
                //分享
                if (shareDialog == null) {
                    shareDialog = DialogPlus.newDialog(PostActivity.this)
                            .setAdapter(new ShareListItemAdapter(PostActivity.this, R.layout.share_item, getShareList()))
                            .setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                                    switch (position) {
                                        //分享给qq好友
                                        case 0:
                                            shareToQQ();
                                            break;
                                        //分享至空间
                                        case 1:
                                            shareToQzone();
                                            break;
                                        //分享给微信好友
                                        case 2:
                                            shareToWeChat();
                                            break;
                                        //分享至朋友圈
                                        case 3:
                                            shareToFriends();
                                            break;
                                        default:
                                            break;
                                    }
                                    dialog.dismiss();
                                }
                            })
                            .setGravity(Gravity.BOTTOM)
                            .setCancelable(true)
                            .create();
                }
                shareDialog.show();
            }
        });
    }

    /**
     * 分享给qq好友
     */
    private void shareToQQ() {
        setQQInstance();
        final Bundle bundle = parseBundle(QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE);
        mTencent.shareToQQ(this, bundle, iUiListener);
    }

    private ArrayList<ShareVO> getShareList() {
        ArrayList<ShareVO> shareList = new ArrayList<>();
        ShareVO qqShare = new ShareVO(BitmapFactory.decodeResource(getResources(), R.drawable.qq), "分享给QQ好友"),
                qzoneShare = new ShareVO(BitmapFactory.decodeResource(getResources(), R.drawable.qzone), "分享至QQ空间"),
                wechatShare = new ShareVO(BitmapFactory.decodeResource(getResources(), R.drawable.wechat_share), "分享给微信好友"),
                friendShare = new ShareVO(BitmapFactory.decodeResource(getResources(), R.drawable.friends), "分享至朋友圈");
        shareList.add(qqShare);
        shareList.add(qzoneShare);
        shareList.add(wechatShare);
        shareList.add(friendShare);
        return shareList;
    }

    /**
     * 分享至空间
     */
    private void shareToQzone() {
        setQQInstance();
        final Bundle bundle = parseBundle(QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        mTencent.shareToQQ(this, bundle, iUiListener);
    }

    /**
     * 包裹类型
     *
     * @param shareType 分享类型
     * @return 包裹完成的bundle
     */
    private Bundle parseBundle(int shareType) {
        Bundle bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, "来自Abletive.com的精彩文章");
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, thumbUrl);
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, "Abletive音乐社区");
        bundle.putInt(QQShare.SHARE_TO_QQ_EXT_INT, shareType);
        return bundle;
    }

    /**
     * 设置QQ的实例和回调接口
     */
    private void setQQInstance() {
        if (mTencent == null) {
            mTencent = Tencent.createInstance(StaticID.TENCENT_APPID, getApplicationContext());
        }
        if (iUiListener == null) {
            iUiListener = new BaseIUIListener();
        }
    }

    /**
     * 分享至微信
     */
    private void shareToWeChat() {
        setWeChatInstance();
        ImageTask imageTask = new ImageTask(thumbUrl);
        imageTask.setImageTaskCallBack(new ImageTask.ImageTaskCallBack() {
            @Override
            public void setImage(Bitmap image) {
                boolean isSend = mWeChat.sendReq(parseReq(image, SendMessageToWX.Req.WXSceneSession));
                processSend(isSend);
            }
        });
        imageTask.execute();
    }


    /**
     * 分享至朋友圈
     */
    private void shareToFriends() {
        setWeChatInstance();
        ImageTask imageTask = new ImageTask(thumbUrl);
        imageTask.setImageTaskCallBack(new ImageTask.ImageTaskCallBack() {
            @Override
            public void setImage(Bitmap image) {
                boolean isSend = mWeChat.sendReq(parseReq(image, SendMessageToWX.Req.WXSceneTimeline));
                processSend(isSend);
            }
        });
        imageTask.execute();
    }

    /**
     * 创建微信请求
     *
     * @param image   加载的图片
     * @param reqType 请求类型
     * @return Req
     */
    private SendMessageToWX.Req parseReq(Bitmap image, int reqType) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(image, 150, 150, true);
        image.recycle();
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.thumbData = Util.bmpToByteArray(scaledBitmap, true);
        msg.title = title;
        msg.description = "来自Abletive.com的精彩文章";
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = reqType;

        return req;
    }

    /**
     * 设置微信实例
     */
    private void setWeChatInstance() {
        if (mWeChat == null) {
            mWeChat = WXAPIFactory.createWXAPI(this, StaticID.WECHAT_APPID, true);
            mWeChat.registerApp(StaticID.WECHAT_APPID);
        }
    }

    /**
     * 设置微信回调
     *
     * @param isSend 是否分享成功
     */
    private void processSend(boolean isSend) {
        if (isSend) {
            Toast.makeText(PostActivity.this, "分享成功！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(PostActivity.this, "分享失败，Abletive期待您的分享~", Toast.LENGTH_SHORT).show();
        }
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

    /**
     * 创建
     *
     * @param type 类型
     * @return
     */
    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
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

    /**
     * shareToQQ的回调接口实现
     */
    private class BaseIUIListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            Toast.makeText(PostActivity.this, "分享成功!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(PostActivity.this, uiError.errorMessage, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(PostActivity.this, "Abletive期待您的分享~", Toast.LENGTH_SHORT).show();
        }
    }
}
