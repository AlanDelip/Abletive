package abletive.vo;

/**
 * 积分排行列表
 *
 * @author Alan
 */
public class RankVO {
    String userID;
    String userName;
    String credit;
    String avatarUrl;

    public RankVO(String userID, String userName, String credit, String avatarUrl) {
        this.userID = userID;
        this.userName = userName;
        this.credit = credit;
        this.avatarUrl = avatarUrl;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
