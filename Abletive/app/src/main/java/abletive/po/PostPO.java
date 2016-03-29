package abletive.po;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alan on 2016/3/7.
 */
public class PostPO {
    String status;
    int count;
    int count_total;
    int pages;
    ArrayList<HashMap<String, Object>> posts;
    HashMap<String, Object> query;

    public PostPO(String status, int count, int count_total, int pages, ArrayList<HashMap<String, Object>> posts, HashMap<String, Object> query) {
        this.status = status;
        this.count = count;
        this.count_total = count_total;
        this.pages = pages;
        this.posts = posts;
        this.query = query;
    }

    public HashMap<String, Object> getQuery() {
        return query;
    }

    public void setQuery(HashMap<String, Object> query) {
        this.query = query;
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

    public int getCount_total() {
        return count_total;
    }

    public void setCount_total(int count_total) {
        this.count_total = count_total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
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
