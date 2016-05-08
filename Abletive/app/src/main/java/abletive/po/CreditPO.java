package abletive.po;

import abletive.vo.CreditListVO;

/**
 * 积分PO
 * Created by Alan on 2016/5/6.
 */
public class CreditPO {
    String date;
    String content;

    public CreditPO(String date, String content) {
        this.date = date;
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CreditListVO toCreditListVO() {
        return new CreditListVO(date, content);
    }
}
