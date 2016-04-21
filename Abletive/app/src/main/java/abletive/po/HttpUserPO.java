package abletive.po;

/**
 * generate_auth_cookie
 * Created by Alan on 2016/4/21.
 */
public class HttpUserPO {
    String status;
    String cookie;
    String cookie_name;
    UserPO user;

    public HttpUserPO(String status, String cookie, String cookie_name, UserPO user) {
        this.status = status;
        this.cookie = cookie;
        this.cookie_name = cookie_name;
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getCookie_name() {
        return cookie_name;
    }

    public void setCookie_name(String cookie_name) {
        this.cookie_name = cookie_name;
    }

    public UserPO getUser() {
        return user;
    }

    public void setUser(UserPO user) {
        this.user = user;
    }
}
