package data;

import android.graphics.Bitmap;

/**
 * 标题
 * Created by Alan on 2016/3/7.
 */
public class PostTitle {
    String title;
    String author;
    Bitmap avatar;
    String time;
    String viewerNum;
    int commentNum;
    String url;

    public PostTitle(String title, String author, Bitmap avatar, String time, String viewerNum, int commentNum, String url) {
        this.title = title;
        this.author = author;
        this.avatar = avatar;
        this.time = time;
        this.viewerNum = viewerNum;
        this.commentNum = commentNum;
        this.url = url;

    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getViewerNum() {
        return viewerNum;
    }

    public void setViewerNum(String viewerNum) {
        this.viewerNum = viewerNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
