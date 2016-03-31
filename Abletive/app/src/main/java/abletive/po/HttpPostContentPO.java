package abletive.po;

/**
 * get_post回复
 * Created by Alan on 2016/3/30.
 */
public class HttpPostContentPO {
    PostPO post;
    String previous_url;
    String next_url;

    public HttpPostContentPO(PostPO post, String previous_url, String next_url) {
        this.post = post;
        this.previous_url = previous_url;
        this.next_url = next_url;
    }

    public PostPO getPost() {
        return post;
    }

    public void setPost(PostPO post) {
        this.post = post;
    }

    public String getPrevious_url() {
        return previous_url;
    }

    public void setPrevious_url(String previous_url) {
        this.previous_url = previous_url;
    }

    public String getNext_url() {
        return next_url;
    }

    public void setNext_url(String next_url) {
        this.next_url = next_url;
    }
}
