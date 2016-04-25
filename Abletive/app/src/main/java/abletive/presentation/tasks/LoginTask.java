package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import abletive.businesslogic.blutil.UserData;
import abletive.businesslogic.userbl.UserSignImpl;
import abletive.logicservice.userblservice.UserSignService;
import abletive.presentation.uiutil.WidgetTool;
import abletive.vo.UserVO;
import alandelip.abletivedemo.R;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 登录任务
 * Created by Alan on 2016/4/23.
 */
public class LoginTask extends AsyncTask<Void, Void, Void> {
    private SweetAlertDialog dialog;
    private String username, password;
    private UserSignService userBl;
    private UserVO userVO;
    private UserData userData;
    private LoginCallBack loginCallBack;
    private Context context;

    public LoginTask(Context context,String username, String password) {
        this.username = username;
        this.password = password;
        userBl = new UserSignImpl();
        userData = UserData.getInstance();
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = WidgetTool.getDialog(context, "登录中...");
        dialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        userVO = userBl.signin(username, password);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        dialog.dismissWithAnimation();
        if (userVO != null) {
            userData.setIsLogin(true);
            userData.setUserVO(userVO);
            userData.setUserID(userVO.getId());
            //cookie暂时用不了
            userData.setCookie("");
            loginCallBack.login();
        } else {
            Toast.makeText(context, context.getString(R.string.login_fail), Toast.LENGTH_SHORT).show();
        }
    }

    public void setLoginCallBack(LoginCallBack loginCallBack) {
        this.loginCallBack = loginCallBack;
    }

    public interface LoginCallBack {
        /**
         * 登录完成后续
         */
        void login();
    }
}
