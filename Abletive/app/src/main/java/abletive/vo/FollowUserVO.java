package abletive.vo;

/**
 * 关注者或粉丝的简略用户信息
 * Created by Alan on 2016/5/2.
 */
public class FollowUserVO {
    String name;
    String id;
    String description;
    String avatarUrl;

    public FollowUserVO(String name, String id, String description, String avatarUrl) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
