package abletive.vo;

import java.util.ArrayList;

/**
 * 文章内容
 *
 * @author Alan
 */
public class PostVO {
    String title;
    String viewsNum;
    String commentsNum;
    String category;
    String time;
    String contentHtml;
    String likesNum;
    String collectsNum;
    String authorID;
    String authorAvatarUrl;
    String url;
    ArrayList<CommentListVO> commentList;

    public PostVO(String title, String viewsNum, String commentsNum, String category, String time, String contentHtml, String likesNum, String collectsNum, String authorID, String authorAvatarUrl, String url,ArrayList<CommentListVO> commentList) {
        this.title = title;
        this.viewsNum = viewsNum;
        this.commentsNum = commentsNum;
        this.category = category;
        this.time = time;
        this.contentHtml = contentHtml;
        this.likesNum = likesNum;
        this.collectsNum = collectsNum;
        this.authorID = authorID;
        this.authorAvatarUrl = authorAvatarUrl;
        this.url = url;
        this.commentList = commentList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getViewsNum() {
        return viewsNum;
    }

    public void setViewsNum(String viewsNum) {
        this.viewsNum = viewsNum;
    }

    public String getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(String commentsNum) {
        this.commentsNum = commentsNum;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(String likesNum) {
        this.likesNum = likesNum;
    }

    public String getCollectsNum() {
        return collectsNum;
    }

    public void setCollectsNum(String collectsNum) {
        this.collectsNum = collectsNum;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getAuthorAvatarUrl() {
        return authorAvatarUrl;
    }

    public void setAuthorAvatarUrl(String authorAvatarUrl) {
        this.authorAvatarUrl = authorAvatarUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<CommentListVO> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<CommentListVO> commentList) {
        this.commentList = commentList;
    }
}
