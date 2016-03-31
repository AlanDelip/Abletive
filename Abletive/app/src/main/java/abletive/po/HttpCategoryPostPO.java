package abletive.po;

import java.util.ArrayList;

/**
 * get_category_posts回复
 * Created by Alan on 2016/3/30.
 */
public class HttpCategoryPostPO {
    int count;
    int pages;
    CategoryPO category;
    ArrayList<PostPO> posts;

    public HttpCategoryPostPO(int count, int pages, CategoryPO category, ArrayList<PostPO> posts) {
        this.count = count;
        this.pages = pages;
        this.category = category;
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

    public CategoryPO getCategory() {
        return category;
    }

    public void setCategory(CategoryPO category) {
        this.category = category;
    }

    public ArrayList<PostPO> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<PostPO> posts) {
        this.posts = posts;
    }
}
