package httpservice;

/**
 * 用于URLConnection添加Parameter的工具类，弥补部分Apache包不能用的缺陷
 * Created by Alan on 2016/3/12.
 */
public class HttpBuilder {

    String url;

    public HttpBuilder(String url) {
        this.url = url;
    }

    public HttpBuilder addParam(String key, String value) {
        url += "?" + key + "=" + value;
        return this;
    }

    public HttpBuilder addParam(String key, int value) {
        return addParam(key, value + "");
    }

    public HttpBuilder addField(String field) {
        url += field + "/";
        return this;
    }

    public String build() {
        return url;
    }

}
