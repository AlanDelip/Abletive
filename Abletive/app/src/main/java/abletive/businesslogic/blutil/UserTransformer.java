package abletive.businesslogic.blutil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import abletive.po.HttpUserPO;
import abletive.po.PublicInfoPO;
import abletive.po.SocialInfoPO;
import abletive.po.UserPO;
import abletive.vo.UserVO;

/**
 * 用户相关转换
 * Created by Alan on 2016/4/22.
 */
public class UserTransformer {

    /**
     * UserVO的转换，判断头像连接img标签，相关内容是否为null等
     *
     * @param httpUserPO
     * @return
     */
    public static UserVO getUserVO(HttpUserPO httpUserPO) {
        UserPO userPO = httpUserPO.getUser();
        String avatarUrl = userPO.getAvatar();
        //如果头像url以html的形式给出就进行正则转换
        if (avatarUrl.startsWith("<")) {
            avatarUrl = fetchImg(avatarUrl);
        }
        PublicInfoPO publicInfoPO = userPO.getPublic_info();
        String gender = "";
        if (publicInfoPO != null) {
            gender = publicInfoPO.getGender();
        }
        SocialInfoPO socialInfoPO = userPO.getSocial_info();
        String qq = "", weibo = "";
        if (socialInfoPO != null) {
            qq = socialInfoPO.getQq();
            weibo = socialInfoPO.getWeibo();
        }

        return new UserVO(userPO.getId() + "", userPO.getNickname(), httpUserPO.getCookie(), gender,
                userPO.getEmail(), userPO.getUrl(), userPO.getDescription(), userPO.getRegistered(),
                avatarUrl, qq, weibo);
    }

    /**
     * UserPO转UserVO，负责signup任务后的转换
     *
     * @param userPO
     * @param cookie
     * @return
     */
    public static UserVO getUserVO(UserPO userPO, String cookie) {
        String avatarUrl = userPO.getAvatar();
        //如果头像url以html的形式给出就进行正则转换
        if (avatarUrl.startsWith("<")) {
            avatarUrl = fetchImg(avatarUrl);
        }
        PublicInfoPO publicInfoPO = userPO.getPublic_info();
        String gender = "";
        if (publicInfoPO != null) {
            gender = publicInfoPO.getGender();
        }
        SocialInfoPO socialInfoPO = userPO.getSocial_info();
        String qq = "", weibo = "";
        if (socialInfoPO != null) {
            qq = socialInfoPO.getQq();
            weibo = socialInfoPO.getWeibo();
        }

        return new UserVO(userPO.getId() + "", userPO.getNickname(), cookie, gender,
                userPO.getEmail(), userPO.getUrl(), userPO.getDescription(), userPO.getRegistered(),
                avatarUrl, qq, weibo);
    }

    /**
     * 获取html中img标签中src内容
     *
     * @param htmlStr html代码
     * @return src内容
     */
    private static String fetchImg(String htmlStr) {
        Pattern p = Pattern
                .compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
        // <img[^<>]*src=[\'\"]([0-9A-Za-z.\\/]*)[\'\"].(.*?)>");
        Matcher m = p.matcher(htmlStr);
        if (m.find()) {
            return m.group(1);
        } else {
            return "";
        }
    }
}
