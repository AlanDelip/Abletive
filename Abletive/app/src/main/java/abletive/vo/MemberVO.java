package abletive.vo;

/**
 * 会员内容
 *
 * @author Alan
 */
public class MemberVO {
    String memberID;
    String userType;
    String startTime;
    String endTime;
    String userStatus;

    public MemberVO(String memberID, String userType, String startTime, String endTime, String userStatus) {
        this.memberID = memberID;
        this.userType = userType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userStatus = userStatus;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
