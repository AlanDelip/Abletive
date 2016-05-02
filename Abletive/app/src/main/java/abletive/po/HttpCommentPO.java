package abletive.po;

/**
 * submit_comment回复
 * Created by Alan on 2016/5/1.
 */
public class HttpCommentPO {
    String status;
    int comment_id;
    int parent;
    String comment_date;

    public HttpCommentPO(String status, int comment_id, int parent, String comment_date) {
        this.status = status;
        this.comment_id = comment_id;
        this.parent = parent;
        this.comment_date = comment_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }
}
