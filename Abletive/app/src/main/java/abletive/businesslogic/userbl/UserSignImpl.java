package abletive.businesslogic.userbl;

import abletive.businesslogic.blutil.UserData;
import abletive.businesslogic.blutil.UserTransformer;
import abletive.businesslogic.internetbl.UserHttpImpl;
import abletive.logicservice.internetblservice.UserHttpService;
import abletive.logicservice.userblservice.UserSignService;
import abletive.po.HttpSignupPO;
import abletive.po.HttpUserPO;
import abletive.po.UserPO;
import abletive.presentation.uiutil.MApplication;
import abletive.vo.SignupVO;
import abletive.vo.UserVO;
import alandelip.abletivedemo.R;

/**
 * 用户登录、登出、注册逻辑
 *
 * @author Alan
 */
public class UserSignImpl implements UserSignService {

    private final static String TAG = "Abletive";

    UserHttpService userBl;
    UserData userData;

    public UserSignImpl() {
        userBl = new UserHttpImpl();
        userData = UserData.getInstance();
    }

    @Override
    public UserVO signin(String userID, String password) {
        HttpUserPO httpUserPO = userBl.signin(userID, password);
        UserVO userVO = null;
        //如果返回status不为ok直接返回null
        if (httpUserPO != null) {
            if (httpUserPO.getStatus().equals(MApplication.getContext().getString(R.string.ok))) {
                userVO = UserTransformer.getUserVO(httpUserPO);
                //设置已经登录
                userData.setIsLogin(true);
                //设置登录的用户信息
                userData.setUserVO(userVO);
            }
        }
        return userVO;
    }

    @Override
    public boolean signout() {
        boolean isLogin = userData.isLogin();
        if (isLogin) {
            userData.setIsLogin(false);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserVO signup(SignupVO signupVO) {
        HttpSignupPO httpSignupPO = userBl.signup(signupVO);
        String cookie = httpSignupPO.getCookie();
        int userID = httpSignupPO.getUser_id();
        UserPO userPO = userBl.getUserInfo(userID + "");
        UserVO userVO = null;
        if (userPO.getStatus().equals(MApplication.getContext().getString(R.string.ok))) {
            userVO = UserTransformer.getUserVO(userPO, cookie);
            //设置已经登录
            userData.setIsLogin(true);
            //设置登录的用户信息
            userData.setUserVO(userVO);
        }
        return userVO;
    }
}
