package abletive.logicservice.postblservice;

import java.util.ArrayList;

import abletive.vo.PostListVO;

/**
 * 文章搜索逻辑接口
 *
 * @author Alan
 */
public interface PostSearchService {
    /**
     * 获得搜索结果
     *
     * @param page 第几页
     * @return 搜索结果数组
     */
    ArrayList<PostListVO> getResult(int page);
}
