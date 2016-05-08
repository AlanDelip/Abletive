package abletive.vo;

/**
 * 个人主页评论列表内容
 * Created by Alan on 2016/5/7.
 */
public class UserCommentVO {
    String postTitle;
    String comment;
    String time;
    String agent;
    String postID;
    String postLink;

    public UserCommentVO(String postTitle, String comment, String time, String agent, String postID, String postLink) {
        this.postTitle = postTitle;
        this.comment = comment;
        this.time = time;
        this.agent = agent;
        this.postID = postID;
        this.postLink = postLink;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getPostLink() {
        return postLink;
    }

    public void setPostLink(String postLink) {
        this.postLink = postLink;
    }

}
