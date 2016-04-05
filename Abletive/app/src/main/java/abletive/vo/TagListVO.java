package abletive.vo;

/**
 * 标签列表元素
 * Created by Alan on 2016/4/1.
 */
public class TagListVO {
    String title;
    String description;
    String postCount;

    public TagListVO(String title, String description, String postCount) {
        this.title = title;
        this.description = description;
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

    public String getPostCount() {
        return postCount;
    }

    public void setPostCount(String postCount) {
        this.postCount = postCount;
    }
}
