package abletive.po;

/**
 * 个人主页信息
 * Created by Alan on 2016/4/22.
 */
public class HttpPersonalPagePO {
    String avatar;
    int follow_status;
    String following_count;
    String follower_count;
    int posts_count;
    int comments_count;
    int credit;
    String gender;
    int registered_days;
    MembershipPO membership;
    int orders_count;
    UserInfoPO user_info;

    public HttpPersonalPagePO(String avatar, int follow_status, String following_count, String follower_count, int posts_count, int comments_count, int credit, String gender, int registered_days, MembershipPO membership, int orders_count, UserInfoPO user_info) {
        this.avatar = avatar;
        this.follow_status = follow_status;
        this.following_count = following_count;
        this.follower_count = follower_count;
        this.posts_count = posts_count;
        this.comments_count = comments_count;
        this.credit = credit;
        this.gender = gender;
        this.registered_days = registered_days;
        this.membership = membership;
        this.orders_count = orders_count;
        this.user_info = user_info;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getFollow_status() {
        return follow_status;
    }

    public void setFollow_status(int follow_status) {
        this.follow_status = follow_status;
    }

    public String getFollowing_count() {
        return following_count;
    }

    public void setFollowing_count(String following_count) {
        this.following_count = following_count;
    }

    public String getFollower_count() {
        return follower_count;
    }

    public void setFollower_count(String follower_count) {
        this.follower_count = follower_count;
    }

    public int getPosts_count() {
        return posts_count;
    }

    public void setPosts_count(int posts_count) {
        this.posts_count = posts_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getRegistered_days() {
        return registered_days;
    }

    public void setRegistered_days(int registered_days) {
        this.registered_days = registered_days;
    }

    public MembershipPO getMembership() {
        return membership;
    }

    public void setMembership(MembershipPO membership) {
        this.membership = membership;
    }

    public int getOrders_count() {
        return orders_count;
    }

    public void setOrders_count(int orders_count) {
        this.orders_count = orders_count;
    }

    public UserInfoPO getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoPO user_info) {
        this.user_info = user_info;
    }
}
