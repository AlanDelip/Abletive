package abletive.vo;

/**
 * 用户信息
 *
 * @author Alan
 */
public class UserVO {
    String id;
    String nickname;
    String cookie;
    String gender;
    String email;
    String url;
    String description;
    String signTime;
    String avatarUrl;
    String weibo;
    String qq;

    public UserVO(String id, String nickname, String cookie, String gender, String email, String url, String description, String signTime, String avatarUrl, String weibo, String qq) {
        this.id = id;
        this.nickname = nickname;
        this.cookie = cookie;
        this.gender = gender;
        this.email = email;
        this.url = url;
        this.description = description;
        this.signTime = signTime;
        this.avatarUrl = avatarUrl;
        this.weibo = weibo;
        this.qq = qq;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
