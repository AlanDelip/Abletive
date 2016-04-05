package abletive.vo;

/**
 * 类别元素(TagListVO和CategoryListVO)
 * Created by Alan on 2016/4/1.
 */
public class TypeListVO {
    String id;
    String title;
    String description;
    String postCount;

    public TypeListVO(String id, String title, String description, String postCount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.postCount = postCount;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostCount() {
        return postCount;
    }

    public void setPostCount(String postCount) {
        this.postCount = postCount;
    }
}
