package abletive.logicservice.internetblservice;

import android.graphics.Bitmap;

import abletive.businesslogic.postbl.PostFilter;
import abletive.po.HttpPostContentPO;
import abletive.po.HttpPostPO;

/**
 * 文章相关网络逻辑接口
 *
 * @author Alan
 */
public interface PostHttpService {
    /**
     * 获得文章列表
     *
     * @param page 第几页
     * @return 文章列表数组
     */
    HttpPostPO getPostList(int page);

    /**
     * 获得文章列表
     *
     * @param page   第几页
     * @param cookie 用户登录cookie
     * @return 文章列表数组
     */
    HttpPostPO getPostList(int page, String cookie);

    /**
     * 获得文章内容
     *
     * @param postID 文章ID
     * @return 文章内容
     */
    HttpPostContentPO getPost(String postID);

    /**
     * 获得文章内容
     *
     * @param postID 文章ID
     * @param cookie 用户登录cookie
     * @return 文章内容
     */
    HttpPostContentPO getPost(String postID, String cookie);

    /**
     * 获得搜索结果
     *
     * @param filter 过滤条件
     * @param page   第几页
     * @return 搜索结果文章列表数组
     */
    HttpPostPO getResult(PostFilter filter, int page);

    /**
     * 获得文章简略图
     *
     * @param thumbnailUrl 简略图url
     * @return 文章简略图
     */
    Bitmap getThumbnail(String thumbnailUrl);
}
