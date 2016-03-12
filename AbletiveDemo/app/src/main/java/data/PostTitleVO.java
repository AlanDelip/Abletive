package data;

import android.graphics.Bitmap;

/**
 * 标题
 * Created by Alan on 2016/3/7.
 */
public class PostTitleVO {
    String title;
    String author;
    Bitmap thumb;
    String category;
    String time;
    String views;
    int comments;
    String url;

    /**
     * @param title    文章标题
     * @param author   作者
     * @param thumb    文章缩略图
     * @param category 文章所属目录
     * @param time     发布时间
     * @param views    浏览数
     * @param comments 评论数
     * @param url      url地址
     */
    public PostTitleVO(String title, String author, Bitmap thumb, String category, String time, String views, int comments, String url) {
        this.title = title;
        this.author = author;
        this.thumb = thumb;
        this.category = category;
        this.time = time;
        this.views = views;
        this.comments = comments;
        this.url = url;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public Bitmap getThumb() {
        return thumb;
    }

    public void setThumb(Bitmap thumb) {
        this.thumb = thumb;
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

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
