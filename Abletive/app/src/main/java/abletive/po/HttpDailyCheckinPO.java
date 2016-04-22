package abletive.po;

import java.util.ArrayList;

/**
 * 签到信息
 * Created by Alan on 2016/4/22.
 */
public class HttpDailyCheckinPO {
    String msg;
    int success;
    int credits;
    ArrayList<UserPO> checkin_list;

    public HttpDailyCheckinPO(String msg, int success, int credits, ArrayList<UserPO> checkin_list) {
        this.msg = msg;
        this.success = success;
        this.credits = credits;
        this.checkin_list = checkin_list;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public ArrayList<UserPO> getCheckin_list() {
        return checkin_list;
    }

    public void setCheckin_list(ArrayList<UserPO> checkin_list) {
        this.checkin_list = checkin_list;
    }
}
