package abletive.po;

/**
 * 置顶文章相关数据
 * Created by Alan on 2016/3/30.
 */
public class QueryPO {
    int count;
    boolean ignore_sticky_posts;

    public QueryPO(int count, boolean ignore_sticky_posts) {
        this.count = count;
        this.ignore_sticky_posts = ignore_sticky_posts;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isIgnore_sticky_posts() {
        return ignore_sticky_posts;
    }

    public void setIgnore_sticky_posts(boolean ignore_sticky_posts) {
        this.ignore_sticky_posts = ignore_sticky_posts;
    }
}
