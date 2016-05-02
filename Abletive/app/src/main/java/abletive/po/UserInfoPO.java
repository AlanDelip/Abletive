package abletive.po;

/**
 * 用户其他信息
 * Created by Alan on 2016/4/22.
 */
public class UserInfoPO {
    String ID;//TODO 修改API
    String user_login;
    String user_nicename;
    String user_email;
    String user_url;
    String display_name;

    public UserInfoPO(String ID, String user_login, String user_nicename, String user_email, String user_url, String display_name) {
        this.ID = ID;
        this.user_login = user_login;
        this.user_nicename = user_nicename;
        this.user_email = user_email;
        this.user_url = user_url;
        this.display_name = display_name;
    }

    public String getId() {
        return ID;
    }

    public void setId(String id) {
        this.ID = id;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getUser_nicename() {
        return user_nicename;
    }

    public void setUser_nicename(String user_nicename) {
        this.user_nicename = user_nicename;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_url() {
        return user_url;
    }

    public void setUser_url(String user_url) {
        this.user_url = user_url;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
}
