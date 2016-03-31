package abletive.po;

/**
 * 文章其他信息
 */
public class CustomFieldsPO {
    int views;
    int um_post_likes;
    int um_post_collects;

    /**
     * @param views            浏览人数
     * @param um_post_likes    点赞次数
     * @param um_post_collects 文章收藏人数
     */
    public CustomFieldsPO(int views, int um_post_likes, int um_post_collects) {
        this.views = views;
        this.um_post_likes = um_post_likes;
        this.um_post_collects = um_post_collects;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getUm_post_likes() {
        return um_post_likes;
    }

    public void setUm_post_likes(int um_post_likes) {
        this.um_post_likes = um_post_likes;
    }

    public int getUm_post_collects() {
        return um_post_collects;
    }

    public void setUm_post_collects(int um_post_collects) {
        this.um_post_collects = um_post_collects;
    }
}
