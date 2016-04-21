package abletive.po;

/**
 * public_info
 * Created by Alan on 2016/4/20.
 */
public class PublicInfoPO {
    String avatar;
    String gender;

    public PublicInfoPO(String avatar, String gender) {
        this.avatar = avatar;
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
