package abletive.po;

import abletive.vo.UserCommentVO;

/**
 * 个人主页评论列表相关PO
 * Created by Alan on 2016/5/7.
 */
public class UserCommentListPO {
    UserCommentDetailPO detail;
    String link;
    String title;

    public UserCommentListPO(UserCommentDetailPO detail, String link, String title) {
        this.detail = detail;
        this.link = link;
        this.title = title;
    }

    public UserCommentDetailPO getDetail() {
        return detail;
    }

    public void setDetail(UserCommentDetailPO detail) {
        this.detail = detail;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserCommentVO toUserCommentVO() {
        return new UserCommentVO(title, detail.getComment_content(),
                detail.getComment_date(), detail.getComment_agent(),
                detail.getComment_post_ID(), link);
    }

}

