package abletive.po;

/**
 * register
 * Created by Alan on 2016/4/21.
 */
public class HttpSignupPO {
    String cookie;
    int user_id;

    public HttpSignupPO(String cookie, int user_id) {
        this.cookie = cookie;
        this.user_id = user_id;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
