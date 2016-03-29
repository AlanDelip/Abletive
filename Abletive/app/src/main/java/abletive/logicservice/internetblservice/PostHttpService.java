package abletive.logicservice.internetblservice;

import java.util.ArrayList;

import abletive.businesslogic.postbl.PostFilter;
import abletive.po.PostListPO;
import abletive.po.PostPO;

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
    ArrayList<PostListPO> getPostList(int page);

    /**
     * 获得文章内容
     *
     * @param postID 文章ID
     * @return 文章内容
     */
    PostPO getPost(String postID);

    /**
     * 获得搜索结果
     *
     * @param filter 过滤条件
     * @param page   第几页
     * @return 搜索结果文章列表数组
     */
    ArrayList<PostListPO> getResult(PostFilter filter, int page);
}
