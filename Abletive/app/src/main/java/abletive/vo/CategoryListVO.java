package abletive.vo;

/**
 * 类别元素
 * Created by Alan on 2016/4/1.
 */
public class CategoryListVO {
    String title;
    String description;
    String parent;
    String postCount;

    public CategoryListVO(String title, String description, String parent, String postCount) {
        this.title = title;
        this.description = description;
        this.parent = parent;
        this.postCount = postCount;
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

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getPostCount() {
        return postCount;
    }

    public void setPostCount(String postCount) {
        this.postCount = postCount;
    }
}
