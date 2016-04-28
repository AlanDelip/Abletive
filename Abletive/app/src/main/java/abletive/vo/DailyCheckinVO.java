package abletive.vo;

import java.util.ArrayList;

/**
 * 签到成功后返回的内容
 * Created by Alan on 2016/4/27.
 */
public class DailyCheckinVO {
    String msg;
    String credits;
    ArrayList<UserVO> userList;

    public DailyCheckinVO(String msg, String credits, ArrayList<UserVO> userList) {
        this.msg = msg;
        this.credits = credits;
        this.userList = userList;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public ArrayList<UserVO> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<UserVO> userList) {
        this.userList = userList;
    }
}
