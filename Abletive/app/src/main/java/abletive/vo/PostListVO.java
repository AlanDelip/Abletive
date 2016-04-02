package abletive.vo;

/**
 * 文章列表元素数据
 *
 * @author Alan
 * @version 2.0
 *          Created by Alan on 2016/3/30.
 */
public class PostListVO {
    String title;
    String author;
    String thumbUrl;
    String category;
    String time;
    String views;
    String comments;
    String url;

    /**
     * @param title    文章标题
     * @param author   作者
     * @param thumbUrl    文章缩略图
     * @param category 文章所属目录
     * @param time     发布时间
     * @param views    浏览数
     * @param comments 评论数
     * @param url      url地址
     */
    public PostListVO(String title, String author, String thumbUrl, String category, String time, String views, String comments, String url) {
        this.title = title;
        this.author = author;
        this.thumbUrl = thumbUrl;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
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
