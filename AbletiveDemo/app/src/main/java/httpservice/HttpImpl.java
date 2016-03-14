package httpservice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import data.CategoryPO;
import data.PostPO;
import data.PostTitleVO;
import data.SearchPO;
import data.TagPO;

/**
 * 负责网络传输的类
 * Created by Alan on 2016/3/7.
 */
public class HttpImpl {
    private static final String TAG = "Abletive";
    //TODO 网址放入properties文件
    private String webSite = "http://abletive.com/api/";
    private InputStream inputStream;

    //TODO:逻辑层对界面的接口应该更加简洁
    public HttpImpl(String request, int page) {
        webSite = new HttpBuilder(webSite)
                .addField(request)
                .addParam(InternetAccess.PAGE.name().toLowerCase(), page)
                .build();
    }

    public HttpImpl(String request) {
        webSite = new HttpBuilder(webSite).addField(request).build();
    }

    public HttpImpl() {
    }

    /**
     * 获得文章列表
     *
     * @return 文章列表数组
     */
    public ArrayList<PostTitleVO> getPostTitleList() {

        if (processConnection(webSite)) {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                String content, result = "";
                while ((content = bufferedReader.readLine()) != null) {
                    result += content;
                }

                PostPO postPO = null;
                if (result.length() != 0) {
                    postPO = JSONHandler.getPosts(result);
                }

                ArrayList<PostTitleVO> postTitleList = null;
                if (postPO != null && postPO.getStatus().equals("ok")) {
                    postTitleList = PostTool.getPostTitle(postPO);
                }

                return postTitleList;

            } catch (java.io.IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }

    }

    /**
     * 获得文章简略图
     *
     * @param thumbnailUrl 简略图url
     * @return 文章简略图
     */
    public Bitmap getThumbnail(String thumbnailUrl) {

        if (processConnection(thumbnailUrl)) {
            return BitmapFactory.decodeStream(inputStream);
        } else {
            return null;
        }
    }

    /**
     * 获得文章类型列表
     *
     * @return 文章类型列表
     */
    public ArrayList<CategoryPO> getCategory() {

        if (processConnection(webSite)) {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                String content, result = "";
                while ((content = bufferedReader.readLine()) != null) {
                    result += content;
                }

                ArrayList<CategoryPO> categories = new ArrayList<CategoryPO>();
                if (result.length() != 0) {
                    JSONObject categoryObject = new JSONObject(result);
                    JSONArray categoryArray = categoryObject.getJSONArray("categories");

                    for (int i = 0; i < categoryArray.length(); i++) {
                        JSONObject jsonObject = categoryArray.getJSONObject(i);
                        CategoryPO categoryPO = JSONHandler.getCategory(jsonObject.toString());
                        categories.add(categoryPO);
                    }
                }
                return categories;

            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }

    }

    public ArrayList<TagPO> getTag() {
        if (processConnection(webSite)) {


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                String content, result = "";
                while ((content = bufferedReader.readLine()) != null) {
                    result += content;
                }

                ArrayList<TagPO> tags = new ArrayList<TagPO>();
                if (result.length() != 0) {
                    JSONObject tagObject = new JSONObject(result);
                    JSONArray tagArray = tagObject.getJSONArray("tags");

                    for (int i = 0; i < tagArray.length(); i++) {
                        JSONObject jsonObject = tagArray.getJSONObject(i);
                        TagPO tagPO = JSONHandler.getTag(jsonObject.toString());
                        tags.add(tagPO);
                    }
                }
                return tags;

            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 获得搜索的文章结果
     *
     * @param keyWord 搜索的关键字
     * @param page    页码
     * @return 文章列表
     */
    public ArrayList<PostTitleVO> getResultPosts(String keyWord, int page) {

        webSite = new HttpBuilder(webSite)
                .addParam(InternetAccess.SEARCH.name().toLowerCase(), keyWord)
                .addParam(InternetAccess.PAGE.name().toLowerCase(), page)
                .build();

        Log.d(TAG, "getResultPosts: " + webSite);

        if (processConnection(webSite)) {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                String content, result = "";
                while ((content = bufferedReader.readLine()) != null) {
                    result += content;
                }

                SearchPO searchPO = null;
                if (result.length() != 0) {
                    searchPO = JSONHandler.getSearch(result);
                }

                ArrayList<PostTitleVO> postTitleList = null;
                if (searchPO != null && searchPO.getStatus().equals("ok")) {
                    postTitleList = PostTool.getPostTitle(searchPO);
                }

                return postTitleList;

            } catch (java.io.IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }

    }


    /**
     * 设置默认连接方式
     *
     * @param site 连接的url String
     */
    private boolean processConnection(String site) {

        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(site);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            inputStream = httpURLConnection.getInputStream();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}


