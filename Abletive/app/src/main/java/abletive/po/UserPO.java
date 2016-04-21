package abletive.po;

/**
 * 用户数据
 *
 * @author Alan
 */
public class UserPO {
    String status;
    int id;
    String username;
    String nicename;
    String email;
    String url;
    String registered;
    String nickname;
    String description;
    String avatar;
    PublicInfoPO public_info;
    SocialInfoPO social_info;
    PrivateInfoPO private_info;

    public UserPO(int id, String username, String nicename, String email, String url, String registered, String nickname, String description, String avatar, PublicInfoPO public_info, SocialInfoPO social_info, PrivateInfoPO private_info) {
        this.id = id;
        this.username = username;
        this.nicename = nicename;
        this.email = email;
        this.url = url;
        this.registered = registered;
        this.nickname = nickname;
        this.description = description;
        this.avatar = avatar;
        this.public_info = public_info;
        this.social_info = social_info;
        this.private_info = private_info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNicename() {
        return nicename;
    }

    public void setNicename(String nicename) {
        this.nicename = nicename;
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

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public PublicInfoPO getPublic_info() {
        return public_info;
    }

    public void setPublic_info(PublicInfoPO public_info) {
        this.public_info = public_info;
    }

    public SocialInfoPO getSocial_info() {
        return social_info;
    }

    public void setSocial_info(SocialInfoPO social_info) {
        this.social_info = social_info;
    }

    public PrivateInfoPO getPrivate_info() {
        return private_info;
    }

    public void setPrivate_info(PrivateInfoPO private_info) {
        this.private_info = private_info;
    }

    public String getStatus() {
        return status;
    }
}
