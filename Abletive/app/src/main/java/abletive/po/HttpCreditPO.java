package abletive.po;

import java.util.ArrayList;

/**
 * 积分列表PO
 * Created by Alan on 2016/5/6.
 */
public class HttpCreditPO {
    ArrayList<CreditPO> credit_list;

    public HttpCreditPO(ArrayList<CreditPO> credit_list) {
        this.credit_list = credit_list;
    }

    public ArrayList<CreditPO> getCredit_list() {
        return credit_list;
    }

    public void setCredit_list(ArrayList<CreditPO> credit_list) {
        this.credit_list = credit_list;
    }
}
