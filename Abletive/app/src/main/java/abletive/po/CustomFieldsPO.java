package abletive.po;

import java.util.ArrayList;

/**
 * 文章其他信息
 */
public class CustomFieldsPO {
    ArrayList<String> views;
    ArrayList<String> um_post_likes;
    ArrayList<String> um_post_collects;

    /**
     * @param views            浏览人数
     * @param um_post_likes    点赞次数
     * @param um_post_collects 文章收藏人数
     */
    public CustomFieldsPO(ArrayList<String> views, ArrayList<String> um_post_likes, ArrayList<String> um_post_collects) {
        this.views = views;
        this.um_post_likes = um_post_likes;
        this.um_post_collects = um_post_collects;
    }

    public int getViews() {
        return Integer.parseInt(views.get(0));
    }

    public void setViews(ArrayList<String> views) {
        this.views = views;
    }

    public int getUm_post_likes() {
        if (um_post_likes != null)
            return Integer.parseInt(um_post_likes.get(0));
        else
            return 0;
    }

    public void setUm_post_likes(ArrayList<String> um_post_likes) {
        this.um_post_likes = um_post_likes;
    }

    public int getUm_post_collects() {
        if (um_post_collects != null) {
            return Integer.parseInt(um_post_collects.get(0));
        } else {
            return 0;
        }
    }

    public void setUm_post_collects(ArrayList<String> um_post_collects) {
        this.um_post_collects = um_post_collects;
    }
}
