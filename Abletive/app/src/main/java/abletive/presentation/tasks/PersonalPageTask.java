package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import abletive.businesslogic.blutil.UserData;
import abletive.businesslogic.userbl.UserInfoImpl;
import abletive.logicservice.userblservice.UserInfoService;
import abletive.presentation.uiutil.WidgetTool;
import abletive.vo.PersonalPageVO;
import alandelip.abletivedemo.R;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 获取个人主页信息任务
 * Created by Alan on 2016/4/30.
 */
public class PersonalPageTask extends AsyncTask<Void, Void, Void> {

    private UserInfoService userBl;
    private String userID;
    private Context context;
    private SweetAlertDialog dialog;
    private PersonalPageVO personalPageVO;
    private PersonalPageCallBack personalPageCallBack;

    /**
     * 个人主页任务构造
     *
     * @param userID 需要查看的UserID
     */
    public PersonalPageTask(Context context, String userID) {
        this.userID = userID;
        this.context = context;
        userBl = new UserInfoImpl();
    }

    @Override
    protected void onPreExecute() {
        dialog = WidgetTool.getDialog(context, "正在获取个人主页");
        dialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        personalPageVO = userBl.getPersonalPage(userID, UserData.getInstance().getUserID());
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        dialog.dismissWithAnimation();
        if (personalPageVO != null) {
            //设置本地数据
            UserData.getInstance().setPersonalPageVO(personalPageVO);
            if(personalPageCallBack!=null){
                personalPageCallBack.initViews();
            }
        } else {
            Toast.makeText(context, context.getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
        }
    }

    public void setPersonPeronalPageCallBack(PersonalPageCallBack personPeronalPageCallBack) {
        this.personalPageCallBack = personPeronalPageCallBack;
    }

    public interface PersonalPageCallBack {
        /**
         * 加载完personalPageVO后再初始化
         */
        void initViews();
    }
}
