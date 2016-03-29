package abletive.po;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alan on 2016/3/15.
 */
public class TagPostPO {
    String status;
    int count;
    int pages;
    TagPO tag;
    ArrayList<HashMap<String, Object>> posts;

    public TagPostPO(String status, int count, int pages, TagPO tag, ArrayList<HashMap<String, Object>> posts) {
        this.status = status;
        this.count = count;
        this.pages = pages;
        this.tag = tag;
        this.posts = posts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public TagPO getTag() {
        return tag;
    }

    public void setTag(TagPO tag) {
        this.tag = tag;
    }

    public ArrayList<HashMap<String, Object>> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<HashMap<String, Object>> posts) {
        this.posts = posts;
    }

    public PostTitlePO toPostTitlePO() {
        return new PostTitlePO(count, posts);
    }
}
