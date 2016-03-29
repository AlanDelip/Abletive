package abletive.po;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alan on 2016/3/15.
 */
public class PostTitlePO {
    int count;
    ArrayList<HashMap<String, Object>> posts;

    public PostTitlePO(int count, ArrayList<HashMap<String, Object>> posts) {
        this.count = count;
        this.posts = posts;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<HashMap<String, Object>> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<HashMap<String, Object>> posts) {
        this.posts = posts;
    }
}
