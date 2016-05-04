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

import abletive.presentation.tasks.FollowListTask;
import abletive.presentation.widget.UserItemAdapter;
import abletive.vo.FollowUserVO;
import alandelip.abletivedemo.R;

public class FollowActivity extends AppCompatActivity {

    private static final String ARG_USERNAME = "user_name",
            ARG_FOLLOWSTATE = "follow_state", ARG_USERID = "userID";
    private String userName, userID;
    private ArrayList<FollowUserVO> userVOList;
    private int page = 1;
    private String follow, followString;
    private UserItemAdapter userItemAdapter;
    private MaterialRefreshLayout refreshLayout;

    /**
     * @param context
     * @param userName    用户名
     * @param followState 1代表关注者，2代表粉丝
     * @param userID      用户ID
     */
    public static void newInstance(Context context, String userName, int followState, String userID) {
        Intent intent = new Intent(context, FollowActivity.class);
        intent.putExtra(ARG_USERID, userID);
        intent.putExtra(ARG_USERNAME, userName);
        intent.putExtra(ARG_FOLLOWSTATE, followState);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);

        if (getIntent() != null) {
            userName = getIntent().getStringExtra(ARG_USERNAME);
            int followState = getIntent().getIntExtra(ARG_FOLLOWSTATE, 1);
            if (followState == 1) {
                follow = getString(R.string.following);
                followString = "关注者";
            } else {
                follow = getString(R.string.follower);
                followString = "粉丝";
            }
            userID = getIntent().getStringExtra(ARG_USERID);
        }

        initViews();

    }

    private void initViews() {
        initToolBar();
        //获得第一页内容
        getPage(1);
    }


    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        String title = userName + "的" + followString;
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }

    /**
     * 获得某页内容
     *
     * @param requiredPage 需要获得第几页
     */
    private void getPage(final int requiredPage) {
        FollowListTask followListTask = new FollowListTask(this, userID, follow, requiredPage);
        followListTask.setFollowTaskCallBack(new FollowListTask.FollowTaskCallBack() {
            @Override
            public void init(ArrayList<FollowUserVO> userVOList) {
                //处理刷新
                if (refreshLayout == null) {
                    initRefreshLayout();
                } else {
                    refreshLayout.finishRefresh();
                    refreshLayout.finishRefreshLoadMore();
                }
                //处理返回列表
                if (userVOList.size() == 0) {
                    Toast.makeText(FollowActivity.this,
                            getString(R.string.reach_last), Toast.LENGTH_SHORT).show();
                    return;
                }
                //如果加载下一页就添加列表，否则重新创建列表
                if (FollowActivity.this.userVOList != null && requiredPage != 1) {
                    FollowActivity.this.userVOList.addAll(userVOList);
                } else {
                    FollowActivity.this.userVOList = userVOList;
                }
                //刷新列表显示
                if (userItemAdapter == null) {
                    initListView();
                } else {
                    userItemAdapter.notifyDataSetChanged();
                }
                //更新页码
                page = requiredPage + 1;
            }
        });
        followListTask.execute();
    }

    /**
     * 初始化刷新
     */
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

    private void initListView() {
        ListView followingList = (ListView) findViewById(R.id.follow_list);
        userItemAdapter = new UserItemAdapter(this, R.layout.user_item, userVOList);
        followingList.setAdapter(userItemAdapter);
        followingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FollowUserVO userVO = (FollowUserVO) parent.getItemAtPosition(position);
                PersonalPageActivity.newInstance(FollowActivity.this, userVO.getId());
            }
        });
    }

}
