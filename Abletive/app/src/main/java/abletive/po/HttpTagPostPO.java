package abletive.po;

import java.util.ArrayList;

/**
 * get_tag_posts回复
 * Created by Alan on 2016/3/30.
 */
public class HttpTagPostPO {
    int count;
    int pages;
    TagPO tag;
    ArrayList<PostPO> posts;

    public HttpTagPostPO(int count, int pages, TagPO tag, ArrayList<PostPO> posts) {
        this.count = count;
        this.pages = pages;
        this.tag = tag;
        this.posts = posts;
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

    public ArrayList<PostPO> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<PostPO> posts) {
        this.posts = posts;
    }
}
