package abletive.presentation.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;

import abletive.businesslogic.blutil.ClientLogic;
import abletive.logicservice.postblservice.ListService;
import abletive.presentation.tasks.NextPageTask;
import abletive.presentation.widget.PostListAdapter;
import abletive.vo.PostListVO;
import alandelip.abletivedemo.R;

/**
 * 搜索结果活动（具体标签、类别、日期、作者文章）
 */
public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "Abletive";

    private int page = 1;
    private String keyWord, id;
    private ListService listBl;
    private PostListAdapter postListAdapter;
    private ListView mListView;
    private ArrayList<PostListVO> postList = new ArrayList<PostListVO>();
    private MaterialRefreshLayout refreshLayout;

    /**
     * @param context     上下文
     * @param keyWord     关键字
     * @param id          标识标签或者类别的ID，在搜索关键字的时候传入keyword相同内容
     * @param listService 列表接口
     */
    public static void newInstance(Context context, String keyWord, String id, ListService listService) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra("keyWord", keyWord);
        intent.putExtra("id", id);
        ClientLogic.getInstance().setListService(listService);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.in_from_bottom, R.anim.out_to_top);
    }

    /**
     * @param context     上下文
     * @param authorName  作者名
     * @param id          作者ID
     * @param listService 列表接口
     */
    public static void newInstanceForUserPage(Context context, String authorName, String id, ListService listService) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra("keyWord", authorName);
        intent.putExtra("id", id);
        intent.putExtra("user", true);
        ClientLogic.getInstance().setListService(listService);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.in_from_bottom, R.anim.out_to_top);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        keyWord = getIntent().getStringExtra("keyWord");
        id = getIntent().getStringExtra("id");
        listBl = ClientLogic.getInstance().getListService();

        initToolBar();
        initListView();
        initRefreshLayout();
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("搜索结果:" + keyWord);
        if (getIntent().getBooleanExtra("user", false)) {
            toolbar.setTitle(keyWord + "的文章列表");
        }
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initListView() {
        mListView = (ListView) findViewById(R.id.result_list);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PostListVO postListVO = (PostListVO) parent.getItemAtPosition(position);
                PostActivity.newInstance(SearchActivity.this, postListVO.toHashMap());
            }
        });
        new InitTask().execute();
    }

    private void initRefreshLayout() {
        refreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh);
        refreshLayout.setLoadMore(true);
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                new InitTask().execute();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                NextPageTask nextPageTask = new NextPageTask(SearchActivity.this, id, mListView, postListAdapter, postList, refreshLayout, listBl);
                nextPageTask.setNextPageCallBack(new NextPageTask.NextPageCallBack() {
                    @Override
                    public void setPostList(ArrayList<PostListVO> nextPagePostList) {
                        postList = nextPagePostList;
                    }

                    @Override
                    public void increasePage() {
                        page++;
                    }
                });
                nextPageTask.execute(page);
            }
        });
    }

    /**
     * 初始化搜索列表，显示第一页
     */
    class InitTask extends AsyncTask<Void, Void, ArrayList<PostListVO>> {

        @Override
        protected ArrayList<PostListVO> doInBackground(Void... params) {
            return listBl.getResultList(1, id);
        }

        @Override
        protected void onPostExecute(ArrayList<PostListVO> postList) {
            refreshLayout.finishRefresh();
            if (postList != null) {
                SearchActivity.this.postList = postList;
                postListAdapter = new PostListAdapter(SearchActivity.this, R.layout.postlist_item, SearchActivity.this.postList);
                mListView.setAdapter(postListAdapter);
                page = 2;
            } else {
                Toast.makeText(SearchActivity.this, getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
