package abletive.businesslogic.blutil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
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

        return new UserVO(userPO.getId() + "", userPO.getUsername(), userPO.getNickname(), httpUserPO.getCookie(), gender,
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
        String gender = "未设置";
        if (publicInfoPO != null) {
            gender = publicInfoPO.getGender();
        }
        SocialInfoPO socialInfoPO = userPO.getSocial_info();
        String qq = "", weibo = "";
        if (socialInfoPO != null) {
            qq = socialInfoPO.getQq();
            weibo = socialInfoPO.getWeibo();
        }

        return new UserVO(userPO.getId() + "", userPO.getUsername(), userPO.getNickname(), cookie, gender,
                userPO.getEmail(), userPO.getUrl(), userPO.getDescription(), userPO.getRegistered(),
                avatarUrl, qq, weibo);
    }

    /**
     * UserPO数组转UserVO数组
     *
     * @param userPOList
     * @return
     */
    public static ArrayList<UserVO> getUserVOList(ArrayList<UserPO> userPOList) {
        ArrayList<UserVO> userVOList = new ArrayList<>();
        if (userPOList != null) {
            for (UserPO userPO : userPOList) {
                //暂时设置cookie为空字符串
                userVOList.add(getUserVO(userPO, ""));
            }
        }
        return userVOList;
    }

    /**
     * 获取html中img标签中src内容
     *
     * @param htmlStr html代码
     * @return src内容
     */
    public static String fetchImg(String htmlStr) {
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

    /**
     * 对地址是html编码的进行转换，并对含有中文字符的地址进行URLEncoding
     *
     * @param content 原始字符串
     * @return 转换后的字符串
     */
    public static String transfer(String content) {
        //如果是html编码就进行正则提取img内容
        if (content.startsWith("<")) {
            content = fetchImg(content);
        }

        String result = "";
        //单个字符进行查看，如果是中文字符就进行URLEncoding
        for (int i = 0; i < content.length(); i++) {
            String strAtI = content.substring(i, i + 1);
            //中文字符
            if (isChineseChar(strAtI)) {
                try {
                    strAtI = URLEncoder.encode(strAtI, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            result += strAtI;
        }
        return result;
    }

    /**
     * 判断是否是中文字符
     *
     * @param str 传入字符
     * @return 是否是中文
     */
    public static boolean isChineseChar(CharSequence str) {
        boolean temp = false;
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            temp = true;
        }
        return temp;
    }
}
