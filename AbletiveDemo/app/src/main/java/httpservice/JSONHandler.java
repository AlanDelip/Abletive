package httpservice;

import com.google.gson.Gson;

import data.CategoryPO;
import data.PostPO;
import data.TagPO;

/**
 * Created by Alan on 2016/3/7.
 */
public class JSONHandler {

    public static PostPO getPosts(String postJSONString) {
        Gson gson = new Gson();
        return gson.fromJson(postJSONString, PostPO.class);
    }

    public static CategoryPO getCategory(String categoryJSONString) {
        Gson gson = new Gson();
        return gson.fromJson(categoryJSONString, CategoryPO.class);
    }

    public static TagPO getTag(String tagJSONString) {
        Gson gson = new Gson();
        return gson.fromJson(tagJSONString, TagPO.class);
    }
}
