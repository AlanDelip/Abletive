package abletive.vo;

/**
 * 积分列表VO
 * Created by Alan on 2016/5/6.
 */
public class CreditListVO {
    String date;
    String content;

    public CreditListVO(String date, String content) {
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
}
