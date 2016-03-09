package alandelip.abletivedemo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import alandelip.abletivedemo.R;
import fragment.BBSFragment;
import fragment.MainFragment;
import fragment.MessageFragment;
import fragment.UserFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Abletive";

    /**
     * 文章列表内容
     */
//    ArrayList<PostTitle> postTitleList;
//    ListView listView;
//    PostTitleAdapter postTitleAdapter;

    /**
     * FragmentTabhost
     */
    private FragmentTabHost mTabHost;

    /**
     * 布局填充器
     */
    private LayoutInflater mLayoutInflater;

    /**
     * 碎片类
     */
    private Class[] mFragmentArray = {MainFragment.class, BBSFragment.class, MessageFragment.class, UserFragment.class};

    /**
     * 选修卡文字
     */
    private String[] mTextArray = {"文章", "消息", "社区", "我"};

    /**
     * 选项卡图标
     */
    private int[] mImageArray = {R.drawable.ic_menu_camera,
            R.drawable.ic_menu_share,
            R.drawable.ic_menu_gallery,
            R.drawable.ic_menu_manage};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        initTabHost();

//        initListView();

    }

    /**
     * 初始化Tabhost进行碎片显示
     */
    private void initTabHost() {
        mLayoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        // 得到fragment的个数
        int count = mFragmentArray.length;
        for (int i = 0; i < count; i++) {
            // 给每个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i])
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
            // 设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.color.gray);
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

//    private void initListView() {
//        listView = (ListView) findViewById(R.id.posts_list);
//        new PostTitleTask().execute();
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                PostTitle postTitle = (PostTitle) parent.getItemAtPosition(position);
//                WebActivity.actionStart(MainActivity.this, postTitle.getUrl(), postTitle.getTitle());
//            }
//        });
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    class PostTitleTask extends AsyncTask<Void, Void, Void> {
//
//        SweetAlertDialog progressDialog;
//
//        @Override
//        protected void onPreExecute() {
//            progressDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
//            progressDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//            progressDialog.setTitleText("Loading");
//            progressDialog.setCancelable(false);
//            progressDialog.show();
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            postTitleList = new HttpImpl("get_posts").getPostTitleList();
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            progressDialog.dismiss();
//            postTitleAdapter = new PostTitleAdapter(MainActivity.this, R.layout.post_title, postTitleList);
//            listView.setAdapter(postTitleAdapter);
//        }
//    }
}
