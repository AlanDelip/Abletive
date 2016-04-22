package abletive.po;

/**
 * 会员信息
 * Created by Alan on 2016/4/22.
 */
public class MembershipPO {
    int id;
    int user_id;
    String user_type;
    String startTime;
    String endTime;
    String user_status;

    public MembershipPO(int id, int user_id, String user_type, String startTime, String endTime, String user_status) {
        this.id = id;
        this.user_id = user_id;
        this.user_type = user_type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user_status = user_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
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

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }
}
