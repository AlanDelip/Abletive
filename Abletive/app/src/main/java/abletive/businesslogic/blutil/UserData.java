package abletive.businesslogic.blutil;

import abletive.vo.PersonalPageVO;
import abletive.vo.UserVO;

/**
 * 用户模块的信息存储（单例模式）
 * Created by Alan on 2016/4/20.
 */
public class UserData {

    private static UserData userData = new UserData();
    /**
     * 用户登录后的cookie
     */
    private String cookie;

    /**
     * 当前登录的userID(防止出现未知的加载异常，设置默认ID为开发者的ID34605)
     */
    private String userID = "34605";

    /**
     * 是否已经登录，初始化为未登录
     */
    private boolean isLogin = false;

    /**
     * 目前加载的用户信息
     */
    private UserVO userVO;

    /**
     * 目前加载的个人主页
     */
    private PersonalPageVO personalPageVO;

    private UserData() {
    }

    public static UserData getInstance() {
        return userData;
    }

    public static UserVO getDefaultUserVO() {
        return new UserVO("34605", "", "", "", "", "", "", "", "", "", "", "");
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public PersonalPageVO getPersonalPageVO() {
        return personalPageVO;
    }

    public void setPersonalPageVO(PersonalPageVO personalPageVO) {
        this.personalPageVO = personalPageVO;
    }
}
