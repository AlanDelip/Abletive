package abletive.presentation.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import abletive.presentation.fragment.BBSFragment;
import abletive.presentation.fragment.MainFragment;
import abletive.presentation.fragment.MessageFragment;
import abletive.presentation.fragment.UserFragment;
import alandelip.abletivedemo.R;
import cn.trinea.android.common.service.impl.ImageCache;
import cn.trinea.android.common.util.CacheManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final ImageCache IMAGE_CACHE = CacheManager.getImageCache();
    private static final String TAG = "Abletive";
    /**
     * 文章界面碎片
     */
    private MainFragment mainFragment;

    /**
     * 消息界面碎片
     */
    private MessageFragment messageFragment;

    /**
     * 论坛界面碎片
     */
    private BBSFragment bbsFragment;

    /**
     * 用户界面碎片
     */
    private UserFragment userFragment;

    /**
     * 导航布局
     */
    private View mainLayout, messageLayout, bbsLayout, userLayout;

    /**
     * 导航栏图片
     */
    private ImageView postImage, messageImage, bbsImage, userImage;

    /**
     * 导航栏文字
     */
    private TextView postText, messageText, bbsText, userText;

    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;

    /**
     * 判断是否退出
     */
    private boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initViews();
        fragmentManager = getSupportFragmentManager();
        //默认加载文章界面
        setTabSelection(0);
    }

    /**
     * 初始化View
     */
    private void initViews() {
        mainLayout = findViewById(R.id.post_layout);
        messageLayout = findViewById(R.id.chat_layout);
        bbsLayout = findViewById(R.id.community_layout);
        userLayout = findViewById(R.id.me_layout);
        postImage = (ImageView) findViewById(R.id.post_image);
        messageImage = (ImageView) findViewById(R.id.chat_image);
        bbsImage = (ImageView) findViewById(R.id.community_image);
        userImage = (ImageView) findViewById(R.id.me_image);
        postText = (TextView) findViewById(R.id.post_text);
        messageText = (TextView) findViewById(R.id.chat_text);
        bbsText = (TextView) findViewById(R.id.community_text);
        userText = (TextView) findViewById(R.id.me_text);
        mainLayout.setOnClickListener(this);
        messageLayout.setOnClickListener(this);
        bbsLayout.setOnClickListener(this);
        userLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.post_layout:
                setTabSelection(0);
                break;
            case R.id.chat_layout:
                setTabSelection(1);
                break;
            case R.id.community_layout:
                setTabSelection(2);
                break;
            case R.id.me_layout:
                setTabSelection(3);
                break;
            default:
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0表示文章，1表示消息，2表示论坛，3表示用户
     */
    private void setTabSelection(int index) {
        //选择前先清除所有选中项
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                postImage.setImageResource(R.drawable.post_selected);
                postText.setTextColor(Color.YELLOW);
                if (mainFragment == null) {
                    mainFragment = new MainFragment();
                    transaction.add(R.id.content, mainFragment);
                } else {
                    transaction.show(mainFragment);
                }
                break;
            case 1:
                messageImage.setImageResource(R.drawable.chat_selected);
                messageText.setTextColor(Color.YELLOW);
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                    transaction.add(R.id.content, messageFragment);
                } else {
                    transaction.show(messageFragment);
                }
                break;
            case 2:
                bbsImage.setImageResource(R.drawable.community_selected);
                bbsText.setTextColor(Color.YELLOW);
                if (bbsFragment == null) {
                    bbsFragment = new BBSFragment();
                    transaction.add(R.id.content, bbsFragment);
                } else {
                    transaction.show(bbsFragment);
                }
                break;
            case 3:
                userImage.setImageResource(R.drawable.me_selected);
                userText.setTextColor(Color.YELLOW);
                if (userFragment == null) {
                    userFragment = new UserFragment();
                    transaction.add(R.id.content, userFragment);
                } else {
                    transaction.show(userFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        postImage.setImageResource(R.drawable.post);
        postText.setTextColor(Color.WHITE);
        messageImage.setImageResource(R.drawable.chat);
        messageText.setTextColor(Color.WHITE);
        bbsImage.setImageResource(R.drawable.community);
        bbsText.setTextColor(Color.WHITE);
        userImage.setImageResource(R.drawable.me);
        userText.setTextColor(Color.WHITE);
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
        if (mainFragment != null) {
            transaction.hide(mainFragment);
        }
        if (userFragment != null) {
            transaction.hide(userFragment);
        }
        if (bbsFragment != null) {
            transaction.hide(bbsFragment);
        }
    }

    @Override
    public void onBackPressed() {
        exitBy2Click();
    }

    private void exitBy2Click() {
        Timer tExit = null;
        if (!isExit) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 1500); // 如果1.5秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            super.onBackPressed();
        }
    }
}