package abletive.po;

/**
 * 用户评论
 * Created by Alan on 2016/5/7.
 */
public class UserCommentDetailPO {
    String comment_ID;
    String comment_post_ID;
    String comment_author;
    String comment_author_email;
    String comment_author_IP;
    String comment_date;
    String comment_content;
    String comment_agent;
    String comment_parent;
    String user_id;

    public UserCommentDetailPO(String comment_ID, String comment_post_ID, String comment_author, String comment_author_email, String comment_author_IP, String comment_date, String comment_content, String comment_agent, String comment_parent, String user_id) {
        this.comment_ID = comment_ID;
        this.comment_post_ID = comment_post_ID;
        this.comment_author = comment_author;
        this.comment_author_email = comment_author_email;
        this.comment_author_IP = comment_author_IP;
        this.comment_date = comment_date;
        this.comment_content = comment_content;
        this.comment_agent = comment_agent;
        this.comment_parent = comment_parent;
        this.user_id = user_id;
    }

    public String getComment_ID() {
        return comment_ID;
    }

    public void setComment_ID(String comment_ID) {
        this.comment_ID = comment_ID;
    }

    public String getComment_post_ID() {
        return comment_post_ID;
    }

    public void setComment_post_ID(String comment_post_ID) {
        this.comment_post_ID = comment_post_ID;
    }

    public String getComment_author() {
        return comment_author;
    }

    public void setComment_author(String comment_author) {
        this.comment_author = comment_author;
    }

    public String getComment_author_email() {
        return comment_author_email;
    }

    public void setComment_author_email(String comment_author_email) {
        this.comment_author_email = comment_author_email;
    }

    public String getComment_author_IP() {
        return comment_author_IP;
    }

    public void setComment_author_IP(String comment_author_IP) {
        this.comment_author_IP = comment_author_IP;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getComment_agent() {
        return comment_agent;
    }

    public void setComment_agent(String comment_agent) {
        this.comment_agent = comment_agent;
    }

    public String getComment_parent() {
        return comment_parent;
    }

    public void setComment_parent(String comment_parent) {
        this.comment_parent = comment_parent;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
