package abletive.logicservice.postblservice;

import java.util.ArrayList;

import abletive.vo.TypeListVO;
import abletive.vo.PostListVO;
import abletive.vo.TagListVO;

/**
 * 文章搜索逻辑接口
 *
 * @author Alan
 */
public interface PostSearchService {
    /**
     * 利用传入的PostFilter中的设置数据来获得搜索结果
     *
     * @param page 第几页
     * @return 搜索结果数组
     */
    ArrayList<PostListVO> getResult(int page);

    /**
     * 获得某关键字的文章列表
     *
     * @param page    第几页
     * @param keyword 关键字
     * @return 该关键字的文章列表
     */
    ArrayList<PostListVO> getKeywordResult(int page, String keyword);

    /**
     * 获得标签列表
     *
     * @return 标签元素数组
     */
    ArrayList<TagListVO> getTagList();

    /**
     * 获得某标签的文章列表
     *
     * @param page  第几页
     * @param tagID 标签ID
     * @return 该标签下的文章列表
     */
    ArrayList<PostListVO> getTagResult(int page, String tagID);

    /**
     * 获得类别列表
     *
     * @return 类别元素数组
     */
    ArrayList<TypeListVO> getCategoryList();

    /**
     * 获得某类别的文章列表
     *
     * @param page       第几页
     * @param categoryID 类别ID
     * @return 该类别下的文章列表
     */
    ArrayList<PostListVO> getCategoryResult(int page, String categoryID);

    /**
     * 获得某作者文章列表
     *
     * @param page     第几页
     * @param authorID 作者ID
     * @return 该作者的文章列表
     */
    ArrayList<PostListVO> getAuthorResult(int page, String authorID);

    /**
     * 获得某日期的文章列表
     *
     * @param page 第几页
     * @param date 日期(yyyy-MM)
     * @return 该日期的文章列表
     */
    ArrayList<PostListVO> getDateResult(int page, String date);
}
