package abletive.businesslogic.blutil;

/**
 * 用户模块的信息存储（单例模式）
 * Created by Alan on 2016/4/20.
 */
public class UserData {

    private static UserData userData = new UserData();
    /**
     * 用户名
     */
    private String userName;
    /**
     * 是否已经登录，初始化为未登录
     */
    private boolean isLogin = false;

    private UserData() {
    }

    public static UserData getInstance() {
        return userData;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }
}
