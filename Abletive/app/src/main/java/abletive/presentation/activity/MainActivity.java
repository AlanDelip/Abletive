package abletive.presentation.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import abletive.presentation.fragment.BBSFragment;
import abletive.presentation.fragment.MainFragment;
import abletive.presentation.fragment.MessageFragment;
import abletive.presentation.fragment.SearchFragment;
import abletive.presentation.fragment.UserFragment;
import abletive.vo.PostVO;
import alandelip.abletivedemo.R;

public class MainActivity extends AppCompatActivity implements SearchFragment.OnSearchResultClickedListener, View.OnClickListener {
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
    private View mainLayout,messageLayout,bbsLayout,userLayout;

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
     * 布局填充器
     */
    private LayoutInflater mLayoutInflater;

    /**
     * 碎片类
     */
    private Class[] mFragmentArray = {MainFragment.class, MessageFragment.class, BBSFragment.class, UserFragment.class};

    /**
     * 选修卡文字
     */
    private String[] mTextArray = {"文章", "消息", "社区", "我"};

    /**
     * 选项卡图标
     */
    private int[] mImageArray = {R.drawable.menu_post,
            R.drawable.menu_chat,
            R.drawable.menu_community,
            R.drawable.menu_me};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initViews();
        fragmentManager = getSupportFragmentManager();
        //默认加载文章界面
        setTabSelection(0);
//        initTabHost();
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

    /**
     * 初始化Tabhost进行碎片显示
     */
//    private void initTabHost() {
//
//        mLayoutInflater = LayoutInflater.from(this);
//        final FragmentTabHost mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
//        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
//        // 得到fragment的个数
//        int count = mFragmentArray.length;
//        for (int i = 0; i < count; i++) {
//            // 给每个Tab按钮设置图标、文字和内容
//            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i])
//                    .setIndicator(getTabItemView(i));
//            // 将Tab按钮添加进Tab选项卡中
//            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
//            // 设置Tab分割线
//            mTabHost.getTabWidget().setDividerDrawable(null);
//            // 设置Tab按钮的背景
//            mTabHost.getTabWidget().getChildAt(i)
//                    .setBackgroundResource(R.color.menu_background);
//        }
//    }


    /**
     * 给每个Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {

        View view = mLayoutInflater.inflate(R.layout.tab_item_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_image);
        imageView.setImageResource(mImageArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.tab_text);
        textView.setText(mTextArray[index]);

        return view;
    }

    @Override
    public void jumpToWebFragment(PostVO postVO) {
        //TODO 跳转至？
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
}