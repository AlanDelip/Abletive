package abletive.businesslogic.blutil;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import abletive.po.HttpAuthorPostPO;
import abletive.po.HttpCategoryPO;
import abletive.po.HttpCategoryPostPO;
import abletive.po.HttpDailyCheckinPO;
import abletive.po.HttpDatePostPO;
import abletive.po.HttpPersonalPagePO;
import abletive.po.HttpPostContentPO;
import abletive.po.HttpPostPO;
import abletive.po.HttpSearchPO;
import abletive.po.HttpSignupPO;
import abletive.po.HttpTagPO;
import abletive.po.HttpTagPostPO;
import abletive.po.HttpUserPO;
import abletive.po.UserPO;
import abletive.presentation.uiutil.MApplication;
import alandelip.abletivedemo.R;

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

    public static HttpPersonalPagePO getPersonPage(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, HttpPersonalPagePO.class);
    }

    public static ArrayList<UserPO> getFollowList(String result) {

        ArrayList<UserPO> userPOList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                UserPO userPO = getUserPO(object.toString());
                if (userPO != null && userPO.getStatus().equals(MApplication.getContext().getString(R.string.ok))) {
                    userPOList.add(userPO);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return userPOList;
    }

    public static HttpDailyCheckinPO getCheckinInfo(String result){
        Gson gson = new Gson();
        return gson.fromJson(result,HttpDailyCheckinPO.class);
    }

    public static int getFollowState(String result){
        int followState = 0;
        try {
            JSONObject jsonObject = new JSONObject(result);
            followState = jsonObject.getInt(MApplication.getContext().getString(R.string.success));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return followState;
    }

    public static int getCredit(String result){
        int credit = 0;
        try {
            JSONObject jsonObject = new JSONObject(result);
            credit = jsonObject.getInt(MApplication.getContext().getString(R.string.credit));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return credit;
    }
}
