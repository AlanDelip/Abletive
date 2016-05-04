package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import abletive.businesslogic.blutil.UserData;
import abletive.businesslogic.userbl.UserInfoImpl;
import abletive.logicservice.userblservice.UserInfoService;
import abletive.presentation.uiutil.WidgetTool;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 关注任务
 * Created by Alan on 2016/5/3.
 */
public class FollowTask extends AsyncTask<Void, Void, Boolean> {

    private String userID, followPresent;
    private FollowState followState;
    private Context context;
    private SweetAlertDialog dialog;
    private UserInfoService userBl;
    private FollowTaskCallBack followTaskCallBack;

    public FollowTask(Context context, String userID, FollowState followState) {
        this.context = context;
        this.userID = userID;
        this.followState = followState;
        if (followState == FollowState.FOLLOW) {
            followPresent = "关注";
        } else {
            followPresent = "取消关注";
        }
        userBl = new UserInfoImpl();
    }

    @Override
    protected void onPreExecute() {
        dialog = WidgetTool.getDialog(context, followPresent + "中...");
        dialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        if (followState == FollowState.FOLLOW) {
            return userBl.follow(userID, UserData.getInstance().getUserID());
        } else {
            return userBl.unfollow(userID, UserData.getInstance().getUserID());
        }
    }

    @Override
    protected void onPostExecute(Boolean state) {
        dialog.dismissWithAnimation();
        if (state) {
            Toast.makeText(context, followPresent + "成功", Toast.LENGTH_SHORT).show();
            followTaskCallBack.afterFollow("已" + followPresent);
        } else {
            Toast.makeText(context, followPresent + "失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void setFollowTaskCallBack(FollowTaskCallBack followTaskCallBack) {
        this.followTaskCallBack = followTaskCallBack;
    }

    public enum FollowState {
        FOLLOW, UNFOLLOW;
    }

    public interface FollowTaskCallBack {
        /**
         * 在关注操作后改变用户之间的状态
         *
         * @param content 状态内容
         */
        void afterFollow(String content);
    }
}
