package abletive.presentation.activity;

import android.content.Context;
import android.content.Intent;
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

import abletive.presentation.tasks.CollectionPostTask;
import abletive.presentation.tasks.PostCollectionTask;
import abletive.presentation.widget.CollectionListAdapter;
import abletive.vo.PostCollectionVO;
import abletive.vo.PostListVO;
import alandelip.abletivedemo.R;

public class CollectionActivity extends AppCompatActivity {

    private static final String ARG_USERNAME = "user_name", ARG_USERID = "user_id";
    private String userName, userID;
    private MaterialRefreshLayout refreshLayout;
    private int page = 1;
    private ArrayList<PostCollectionVO> collectionList;
    private CollectionListAdapter postListAdapter;

    public static void newInstance(Context context, String userID, String userName) {
        Intent intent = new Intent(context, CollectionActivity.class);
        intent.putExtra(ARG_USERNAME, userName);
        intent.putExtra(ARG_USERID, userID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colection);

        if (getIntent() != null) {
            userName = getIntent().getStringExtra(ARG_USERNAME);
            userID = getIntent().getStringExtra(ARG_USERID);
        }
        initViews();
    }

    private void initViews() {
        initToolBar();
        getPage(1);
    }

    private void initListView() {
        final ListView collectionList = (ListView) findViewById(R.id.collection_list);
        postListAdapter = new CollectionListAdapter(this, R.layout.postlist_item, this.collectionList);
        collectionList.setAdapter(postListAdapter);
        collectionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PostCollectionVO postCollectionVO = (PostCollectionVO) parent.getItemAtPosition(position);
                CollectionPostTask collectionPostTask =
                        new CollectionPostTask(CollectionActivity.this, postCollectionVO.getId());
                collectionPostTask.setCollectionPostTaskCallBack(new CollectionPostTask.CollectionPostTaskCallBack() {
                    @Override
                    public void setPost(PostListVO postListVO) {
                        PostActivity.newInstance(CollectionActivity.this, postListVO.toHashMap());
                    }
                });
                collectionPostTask.execute();
            }
        });
    }

    /**
     * 获得某页内容
     *
     * @param requiredPage 需要获得第几页
     */
    private void getPage(final int requiredPage) {
        PostCollectionTask postCollectionTask = new PostCollectionTask(this, userID, requiredPage);
        postCollectionTask.setPostCollectionList(new PostCollectionTask.CollectionPostTaskCallBack() {
            @Override
            public void updatePostList(ArrayList<PostCollectionVO> postCollectionList) {
                //处理刷新
                if (refreshLayout == null) {
                    initRefreshLayout();
                } else {
                    refreshLayout.finishRefresh();
                    refreshLayout.finishRefreshLoadMore();
                }
                //处理返回列表
                if (postCollectionList.size() == 0) {
                    Toast.makeText(CollectionActivity.this,
                            getString(R.string.reach_last), Toast.LENGTH_SHORT).show();
                    return;
                }

                //如果加载下一页就添加列表，否则重新创建列表
                if (CollectionActivity.this.collectionList != null && requiredPage != 1) {
                    CollectionActivity.this.collectionList.addAll(postCollectionList);
                } else {
                    CollectionActivity.this.collectionList = postCollectionList;
                }
                //刷新列表显示
                if (postListAdapter == null) {
                    initListView();
                } else {
                    postListAdapter.notifyDataSetChanged();
                }
                //更新页码
                page = requiredPage + 1;
            }
        });
        postCollectionTask.execute();
    }

    private void initRefreshLayout() {
        refreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh);
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                //刷新获得第一页
                getPage(1);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //获得下一页
                getPage(page);
            }
        });
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        String title = userName + "的文章收藏";
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }

}
