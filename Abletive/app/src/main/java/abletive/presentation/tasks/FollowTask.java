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
public class FollowTask extends AsyncTask<Integer, Void, Void> {

    private Context context;
    private String userID, type;
    private UserInfoService userBl;
    private SweetAlertDialog dialog;
    private ArrayList<FollowUserVO> userVOList;
    private FollowTaskCallBack followTaskCallBack;


    public FollowTask(Context context, String userID, String type) {
        this.context = context;
        this.userID = userID;
        this.type = type;
        userBl = new UserInfoImpl();
    }

    @Override
    protected void onPreExecute() {
        dialog = WidgetTool.getDefaultDialog(context);
        dialog.show();
    }

    @Override
    protected Void doInBackground(Integer... page) {
        userVOList = userBl.getFollowList(userID, UserData.getInstance().getUserID(), type, page[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        dialog.dismissWithAnimation();
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
