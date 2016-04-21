package abletive.vo;

/**
 * 注册信息
 *
 * @author Alan
 */
public class SignupVO {
    String username;
    String userpass;
    String email;
    String displayname;

    public SignupVO(String username, String userpass, String email, String displayname) {
        this.username = username;
        this.userpass = userpass;
        this.email = email;
        this.displayname = displayname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }
}
