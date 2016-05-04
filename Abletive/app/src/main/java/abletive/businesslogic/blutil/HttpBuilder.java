package abletive.businesslogic.blutil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import abletive.presentation.uiutil.MApplication;

/**
 * 用于URLConnection添加Parameter的工具类，弥补部分Apache包不能用的缺陷
 *
 * @author Alan
 * @version 1.1
 */
public class HttpBuilder {

    String url;

    public HttpBuilder(String url) {
        this.url = url;
    }

    public HttpBuilder() {
        url = MApplication.getServerIP();
    }

    /**
     * string转utf8编码
     *
     * @param str 字符串
     * @return utf8编码字符串
     */
    public static String string2UTF8(String str) {
        String xmlUTF8 = "";
        try {
            xmlUTF8 = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return xmlUTF8;
    }

    public HttpBuilder addParam(String key, String value) {
        url += key + "=" + value + "&";
        return this;
    }

    public HttpBuilder addParam(String key, int value) {
        return addParam(key, value + "");
    }

    public HttpBuilder addField(String field) {
        return addField(field, true);
    }

    public HttpBuilder addField(String field, boolean isEnd) {
        if (isEnd) {
            url += field + "/?";
        } else {
            url += field + "/";
        }
        return this;
    }

    public String build() {
        if (url.charAt(url.length() - 1) != '?') {
            url = url.substring(0, url.length() - 1);
        }
        return url;
    }

}
