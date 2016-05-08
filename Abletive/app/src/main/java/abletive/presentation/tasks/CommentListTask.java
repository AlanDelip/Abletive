package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;

import abletive.businesslogic.postbl.PostImpl;
import abletive.logicservice.postblservice.PostService;
import abletive.presentation.uiutil.WidgetTool;
import abletive.vo.CommentListVO;
import abletive.vo.PostVO;
import alandelip.abletivedemo.R;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 评论列表任务
 * Created by Alan on 2016/5/7.
 */
public class CommentListTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private String postID;
    private int page;
    private ArrayList<CommentListVO> commentList;
    private PostService postBl;
    private SweetAlertDialog dialog;
    private CommentListTaskCallBack commentListTaskCallBack;

    public CommentListTask(Context context, String postID, int page) {
        this.context = context;
        this.postID = postID;
        this.page = page;
        postBl = new PostImpl();
    }

    @Override
    protected void onPreExecute() {
        if (page == 1) {
            dialog = WidgetTool.getDialog(context, "获取评论列表中...");
            dialog.show();
        }
    }

    @Override
    protected Void doInBackground(Void... params) {
        PostVO postVO = postBl.getPost(postID);
        commentList = postVO.getCommentList();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (dialog != null) {
            dialog.dismissWithAnimation();
        }
        if (commentList != null) {
            commentListTaskCallBack.updateCommentList(commentList);
        } else {
            Toast.makeText(context, context.getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
        }

    }

    public void setCommentListTaskCallBack(CommentListTaskCallBack commentListTaskCallBack) {
        this.commentListTaskCallBack = commentListTaskCallBack;
    }

    public interface CommentListTaskCallBack {
        /**
         * 更新评论列表
         *
         * @param commentList 评论列表
         */
        void updateCommentList(ArrayList<CommentListVO> commentList);
    }
}
