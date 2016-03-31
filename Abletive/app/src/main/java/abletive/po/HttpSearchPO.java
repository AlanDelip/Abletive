package abletive.po;

import java.util.ArrayList;

/**
 * get_search_results回复
 * Created by Alan on 2016/3/30.
 */
public class HttpSearchPO {
    int count;
    int pages;
    int count_total;
    ArrayList<PostPO> posts;

    public HttpSearchPO(int count, int pages, int count_total, ArrayList<PostPO> posts) {
        this.count = count;
        this.pages = pages;
        this.count_total = count_total;
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

    public int getCount_total() {
        return count_total;
    }

    public void setCount_total(int count_total) {
        this.count_total = count_total;
    }

    public ArrayList<PostPO> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<PostPO> posts) {
        this.posts = posts;
    }
}
