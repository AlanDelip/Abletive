package abletive.po;

import java.util.ArrayList;

/**
 * get_author_posts回复
 * Created by Alan on 2016/3/30.
 */
public class HttpAuthorPO {
    int count;
    int pages;
    AuthorPO author;
    ArrayList<PostPO> posts;

    public HttpAuthorPO(int count, int pages, AuthorPO author, ArrayList<PostPO> posts) {
        this.count = count;
        this.pages = pages;
        this.author = author;
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

    public AuthorPO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorPO author) {
        this.author = author;
    }

    public ArrayList<PostPO> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<PostPO> posts) {
        this.posts = posts;
    }
}
