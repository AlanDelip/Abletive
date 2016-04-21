package abletive.po;

/**
 * private_info
 * Created by Alan on 2016/4/20.
 */
public class PrivateInfoPO {
    String login;
    String email;

    public PrivateInfoPO(String login, String email) {
        this.login = login;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
