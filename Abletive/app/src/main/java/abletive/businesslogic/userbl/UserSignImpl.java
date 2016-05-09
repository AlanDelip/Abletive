package abletive.businesslogic.userbl;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

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
    public UserVO signin(Context context, String userID, String password) {
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
                //保存用户信息
                Properties properties = getLogProperty(context);
                if (properties != null) {
                    properties.setProperty("isLogin", "true");
                    properties.setProperty("id", userVO.getId());
                    properties.setProperty("username", userVO.getUsername());
                    properties.setProperty("nickname", userVO.getNickname());
                    properties.setProperty("email", userVO.getEmail());
                    properties.setProperty("qq", userVO.getQq());
                    properties.setProperty("weibo", userVO.getWeibo());
                    properties.setProperty("url", userVO.getUrl());
                    properties.setProperty("avatarUrl", userVO.getAvatarUrl());
                    properties.setProperty("description", userVO.getDescription());
                    properties.setProperty("gender", userVO.getGender());
                    properties.setProperty("cookie", userVO.getCookie());
                    properties.setProperty("gender", userVO.getGender());
                    try {
                        properties.store(context.openFileOutput("sign_log.properties", Context.MODE_PRIVATE), null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return userVO;
    }

    @Override
    public boolean signout(Context context) {
        boolean isLogin = userData.isLogin();
        if (isLogin) {
            userData.setIsLogin(false);
            Properties properties = getLogProperty(context);
            if (properties != null) {
                properties.setProperty("isLogin", "false");
                try {
                    properties.store(context.openFileOutput("sign_log.properties", Context.MODE_PRIVATE), null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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

    @Override
    public boolean preLogin(Context context) {
        Properties properties = getLogProperty(context);
        if (properties != null) {
            String loginState = properties.getProperty("isLogin");
            if (loginState.equals("true")) {
                UserData.getInstance().setIsLogin(true);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public UserVO getSignedUserData(Context context) {
        //TODO 利用GreenDAO从数据库读取已经存取的内容
        //1. 如果没有存就建一个userlog表，列为UserPO中的所有内容
        //2. 如果已经存在就直接拿
        //3. 注销时删除

        Properties properties = getLogProperty(context);
        UserVO userVO = null;
        if (properties != null) {
            userVO = new UserVO(properties.getProperty("id"), properties.getProperty("username"),
                    properties.getProperty("nickname"), properties.getProperty("cookie"),
                    properties.getProperty("gender"), properties.getProperty("email"),
                    properties.getProperty("url"), properties.getProperty("description"),
                    properties.getProperty("signTime"), properties.getProperty("avatarUrl"),
                    properties.getProperty("weibo"), properties.getProperty("qq"));
        }
        return userVO;
    }

    /**
     * 获得登录日志
     *
     * @return 登录日志
     */
    private Properties getLogProperty(Context context) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = context.openFileInput("sign_log.properties");
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
            try {
                FileOutputStream fileOutputStream = context.openFileOutput("sign_log.properties", Context.MODE_PRIVATE);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
                writer.write(getDefaultSignInfo(context));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        }
    }

    private String getDefaultSignInfo(Context context) {
        return "isLogin=false\n" +
                "id=null\n" +
                "username=null\n" +
                "nickname=null\n" +
                "cookie=null\n" +
                "gender=null\n" +
                "email=null\n" +
                "url=null\n" +
                "description=null\n" +
                "signTime=null\n" +
                "avatarUrl=null\n" +
                "weibo=null\n" +
                "qq=null";
    }
}
