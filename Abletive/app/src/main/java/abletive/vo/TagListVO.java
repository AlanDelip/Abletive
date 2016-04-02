package abletive.vo;

/**
 * 标签列表元素
 * Created by Alan on 2016/4/1.
 */
public class TagListVO {
    String title;
    String description;
    int postCount;

    public TagListVO(String title, String description, int postCount) {
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

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }
}
