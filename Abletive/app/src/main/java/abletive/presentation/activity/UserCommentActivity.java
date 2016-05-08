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

import java.util.ArrayList;

import abletive.presentation.tasks.UserCommentTask;
import abletive.presentation.widget.UserCommentItemAdapter;
import abletive.vo.UserCommentVO;
import alandelip.abletivedemo.R;

public class UserCommentActivity extends AppCompatActivity {

    private static final String ARG_USERID = "user_id", ARG_USERNAME = "user_name";
    private String userID, userName;
    private int page = 1;
    private UserCommentItemAdapter userCommentItemAdapter;
    private ArrayList<UserCommentVO> commentList;


    public static void newInstance(Context context, String userID, String userName) {
        Intent intent = new Intent(context, UserCommentActivity.class);
        intent.putExtra(ARG_USERID, userID);
        intent.putExtra(ARG_USERNAME, userName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        if (getIntent() != null) {
            userID = getIntent().getStringExtra(ARG_USERID);
            userName = getIntent().getStringExtra(ARG_USERNAME);
        }

        initViews();
    }

    private void initViews() {
        initToolBar();
        getPage(1);
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        String title = userName + "的评论";
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }

    /**
     * 获得某页内容
     *
     * @param requiredPage 需要获得第几页
     */
    private void getPage(final int requiredPage) {
        UserCommentTask userCommentTask = new UserCommentTask(this, userID, requiredPage);
        userCommentTask.setUserCommentTaskCallBack(new UserCommentTask.UserCommentTaskCallBack() {
            @Override
            public void updateCommentList(ArrayList<UserCommentVO> commentList) {
                //处理返回列表
                if (commentList.size() == 0) {
                    Toast.makeText(UserCommentActivity.this,
                            getString(R.string.reach_last), Toast.LENGTH_SHORT).show();
                    return;
                }

                //如果加载下一页就添加列表，否则重新创建列表
                if (UserCommentActivity.this.commentList != null && requiredPage != 1) {
                    UserCommentActivity.this.commentList.addAll(commentList);
                } else {
                    UserCommentActivity.this.commentList = commentList;
                }
                //刷新列表显示
                if (userCommentItemAdapter == null) {
                    initListView();
                } else {
                    userCommentItemAdapter.notifyDataSetChanged();
                }
                //更新页码
                page = requiredPage + 1;
            }
        });
        userCommentTask.execute();
    }

    private void initListView() {
        ListView commentListView = (ListView) findViewById(R.id.comment_list);
        userCommentItemAdapter = new UserCommentItemAdapter(this, R.layout.user_comment_item, commentList);
        commentListView.setAdapter(userCommentItemAdapter);

        commentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO 跳转至文章界面
            }
        });
    }

}
