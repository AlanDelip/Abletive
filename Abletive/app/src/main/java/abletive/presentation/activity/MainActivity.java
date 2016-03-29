package abletive.presentation.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import alandelip.abletivedemo.R;
import abletive.presentation.fragment.BBSFragment;
import abletive.presentation.fragment.MainFragment;
import abletive.presentation.fragment.MessageFragment;
import abletive.presentation.fragment.UserFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Abletive";

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
        setContentView(R.layout.activity_main);

        initTabHost();
    }

    /**
     * 初始化Tabhost进行碎片显示
     */
    private void initTabHost() {

        mLayoutInflater = LayoutInflater.from(this);
        final FragmentTabHost mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        // 得到fragment的个数
        int count = mFragmentArray.length;
        for (int i = 0; i < count; i++) {
            // 给每个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i])
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
            // 设置Tab分割线
            mTabHost.getTabWidget().setDividerDrawable(null);
            // 设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.color.menu_background);
        }
    }

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

}
