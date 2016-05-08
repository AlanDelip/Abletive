package abletive.po;

import java.util.ArrayList;

/**
 * 个人主页评论PO
 * Created by Alan on 2016/5/7.
 */
public class HttpUserCommentPO {
    int count;
    ArrayList<UserCommentListPO> comment_list;

    public HttpUserCommentPO(int count, ArrayList<UserCommentListPO> comment_list) {
        this.count = count;
        this.comment_list = comment_list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<UserCommentListPO> getComment_list() {
        return comment_list;
    }

    public void setComment_list(ArrayList<UserCommentListPO> comment_list) {
        this.comment_list = comment_list;
    }
}
