package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;

import abletive.businesslogic.userbl.UserInfoImpl;
import abletive.logicservice.userblservice.UserInfoService;
import abletive.presentation.uiutil.WidgetTool;
import abletive.vo.UserCommentVO;
import alandelip.abletivedemo.R;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 个人主页评论列表
 * Created by Alan on 2016/5/7.
 */
public class UserCommentTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private SweetAlertDialog dialog;
    private String userID;
    private int page;
    private UserInfoService userBl;
    private ArrayList<UserCommentVO> commentList;
    private UserCommentTaskCallBack userCommentTaskCallBack;

    public UserCommentTask(Context context, String userID, int page) {
        this.context = context;
        this.userID = userID;
        this.page = page;
        userBl = new UserInfoImpl();
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
        commentList = userBl.getUserCommentList(userID, page);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (dialog != null) {
            dialog.dismissWithAnimation();
        }
        if (commentList != null) {
            userCommentTaskCallBack.updateCommentList(commentList);
        } else {
            Toast.makeText(context, context.getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
        }
    }

    public void setUserCommentTaskCallBack(UserCommentTaskCallBack userCommentTaskCallBack) {
        this.userCommentTaskCallBack = userCommentTaskCallBack;
    }

    public interface UserCommentTaskCallBack {
        /**
         * 更新评论列表
         *
         * @param commentList 评论列表
         */
        void updateCommentList(ArrayList<UserCommentVO> commentList);
    }
}
