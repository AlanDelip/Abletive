package abletive.logicservice.internetblservice;

import android.graphics.Bitmap;

import abletive.po.HttpAuthorPostPO;
import abletive.po.HttpCategoryPO;
import abletive.po.HttpCategoryPostPO;
import abletive.po.HttpCommentPO;
import abletive.po.HttpDatePostPO;
import abletive.po.HttpPostContentPO;
import abletive.po.HttpPostPO;
import abletive.po.HttpSearchPO;
import abletive.po.HttpTagPO;
import abletive.po.HttpTagPostPO;

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
     * 获得文章列表
     *
     * @param page              第几页
     * @param cookie            用户登录cookie
     * @param ignoreStickyPosts 是否忽略置顶文章
     * @return 文章列表数组
     */
    HttpPostPO getPostList(int page, String cookie, boolean ignoreStickyPosts);

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
     * 获得关键字搜索结果
     *
     * @param page    第几页
     * @param keyword 关键字
     * @return 搜索结果文章列表数组
     */
    HttpSearchPO getKeywordResult(int page, String keyword);

    /**
     * 获得标签列表
     *
     * @return 标签列表元素数据
     */
    HttpTagPO getTagList();

    /**
     * 获得某标签下的文章列表
     *
     * @param page  第几页
     * @param tagID 标签ID
     * @return 该标签下的文章列表
     */
    HttpTagPostPO getTagResult(int page, String tagID);

    /**
     * 获得类别列表
     *
     * @return 类别列表元素数据
     */
    HttpCategoryPO getCategoryList();

    /**
     * 获得某类别下的文章列表
     *
     * @param page       第几页
     * @param categoryID 类别ID
     * @return 该类别下的文章列表
     */
    HttpCategoryPostPO getCategoryResult(int page, String categoryID);

    /**
     * 获得某日期的文章列表
     *
     * @param page 第几页
     * @param date 日期
     * @return 该日期的文章列表
     */
    HttpDatePostPO getDateResult(int page, String date);

    /**
     * 获得某作者的文章列表
     *
     * @param page     第几页
     * @param authorID 用户ID
     * @return 该作者的文章列表
     */
    HttpAuthorPostPO getAuthorResult(int page, String authorID);

    /**
     * 获得文章简略图
     *
     * @param thumbnailUrl 简略图url
     * @return 文章简略图
     */
    Bitmap getThumbnail(String thumbnailUrl);

    /**
     * 点赞文章动作
     *
     * @param postID 文章ID
     * @param userID 用户ID
     * @return 点赞成功获得的积分
     */
    int like(String postID, String userID);

    /**
     * @param postID 文章ID
     * @param userID 用户ID
     * @param act    add添加收藏/remove取消收藏
     * @return
     */
    boolean collect(String postID, String userID, String act);

    /**
     * 评论文章
     *
     * @param userID   用户ID
     * @param postID   文章ID
     * @param parentID 父类评论
     * @param comment  评论内容
     * @param email    邮箱
     * @return
     */
    HttpCommentPO comment(String userID, String postID, String parentID, String comment, String email);
}
