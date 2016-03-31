package abletive.businesslogic.blutil;

import com.google.gson.Gson;

import abletive.po.CategoryPO;
import abletive.po.HttpPostContentPO;
import abletive.po.HttpPostPO;
import abletive.po.SearchPO;
import abletive.po.TagPO;
import abletive.po.TagPostPO;

/**
 * JSON字符串处理逻辑
 *
 * @author Alan
 */
public class JSONHandler {

    public static HttpPostPO getPosts(String postJSONString) {
        Gson gson = new Gson();
        return gson.fromJson(postJSONString, HttpPostPO.class);
    }

    public static HttpPostContentPO getPost(String postJSONString) {
        Gson gson = new Gson();
        return gson.fromJson(postJSONString, HttpPostContentPO.class);
    }

    public static CategoryPO getCategory(String categoryJSONString) {
        Gson gson = new Gson();
        return gson.fromJson(categoryJSONString, CategoryPO.class);
    }

    public static TagPO getTag(String tagJSONString) {
        Gson gson = new Gson();
        return gson.fromJson(tagJSONString, TagPO.class);
    }

    public static SearchPO getSearch(String searchJSONString) {
        Gson gson = new Gson();
        return gson.fromJson(searchJSONString, SearchPO.class);
    }

    public static TagPostPO getTagPost(String tagPostJSONString) {
        Gson gson = new Gson();
        return gson.fromJson(tagPostJSONString, TagPostPO.class);
    }
}
