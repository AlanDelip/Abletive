package abletive.vo;

import abletive.po.UserInfoPO;

/**
 * 个人主页信息
 * Created by Alan on 2016/4/29.
 */
public class PersonalPageVO {

    String avatarUrl;
    String followState;
    String postCount;
    String postComment;
    String credits;
    String collectionCount;
    MemberVO memberVO;
    String gender;
    String registerTime;
    String shopOrders;
    UserInfoPO userInfoPO;

    public PersonalPageVO(String avatarUrl, String followState, String postCount, String postComment, String credits, String collectionCount, MemberVO memberVO, String gender, String registerTime, String shopOrders, UserInfoPO userInfoPO) {
        this.avatarUrl = avatarUrl;
        this.followState = followState;
        this.postCount = postCount;
        this.postComment = postComment;
        this.credits = credits;
        this.collectionCount = collectionCount;
        this.memberVO = memberVO;
        this.gender = gender;
        this.registerTime = registerTime;
        this.shopOrders = shopOrders;
        this.userInfoPO = userInfoPO;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getFollowState() {
        return followState;
    }

    public void setFollowState(String followState) {
        this.followState = followState;
    }

    public String getPostCount() {
        return postCount;
    }

    public void setPostCount(String postCount) {
        this.postCount = postCount;
    }

    public String getPostComment() {
        return postComment;
    }

    public void setPostComment(String postComment) {
        this.postComment = postComment;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(String collectionCount) {
        this.collectionCount = collectionCount;
    }

    public MemberVO getMemberVO() {
        return memberVO;
    }

    public void setMemberVO(MemberVO memberVO) {
        this.memberVO = memberVO;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getShopOrders() {
        return shopOrders;
    }

    public void setShopOrders(String shopOrders) {
        this.shopOrders = shopOrders;
    }

    public UserInfoPO getUserInfoPO() {
        return userInfoPO;
    }

    public void setUserInfoPO(UserInfoPO userInfoPO) {
        this.userInfoPO = userInfoPO;
    }
}
