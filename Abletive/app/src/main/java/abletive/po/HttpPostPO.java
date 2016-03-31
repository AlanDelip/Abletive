package abletive.po;

import java.util.ArrayList;

/**
 * get_posts回复
 * Created by Alan on 2016/3/7.
 */
public class HttpPostPO {
    String status;
    int count;
    int count_total;
    int pages;
    ArrayList<PostPO> posts;
    QueryPO query;

    public HttpPostPO(String status, int count, int count_total, int pages, ArrayList<PostPO> posts, QueryPO query) {
        this.status = status;
        this.count = count;
        this.count_total = count_total;
        this.pages = pages;
        this.posts = posts;
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

    public ArrayList<PostPO> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<PostPO> posts) {
        this.posts = posts;
    }

    public QueryPO getQuery() {
        return query;
    }

    public void setQuery(QueryPO query) {
        this.query = query;
    }
}
