package abletive.po;

/**
 * social_info
 * Created by Alan on 2016/4/20.
 */
public class SocialInfoPO {
    String weibo;
    String qq;

    public SocialInfoPO(String weibo, String qq) {
        this.weibo = weibo;
        this.qq = qq;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
