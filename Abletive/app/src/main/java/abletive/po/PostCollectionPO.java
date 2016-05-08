package abletive.po;

import java.util.Map;

/**
 * 文章收藏
 * Created by Alan on 2016/5/5.
 */
public class PostCollectionPO {
    int id;
    String title;
    String excerpt;
    int views;
    String author;
    String date;
    String category;
    String comment_count;
    Map<String, String> thumbnail;

    public PostCollectionPO(int id, String title, String excerpt, int views, String author, String date, String category, String comment_count, Map<String, String> thumbnail) {
        this.id = id;
        this.title = title;
        this.excerpt = excerpt;
        this.views = views;
        this.author = author;
        this.date = date;
        this.category = category;
        this.comment_count = comment_count;
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getThumbnail() {
        //API如是说...
        return thumbnail.get("0");
    }

    public void setThumbnail(Map<String, String> thumbnail) {
        this.thumbnail = thumbnail;
    }
}
