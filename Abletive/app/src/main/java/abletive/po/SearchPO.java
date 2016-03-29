package abletive.po;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 文章搜索结果
 * Created by Alan on 2016/3/14.
 */
public class SearchPO {
    String status;
    int count;
    int pages;
    int count_total;
    ArrayList<HashMap<String, Object>> posts;

    public SearchPO(String status, int count, int pages, int count_total, ArrayList<HashMap<String, Object>> posts) {
        this.status = status;
        this.count = count;

        this.pages = pages;
        this.count_total = count_total;
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

    public int getCountTotal() {
        return count_total;
    }

    public void setCountTotal(int countTotal) {
        this.count_total = count_total;
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
