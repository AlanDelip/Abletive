package abletive.vo;

/**
 * 收藏
 * Created by Alan on 2016/5/5.
 */
public class PostCollectionVO {
    String id;
    String title;
    String author;
    String thumbUrl;
    String category;
    String time;
    String views;
    String comments;
    String excerpt;

    public PostCollectionVO(String id, String title, String author, String thumbUrl, String category, String time, String views, String comments,String excerpt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.thumbUrl = thumbUrl;
        this.category = category;
        this.time = time;
        this.views = views;
        this.comments = comments;
        this.excerpt = excerpt;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
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

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
