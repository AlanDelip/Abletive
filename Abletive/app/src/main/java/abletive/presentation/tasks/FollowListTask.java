package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;

import abletive.businesslogic.blutil.UserData;
import abletive.businesslogic.userbl.UserInfoImpl;
import abletive.logicservice.userblservice.UserInfoService;
import abletive.presentation.uiutil.WidgetTool;
import abletive.vo.FollowUserVO;
import alandelip.abletivedemo.R;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 关注人，被关注人任务
 * Created by Alan on 2016/4/30.
 */
public class FollowListTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private String userID, type;
    private UserInfoService userBl;
    private SweetAlertDialog dialog;
    private int page;
    private ArrayList<FollowUserVO> userVOList;
    private FollowTaskCallBack followTaskCallBack;


    public FollowListTask(Context context, String userID, String type, int page) {
        this.context = context;
        this.userID = userID;
        this.type = type;
        this.page = page;
        userBl = new UserInfoImpl();
    }

    @Override
    protected void onPreExecute() {
        if (page == 1) {
            dialog = WidgetTool.getDefaultDialog(context);
            dialog.show();
        }
    }

    @Override
    protected Void doInBackground(Void... aVoid) {
        userVOList = userBl.getFollowList(userID, UserData.getInstance().getUserID(), type, page);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (dialog != null) {
            dialog.dismissWithAnimation();
        }
        if (userVOList != null) {
            if (followTaskCallBack != null) {
                followTaskCallBack.init(userVOList);
            }
        } else {
            Toast.makeText(context, context.getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
        }
    }

    public void setFollowTaskCallBack(FollowTaskCallBack followTaskCallBack) {
        this.followTaskCallBack = followTaskCallBack;
    }

    public interface FollowTaskCallBack {
        /**
         * 初始化界面
         */
        void init(ArrayList<FollowUserVO> userVOList);
    }
}
