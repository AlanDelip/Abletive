package abletive.vo;

/**
 * 评论列表VO
 * Created by Alan on 2016/5/7.
 */
public class CommentListVO {
    String avatarUrl;
    String author;
    String parent;
    String parentName;
    String commentHtml;
    String agent;
    String time;
    String id;
    String memberState;

    public CommentListVO(String avatarUrl, String author, String parent, String parentName,String commentHtml, String agent, String time, String id, String memberState) {
        this.avatarUrl = avatarUrl;
        this.author = author;
        this.parent = parent;
        this.parentName = parentName;
        this.commentHtml = commentHtml;
        this.agent = agent;
        this.time = time;
        this.id = id;
        this.memberState = memberState;
    }


    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getCommentHtml() {
        return commentHtml;
    }

    public void setCommentHtml(String commentHtml) {
        this.commentHtml = commentHtml;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberState() {
        return memberState;
    }

    public void setMemberState(String memberState) {
        this.memberState = memberState;
    }
}
