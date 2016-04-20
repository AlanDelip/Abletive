package abletive.po;

import java.util.ArrayList;
import java.util.Map;

/**
 * 文章内容对象，文章列表不包含content
 *
 * @author Alan
 */
public class PostPO {
    int id;
    String type;
    String slug;
    String url;
    String status;
    String title;
    String title_plain;
    String content;
    String excerpt;
    String date;
    ArrayList<CategoryPO> categories;
    ArrayList<TagPO> tags;
    AuthorPO author;
    ArrayList<Map<Object, Object>> comments;
    int comment_count;
    String comment_status;
    CustomFieldsPO custom_fields;
    ThumbnailImagePO thumbnail_images;

    public PostPO(int id, String type, String slug, String url, String status, String title, String title_plain, String content, String excerpt, String date, ArrayList<CategoryPO> categories, ArrayList<TagPO> tags, AuthorPO author, ArrayList<Map<Object, Object>> comments, int comment_count, String comment_status, CustomFieldsPO custom_fields, ThumbnailImagePO thumbnail_images) {
        this.id = id;
        this.type = type;
        this.slug = slug;
        this.url = url;
        this.status = status;
        this.title = title;
        this.title_plain = title_plain;
        this.content = content;
        this.excerpt = excerpt;
        this.date = date;
        this.categories = categories;
        this.tags = tags;
        this.author = author;
        this.comments = comments;
        this.comment_count = comment_count;
        this.comment_status = comment_status;
        this.custom_fields = custom_fields;
        this.thumbnail_images = thumbnail_images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_plain() {
        return title_plain;
    }

    public void setTitle_plain(String title_plain) {
        this.title_plain = title_plain;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<CategoryPO> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<CategoryPO> categories) {
        this.categories = categories;
    }

    public ArrayList<TagPO> getTags() {
        return tags;
    }

    public void setTags(ArrayList<TagPO> tags) {
        this.tags = tags;
    }

    public AuthorPO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorPO author) {
        this.author = author;
    }

    public ArrayList<Map<Object, Object>> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Map<Object, Object>> comments) {
        this.comments = comments;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public String getComment_status() {
        return comment_status;
    }

    public void setComment_status(String comment_status) {
        this.comment_status = comment_status;
    }

    public CustomFieldsPO getCustom_fields() {
        return custom_fields;
    }

    public void setCustom_fields(CustomFieldsPO custom_fields) {
        this.custom_fields = custom_fields;
    }

    public ThumbnailImagePO getThumbnail_image() {
        return thumbnail_images;
    }

    public void setThumbnail_image(ThumbnailImagePO thumbnail_images) {
        this.thumbnail_images = thumbnail_images;
    }
}
