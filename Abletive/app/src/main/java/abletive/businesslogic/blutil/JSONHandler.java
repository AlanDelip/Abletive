package abletive.businesslogic.blutil;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import abletive.po.HttpAuthorPostPO;
import abletive.po.HttpCategoryPO;
import abletive.po.HttpCategoryPostPO;
import abletive.po.HttpDatePostPO;
import abletive.po.HttpPostContentPO;
import abletive.po.HttpPostPO;
import abletive.po.HttpSearchPO;
import abletive.po.HttpSignupPO;
import abletive.po.HttpTagPO;
import abletive.po.HttpTagPostPO;
import abletive.po.HttpUserPO;
import abletive.po.UserPO;

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

    public static HttpCategoryPO getCategory(String categoryJSONString) {
        Gson gson = new Gson();
        return gson.fromJson(categoryJSONString, HttpCategoryPO.class);
    }

    public static HttpTagPO getTag(String tagJSONString) {
        Gson gson = new Gson();
        return gson.fromJson(tagJSONString, HttpTagPO.class);
    }

    public static HttpSearchPO getSearch(String searchJSONString) {
        Gson gson = new Gson();
        return gson.fromJson(searchJSONString, HttpSearchPO.class);
    }

    public static HttpTagPostPO getTagPost(String tagPostJSONString) {
        Gson gson = new Gson();
        return gson.fromJson(tagPostJSONString, HttpTagPostPO.class);
    }

    public static HttpCategoryPostPO getCategoryPost(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, HttpCategoryPostPO.class);
    }

    public static HttpDatePostPO getDatePost(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, HttpDatePostPO.class);
    }

    public static HttpAuthorPostPO getAuthorPost(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, HttpAuthorPostPO.class);
    }

    public static HttpUserPO getHttpUserPO(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, HttpUserPO.class);
    }

    public static UserPO getUserPO(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, UserPO.class);
    }

    public static String getNonce(String nonceJSON) {
        String nonce = "";
        try {
            JSONObject jsonObject = new JSONObject(nonceJSON);
            nonce = jsonObject.getString("nonce");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return nonce;
    }

    public static HttpSignupPO getSignup(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, HttpSignupPO.class);
    }
}
