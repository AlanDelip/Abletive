package abletive.businesslogic.internetbl;

import android.content.Context;

import abletive.businesslogic.blutil.HttpBuilder;
import abletive.businesslogic.blutil.HttpConnection;
import abletive.businesslogic.blutil.JSONHandler;
import abletive.logicservice.internetblservice.UserHttpService;
import abletive.po.HttpSignupPO;
import abletive.po.HttpUserPO;
import abletive.po.UserPO;
import abletive.presentation.uiutil.MApplication;
import abletive.vo.SignupVO;
import alandelip.abletivedemo.R;

/**
 * 用户相关网络逻辑
 *
 * @author Alan
 */
public class UserHttpImpl implements UserHttpService {

    private final static String TAG = "Abletive";
    /**
     * http连接工具
     */
    HttpConnection httpConnection;
    /**
     * 暂存全局上下文
     */
    Context context;

    public UserHttpImpl() {
        httpConnection = new HttpConnection();
        context = MApplication.getContext();
    }

    @Override
    public HttpUserPO signin(String userID, String password) {
        String request =
                new HttpBuilder()
                        .addField(context.getString(R.string.auth), false)
                        .addField(context.getString(R.string.generate_auth_cookie))
                        .addParam(context.getString(R.string.username), userID)
                        .addParam(context.getString(R.string.password), password)
                        .build();

        String result = httpConnection.getResult(request);

        HttpUserPO httpUserPO = null;
        if (result != null) {
            if (result.length() != 0) {
                httpUserPO = JSONHandler.getHttpUserPO(result);
            }
        }
        return httpUserPO;
    }

    @Override
    public HttpSignupPO signup(SignupVO signupVO) {

        String nonceRequest =
                new HttpBuilder()
                .addField(context.getString(R.string.api),false)
                .addField(context.getString(R.string.get_nonce))
                .addParam(context.getString(R.string.controller),context.getString(R.string.user))
                .addParam(context.getString(R.string.method),context.getString(R.string.register))
                .build();

        String result = httpConnection.getResult(nonceRequest);
        String nonce = "";
        if(result!=null){
            if(result.length()!=0){
                nonce = JSONHandler.getNonce(result);
            }
        }

        String request =
                new HttpBuilder()
                        .addField(context.getString(R.string.user), false)
                        .addField(context.getString(R.string.register))
                        .addParam(context.getString(R.string.username), signupVO.getUsername())
                        .addParam(context.getString(R.string.user_pass), signupVO.getUserpass())
                        .addParam(context.getString(R.string.email),signupVO.getEmail())
                        .addParam(context.getString(R.string.display_name),signupVO.getDisplayname())
                        .build();
        result = httpConnection.getResult(request);

        HttpSignupPO httpSignupPO = null;
        if(result!=null){
            if(result.length()!=0){
                httpSignupPO = JSONHandler.getSignup(result);
            }
        }
        return httpSignupPO;
    }

    @Override
    public UserPO getUserInfo(String userID) {
        String request =
                new HttpBuilder()
                .addField(context.getString(R.string.user),false)
                .addField(context.getString(R.string.get_userinfo))
                .addParam(context.getString(R.string.user_id),userID)
                .build();
        String result = httpConnection.getResult(request);

        UserPO userPO = null;
        if(result!=null){
            if(result.length()!=0){
                userPO = JSONHandler.getUserPO(result);
            }
        }
        return userPO;
    }
}
