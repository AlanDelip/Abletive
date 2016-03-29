package data;

/**
 * 文章类别
 * Created by Alan on 2016/3/12.
 */
public class CategoryPO {
    int id;
    String slug;
    String title;
    String description;
    int parent;
    int postCount;

    /**
     * @param id          分类唯一ID
     * @param slug        分类唯一URI
     * @param title       分类标题
     * @param description 分类概述
     * @param parent      父类
     * @param postCount   分类文章篇数
     */
    public CategoryPO(int id, String slug, String title, String description, int parent, int postCount) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.parent = parent;
        this.postCount = postCount;
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

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }
}
