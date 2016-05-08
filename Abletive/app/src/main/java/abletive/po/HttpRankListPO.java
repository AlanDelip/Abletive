package abletive.po;

import java.util.ArrayList;

/**
 * ranklist的网络回复
 * Created by Alan on 2016/5/6.
 */
public class HttpRankListPO {
    ArrayList<RankPO> credit_list;

    public HttpRankListPO(ArrayList<RankPO> credit_list) {
        this.credit_list = credit_list;
    }

    public ArrayList<RankPO> getCredit_list() {
        return credit_list;
    }

    public void setCredit_list(ArrayList<RankPO> credit_list) {
        this.credit_list = credit_list;
    }
}
