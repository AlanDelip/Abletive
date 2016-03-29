package httpservice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import abletive.businesslogic.blutil.HttpBuilder;
import abletive.businesslogic.blutil.InternetAccess;
import abletive.businesslogic.blutil.JSONHandler;
import abletive.po.CategoryPO;
import abletive.po.PostPO;
import abletive.po.SearchPO;
import abletive.po.TagPO;
import abletive.po.TagPostPO;
import abletive.vo.PostListVO;

/**
 * 负责网络传输的类
 *
 * @author Alan
 */
public class HttpImpl {
    private static final String TAG = "Abletive";
    //TODO 网址放入properties文件
    private String webSite = "http://abletive.com/api/";
    private InputStream inputStream;
    private HttpURLConnection httpURLConnection;

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
    public ArrayList<PostListVO> getPostTitleList() {

        webSite = new HttpBuilder(webSite).addParam("count", 7).build();

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

                ArrayList<PostListVO> postTitleList = null;
                if (postPO != null && postPO.getStatus().equals("ok")) {
                    postTitleList = PostTool.getPostTitle(postPO.toPostTitlePO());
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
        Bitmap thumb = null;
        if (processConnection(thumbnailUrl)) {
            thumb = BitmapFactory.decodeStream(inputStream);
        }
        return thumb;
    }

    /**
     * 获得文章类型列表
     *
     * @return 文章类型列表
     */
    public ArrayList<CategoryPO> getCategory() {
        ArrayList<CategoryPO> categories = new ArrayList<CategoryPO>();
        if (processConnection(webSite)) {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                String content, result = "";
                while ((content = bufferedReader.readLine()) != null) {
                    result += content;
                }

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
            } finally {
                closeConn();
                try {
                    closeStream(bufferedReader);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return categories;

    }

    public ArrayList<TagPO> getTag() {
        ArrayList<TagPO> tags = new ArrayList<TagPO>();
        if (processConnection(webSite)) {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                String content, result = "";
                while ((content = bufferedReader.readLine()) != null) {
                    result += content;
                }


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
            } finally {
                closeConn();
                try {
                    closeStream(bufferedReader);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return tags;
    }

    /**
     * 获得搜索的文章结果
     *
     * @param keyWord 搜索的关键字
     * @param page    页码
     * @return 文章列表
     */
    public ArrayList<PostListVO> getResultPosts(String keyWord, int page) {

        webSite = new HttpBuilder(webSite)
                .addParam(InternetAccess.SEARCH.name().toLowerCase(), keyWord)
                .addParam(InternetAccess.PAGE.name().toLowerCase(), page)
                .build();

        return getResultPosts();
    }

    public ArrayList<PostListVO> getResultPosts() {
        ArrayList<PostListVO> postTitleList = null;
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

                if (searchPO != null && searchPO.getStatus().equals("ok")) {
                    postTitleList = PostTool.getPostTitle(searchPO.toPostTitlePO());
                }
                return postTitleList;

            } catch (java.io.IOException e) {
                e.printStackTrace();
            } finally {
                closeConn();
                try {
                    closeStream(bufferedReader);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return postTitleList;
    }


    //TODO 这几个方法需要进行封装

    /**
     * 服务于SearchActivity
     *
     * @param id   tag的唯一id
     * @param page 页码
     * @return 该tag下所有的文章列表
     */
    public ArrayList<PostListVO> getTagPost(int id, int page) {
        webSite = new HttpBuilder(webSite)
                .addParam("id", id)
                .addParam("page", page)
                .build();

        ArrayList<PostListVO> postTitleList = null;
        if (processConnection(webSite)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                String content, result = "";
                while ((content = bufferedReader.readLine()) != null) {
                    result += content;
                }

                TagPostPO tagPost = null;
                if (result.length() != 0) {
                    tagPost = JSONHandler.getTagPost(result);
                }


                if (tagPost != null && tagPost.getStatus().equals("ok")) {
                    postTitleList = PostTool.getPostTitle(tagPost.toPostTitlePO());
                }

                return postTitleList;

            } catch (java.io.IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                closeConn();
                try {
                    closeStream(bufferedReader);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return postTitleList;
    }

    /**
     * @param date 日期YYYY-MM
     * @param page 页码
     * @return 文章标题列表
     */
    public ArrayList<PostListVO> getDatePost(String date, int page) {
        webSite = new HttpBuilder(webSite)
                .addParam("date", date)
                .addParam("page", page)
                .build();

        return getResultPosts();
    }


    /**
     * 设置默认连接方式
     *
     * @param site 连接的url String
     */

    private boolean processConnection(String site) {

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

    private void closeConn() {
        httpURLConnection.disconnect();
    }

    private void closeStream(Closeable stream) throws IOException {
        if (stream != null) {
            stream.close();
        }
    }
}


