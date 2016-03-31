package abletive.businesslogic.internetbl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

import abletive.businesslogic.blutil.HttpBuilder;
import abletive.businesslogic.blutil.HttpConnection;
import abletive.businesslogic.blutil.JSONHandler;
import abletive.businesslogic.postbl.PostFilter;
import abletive.logicservice.internetblservice.PostHttpService;
import abletive.po.HttpPostContentPO;
import abletive.po.HttpPostPO;
import abletive.presentation.uiutil.MApplication;
import alandelip.abletivedemo.R;

/**
 * 文章相关网络逻辑
 *
 * @author Alan
 */
public class PostHttpImpl implements PostHttpService {

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
        String request =
                new HttpBuilder()
                        .addField(context.getString(R.string.get_posts))
                        .addParam(context.getString(R.string.page), page)
                        .addParam(context.getString(R.string.cookie), cookie)
                        .build();

        InputStream postInputStream = httpConnection.processConnection(request);
        String result = httpConnection.transStream(postInputStream);
        httpConnection.closeStream(postInputStream);
        httpConnection.closeConn();

        HttpPostPO httpPostPO = null;
        if (result.length() != 0) {
            httpPostPO = JSONHandler.getPosts(result);
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

        InputStream postInputStream = httpConnection.processConnection(request);
        String result = httpConnection.transStream(postInputStream);
        httpConnection.closeStream(postInputStream);
        httpConnection.closeConn();

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
    public HttpPostPO getResult(PostFilter filter, int page) {
        return null;
    }

    @Override
    public Bitmap getThumbnail(String thumbnailUrl) {
        Bitmap thumb;
        InputStream inputStream = httpConnection.processConnection(thumbnailUrl);
        thumb = BitmapFactory.decodeStream(inputStream);
        return thumb;
    }
}
