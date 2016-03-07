package httpservice;

import com.google.gson.Gson;

import data.PostPO;

/**
 * Created by Alan on 2016/3/7.
 */
public class JSONHandler {

    public static PostPO getPosts(String postJSONString) {
        Gson gson = new Gson();
        return gson.fromJson(postJSONString, PostPO.class);
    }
}
