package abletive.businesslogic.internetbl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

import abletive.businesslogic.blutil.HttpBuilder;
import abletive.businesslogic.blutil.HttpConnection;
import abletive.businesslogic.blutil.JSONHandler;
import abletive.logicservice.internetblservice.PostHttpService;
import abletive.po.HttpAuthorPostPO;
import abletive.po.HttpCategoryPO;
import abletive.po.HttpCategoryPostPO;
import abletive.po.HttpDatePostPO;
import abletive.po.HttpPostContentPO;
import abletive.po.HttpPostPO;
import abletive.po.HttpSearchPO;
import abletive.po.HttpTagPO;
import abletive.po.HttpTagPostPO;
import abletive.presentation.uiutil.MApplication;
import alandelip.abletivedemo.R;

/**
 * 文章相关网络逻辑
 *
 * @author Alan
 */
public class PostHttpImpl implements PostHttpService {
    private final static String TAG = "Abletive";
    /**
     * http连接工具
     */
    HttpConnection httpConnection;
    /**
     * 暂存全局上下文
     */
    Context context;

    public PostHttpImpl() {
        httpConnection = new HttpConnection();
        context = MApplication.getContext();
    }


    @Override
    public HttpPostPO getPostList(int page) {
        return getPostList(page, "");
    }

    @Override
    public HttpPostPO getPostList(int page, String cookie) {
        return getPostList(page, cookie, true);
    }

    @Override
    public HttpPostPO getPostList(int page, String cookie, boolean ignoreStickyPosts) {
        String request =
                new HttpBuilder()
                        .addField(context.getString(R.string.get_posts))
                        .addParam(context.getString(R.string.page), page)
                        .addParam(context.getString(R.string.cookie), cookie)
                        .addParam(context.getString(R.string.ignore_sticky_posts), ignoreStickyPosts + "")
                        .build();

        String result = httpConnection.getResult(request);

        HttpPostPO httpPostPO = null;
        if (result != null) {
            if (result.length() != 0) {
                httpPostPO = JSONHandler.getPosts(result);
            }
        }
        return httpPostPO;
    }

    @Override
    public HttpPostContentPO getPost(String postID, String cookie) {
        String request =
                new HttpBuilder()
                        .addField(context.getString(R.string.get_post))
                        .addParam(context.getString(R.string.id), postID)
                        .addParam(context.getString(R.string.cookie), cookie)
                        .build();

        String result = httpConnection.getResult(request);

        HttpPostContentPO httpPostContentPO = null;
        if (result.length() != 0) {
            httpPostContentPO = JSONHandler.getPost(result);
        }

        return httpPostContentPO;
    }

    @Override
    public HttpPostContentPO getPost(String postID) {
        return getPost(postID, "");
    }

    @Override
    public HttpSearchPO getKeywordResult(int page, String keyword) {
        String request =
                new HttpBuilder()
                        .addField(context.getString(R.string.get_search_results))
                        .addParam(context.getString(R.string.search), keyword)
                        .addParam(context.getString(R.string.page), page)
                        .build();

        String result = httpConnection.getResult(request);

        HttpSearchPO httpSearchPO = null;
        if (result.length() != 0) {
            httpSearchPO = JSONHandler.getSearch(result);
        }

        return httpSearchPO;
    }

    @Override
    public HttpTagPO getTagList() {
        String request =
                new HttpBuilder()
                        .addField(context.getString(R.string.get_tag_index))
                        .build();

        String result = httpConnection.getResult(request);

        HttpTagPO httpTagPO = null;
        if (result.length() != 0) {
            httpTagPO = JSONHandler.getTag(result);
        }

        return httpTagPO;
    }

    @Override
    public HttpTagPostPO getTagResult(int page, String tagID) {
        String request =
                new HttpBuilder()
                        .addField(context.getString(R.string.get_tag_posts))
                        .addParam(context.getString(R.string.page), page)
                        .addParam(context.getString(R.string.id), tagID)
                        .build();

        String result = httpConnection.getResult(request);

        HttpTagPostPO httpTagPostPO = null;
        if (result.length() != 0) {
            httpTagPostPO = JSONHandler.getTagPost(result);
        }

        return httpTagPostPO;
    }

    @Override
    public HttpCategoryPO getCategoryList() {
        String request =
                new HttpBuilder()
                        .addField(context.getString(R.string.get_category_index))
                        .build();

        String result = httpConnection.getResult(request);

        HttpCategoryPO httpCategoryPO = null;
        if (result.length() != 0) {
            httpCategoryPO = JSONHandler.getCategory(result);
        }
        return httpCategoryPO;
    }

    @Override
    public HttpCategoryPostPO getCategoryResult(int page, String categoryID) {
        String request =
                new HttpBuilder()
                        .addField(context.getString(R.string.get_category_posts))
                        .addParam(context.getString(R.string.page), page)
                        .addParam(context.getString(R.string.id), categoryID)
                        .build();

        String result = httpConnection.getResult(request);

        HttpCategoryPostPO httpCategoryPostPO = null;
        if (result.length() != 0) {
            httpCategoryPostPO = JSONHandler.getCategoryPost(result);
        }
        return httpCategoryPostPO;
    }

    @Override
    public HttpDatePostPO getDateResult(int page, String date) {
        String request =
                new HttpBuilder()
                        .addField(context.getString(R.string.get_date_posts))
                        .addParam(context.getString(R.string.page), page)
                        .addParam(context.getString(R.string.date), date)
                        .build();

        String result = httpConnection.getResult(request);
        HttpDatePostPO httpDatePostPO = null;
        if (result.length() != 0) {
            httpDatePostPO = JSONHandler.getDatePost(result);
        }
        return httpDatePostPO;
    }

    @Override
    public HttpAuthorPostPO getAuthorResult(int page, String authorID) {
        String request =
                new HttpBuilder()
                        .addField(context.getString(R.string.get_author_posts))
                        .addParam(context.getString(R.string.page), page)
                        .addParam(context.getString(R.string.id), authorID)
                        .build();

        String result = httpConnection.getResult(request);

        HttpAuthorPostPO httpAuthorPostPO = null;
        if (result.length() != 0) {
            httpAuthorPostPO = JSONHandler.getAuthorPost(result);
        }
        return httpAuthorPostPO;
    }

    @Override
    public Bitmap getThumbnail(String thumbnailUrl) {
        Bitmap thumb;
        InputStream inputStream = httpConnection.processConnection(thumbnailUrl);
        thumb = BitmapFactory.decodeStream(inputStream);
        return thumb;
    }
}
