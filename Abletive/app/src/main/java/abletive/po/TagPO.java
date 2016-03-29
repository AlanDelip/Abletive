package abletive.po;

/**
 * 文章标签
 * Created by Alan on 2016/3/12.
 */
public class TagPO {
    int id;
    String slug;
    String title;
    String description;
    int post_count;

    public TagPO(int id, String slug, String title, String description, int post_count) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.post_count = post_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPostCount() {
        return post_count;
    }

    public void setPostCount(int postCount) {
        this.post_count = postCount;
    }
}
