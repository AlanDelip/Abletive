package abletive.logicservice.postblservice;

import java.util.ArrayList;

import abletive.vo.PostListVO;

/**
 * 列表接口
 */
public interface ListService<T> {
    /**
     * 获得结果列表
     *
     * @param page  第几页
     * @param param 传入参数
     * @return 文章列表
     */
    ArrayList<PostListVO> getResultList(int page, String param);

    /**
     * 获得列表
     *
     * @return 列表
     */
    ArrayList<T> getList();
}
