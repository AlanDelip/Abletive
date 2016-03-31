package abletive.logicservice.postblservice;

import android.graphics.Bitmap;

import java.util.ArrayList;

import abletive.vo.PostListVO;
import abletive.vo.PostVO;

/**
 * 文章逻辑接口
 *
 * @author Alan
 */
public interface PostService {
    /**
     * 获得文章列表
     *
     * @param page 第几页
     * @return 文章列表数组
     */
    ArrayList<PostListVO> getPostList(int page);

    /**
     * 获得文章列表
     *
     * @param page   第几页
     * @param cookie 用户登录cookie
     * @return 文章列表数组
     */
    ArrayList<PostListVO> getPostList(int page, String cookie);

    /**
     * 获得文章内容
     *
     * @param postID 文章ID
     * @return 文章内容Html
     */
    PostVO getPost(String postID);

    /**
     * 获得文章内容
     *
     * @param postID 文章ID
     * @param cookie 用户登录cookie
     * @return 文章内容Html
     */
    PostVO getPost(String postID, String cookie);

    /**
     * 获得文章简略图
     *
     * @param thumbnailUrl 简略图url
     * @return 文章简略图
     */
    Bitmap getThumbnail(String thumbnailUrl);
}
