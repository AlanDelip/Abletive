package abletive.businesslogic.internetbl;

import android.content.Context;

import java.util.ArrayList;

import abletive.businesslogic.blutil.HttpBuilder;
import abletive.businesslogic.blutil.HttpConnection;
import abletive.businesslogic.blutil.JSONHandler;
import abletive.logicservice.internetblservice.UserHttpService;
import abletive.po.HttpDailyCheckinPO;
import abletive.po.HttpPersonalPagePO;
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
                        .addField(context.getString(R.string.get_nonce))
                        .addParam(context.getString(R.string.controller), context.getString(R.string.user))
                        .addParam(context.getString(R.string.method), context.getString(R.string.register))
                        .build();

        String result = httpConnection.getResult(nonceRequest);
        String nonce = "";
        if (result != null) {
            if (result.length() != 0) {
                nonce = JSONHandler.getNonce(result);
            }
        }

        String request =
                new HttpBuilder()
                        .addField(context.getString(R.string.user), false)
                        .addField(context.getString(R.string.register))
                        .addParam(context.getString(R.string.username), signupVO.getUsername())
                        .addParam(context.getString(R.string.user_pass), signupVO.getUserpass())
                        .addParam(context.getString(R.string.email), signupVO.getEmail())
                        .addParam(context.getString(R.string.display_name), signupVO.getDisplayname())
                        .addParam(context.getString(R.string.nonce),nonce)
                        .build();
        result = httpConnection.getResult(request);

        HttpSignupPO httpSignupPO = null;
        if (result != null) {
            if (result.length() != 0) {
                httpSignupPO = JSONHandler.getSignup(result);
            }
        }
        return httpSignupPO;
    }

    @Override
    public UserPO getUserInfo(String userID) {
        String request =
                new HttpBuilder()
                        .addField(context.getString(R.string.user), false)
                        .addField(context.getString(R.string.get_userinfo))
                        .addParam(context.getString(R.string.user_id), userID)
                        .build();
        String result = httpConnection.getResult(request);

        UserPO userPO = null;
        if (result != null) {
            if (result.length() != 0) {
                userPO = JSONHandler.getUserPO(result);
            }
        }
        return userPO;
    }

    @Override
    public HttpPersonalPagePO getPersonalPage(String userID, String currentUserID) {
        String request =
                new HttpBuilder()
                        .addField(context.getString(R.string.user), false)
                        .addField(context.getString(R.string.get_personal_page_detail))
                        .addParam(context.getString(R.string.user_id), userID)
                        .addParam(context.getString(R.string.current_user_id), currentUserID)
                        .build();
        String result = httpConnection.getResult(request);

        HttpPersonalPagePO httpPersonalPagePO = null;
        if (result != null) {
            if (result.length() != 0) {
                httpPersonalPagePO = JSONHandler.getPersonPage(result);
            }
        }
        return httpPersonalPagePO;
    }

    @Override
    public ArrayList<UserPO> getFollowList(String userID, String currentUserID, int page) {
        String request =
                new HttpBuilder()
                        .addField(context.getString(R.string.user), false)
                        .addField(context.getString(R.string.get_follow_list))
                        .addParam(context.getString(R.string.user_id), userID)
                        .addParam(context.getString(R.string.current_user_id), currentUserID)
                        .addParam(context.getString(R.string.page), page)
                        .build();
        String result = httpConnection.getResult(request);

        ArrayList<UserPO> userPOList = null;
        if (result != null) {
            if (result.length() != 0) {
                userPOList = JSONHandler.getFollowList(result);
            }
        }
        return userPOList;
    }

    @Override
    public HttpDailyCheckinPO dailyCheckin(String userID) {
        String request =
                new HttpBuilder()
                        .addField(context.getString(R.string.user), false)
                        .addField(context.getString(R.string.daily_checkin))
                        .addParam(context.getString(R.string.user_id), userID)
                        .build();
        String result = httpConnection.getResult(request);

        HttpDailyCheckinPO httpDailyCheckinPO = null;
        if (result != null) {
            if (result.length() != 0) {
                httpDailyCheckinPO = JSONHandler.getCheckinInfo(result);
            }
        }
        return httpDailyCheckinPO;
    }

    @Override
    public int follow(String userID, String currentUserID, String act) {
        String request =
                new HttpBuilder()
                        .addField(context.getString(R.string.user), false)
                        .addField(context.getString(R.string.follow_act))
                        .addParam(context.getString(R.string.user_id), userID)
                        .addParam(context.getString(R.string.followed), currentUserID)
                        .addParam(context.getString(R.string.act), act)
                        .build();
        String result = httpConnection.getResult(request);

        int followState = 0;
        if (result != null) {
            if (result.length() != 0) {
                followState = JSONHandler.getFollowState(result);
            }
        }
        return followState;
    }
}
