package abletive.po;

import abletive.vo.RankVO;

/**
 * 排行榜PO
 * Created by Alan on 2016/5/6.
 */
public class RankPO {
    String name;
    String avatar;
    String user_id;
    String credit;

    public RankPO(String name, String avatar, String user_id, String credit) {
        this.name = name;
        this.avatar = avatar;
        this.user_id = user_id;
        this.credit = credit;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public RankVO toRankVO() {
        return new RankVO(user_id, name, credit, avatar);
    }
}
