package abletive.po;

import abletive.vo.FollowUserVO;

/**
 * 关注者和粉丝的用户信息
 * Created by Alan on 2016/5/2.
 */
public class FollowUserPO {
    String name;
    String avatar;
    String id;
    String gender;
    String membership;
    String description;

    public FollowUserPO(String name, String avatar, String id, String gender, String membership, String description) {
        this.name = name;
        this.avatar = avatar;
        this.id = id;
        this.gender = gender;
        this.membership = membership;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FollowUserVO toFollowUserVO() {
        return new FollowUserVO(name, id, description, avatar);
    }
}
