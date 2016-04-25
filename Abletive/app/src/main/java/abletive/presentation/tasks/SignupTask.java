package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import abletive.businesslogic.blutil.UserData;
import abletive.businesslogic.userbl.UserSignImpl;
import abletive.logicservice.userblservice.UserSignService;
import abletive.presentation.uiutil.WidgetTool;
import abletive.vo.SignupVO;
import abletive.vo.UserVO;
import alandelip.abletivedemo.R;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 注册任务
 * Created by Alan on 2016/4/23.
 */
public class SignupTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private SignupVO signupVO;
    private UserSignService userBl;
    private UserVO userVO;
    private UserData userData;
    private SweetAlertDialog dialog;
    private SignupCallBack signupCallBack;

    public SignupTask(Context context, SignupVO signupVO) {
        this.context = context;
        this.signupVO = signupVO;
        userBl = new UserSignImpl();
        userData = UserData.getInstance();
    }

    @Override
    protected void onPreExecute() {
        dialog = WidgetTool.getDialog(context, "注册中...");
        dialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        userVO = userBl.signup(signupVO);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        dialog.dismissWithAnimation();
        if (userVO != null) {
            userData.setIsLogin(true);
            userData.setUserVO(userVO);
            userData.setUserID(userVO.getId());
            //cookie暂时用不到
            userData.setCookie("");
            signupCallBack.signup();
        } else {
            Toast.makeText(context, context.getString(R.string.multiple_usernames), Toast.LENGTH_SHORT).show();
        }
    }

    public void setSignupCallBack(SignupCallBack signupCallBack) {
        this.signupCallBack = signupCallBack;
    }

    public interface SignupCallBack {
        /**
         * 注册后续处理
         */
        void signup();
    }
}
