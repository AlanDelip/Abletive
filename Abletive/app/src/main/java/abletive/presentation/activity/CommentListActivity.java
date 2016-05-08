package abletive.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import abletive.businesslogic.blutil.UserData;
import abletive.presentation.tasks.CommentListTask;
import abletive.presentation.tasks.CommentTask;
import abletive.presentation.widget.CommentListItemAdapter;
import abletive.vo.CommentListVO;
import alandelip.abletivedemo.R;

public class CommentListActivity extends AppCompatActivity {

    private static final String ARG_POSTID = "post_id";
    private int page = 1;
    private String postID, parentID = "0";
    private ArrayList<CommentListVO> commentList;
    private CommentListItemAdapter commentListItemAdapter;
    private EditText replyEdit;

    public static void newInstance(Context context, String postID) {
        Intent intent = new Intent(context, CommentListActivity.class);
        intent.putExtra(ARG_POSTID, postID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);

        if (getIntent() != null) {
            postID = getIntent().getStringExtra(ARG_POSTID);
        }

        initViews();
    }

    private void initViews() {
        initToolBar();
        initReplyBar();
        getPage(1);
    }

    private void initReplyBar() {
        replyEdit = (EditText) findViewById(R.id.comment_area);
        TextView replyButton = (TextView) findViewById(R.id.comment_confirm);
        replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserData.getInstance().isLogin()) {
                    String commentContent = replyEdit.getText().toString();
                    CommentTask commentTask = new CommentTask(CommentListActivity.this,
                            UserData.getInstance().getUserID(), postID, parentID, commentContent,
                            UserData.getInstance().getUserVO().getEmail());
                    commentTask.execute();
                } else {
                    Toast.makeText(CommentListActivity.this, "请先登录~", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 获得某页内容
     *
     * @param requiredPage 需要获得第几页
     */
    private void getPage(final int requiredPage) {
        CommentListTask commentListTask = new CommentListTask(this, postID, requiredPage);
        commentListTask.setCommentListTaskCallBack(new CommentListTask.CommentListTaskCallBack() {
            @Override
            public void updateCommentList(ArrayList<CommentListVO> commentList) {
                //处理返回列表
                if (commentList.size() == 0) {
                    Toast.makeText(CommentListActivity.this,
                            getString(R.string.reach_last), Toast.LENGTH_SHORT).show();
                    return;
                }

                //如果加载下一页就添加列表，否则重新创建列表
                if (CommentListActivity.this.commentList != null && requiredPage != 1) {
                    CommentListActivity.this.commentList.addAll(commentList);
                } else {
                    CommentListActivity.this.commentList = commentList;
                }
                //刷新列表显示
                if (commentListItemAdapter == null) {
                    initListView();
                } else {
                    commentListItemAdapter.notifyDataSetChanged();
                }
                //更新页码
                page = requiredPage + 1;
            }
        });
        commentListTask.execute();
    }

    private void initListView() {
        ListView commentListView = (ListView) findViewById(R.id.comment_list);
        commentListItemAdapter = new CommentListItemAdapter(this, R.layout.comment_list_item, commentList);
        commentListView.setAdapter(commentListItemAdapter);

        Log.d("Abletive", "initListView: ");
        commentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Ableitve", "onItemClick: ");
                CommentListVO commentListVO = (CommentListVO) parent.getItemAtPosition(position);
                //获得焦点
                replyEdit.requestFocus();
                replyEdit.setHint("回复" + commentListVO.getAuthor());
                parentID = commentListVO.getId();
            }
        });
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
