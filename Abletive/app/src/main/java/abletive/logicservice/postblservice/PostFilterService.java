package abletive.logicservice.postblservice;

import abletive.businesslogic.postbl.PostFilter;

/**
 * 文章过滤器逻辑接口
 *
 * @author Alan
 */
public interface PostFilterService {
    /**
     * 设置过滤器类型，需要调用{@link PostFilterService#setFilterContent(String)}方法，设置具体内容
     *
     * @param filter 过滤器类型
     * @return 是否设置成功
     */
    boolean setFilter(PostFilter.Filter filter);

    /**
     * 设置文字过滤
     *
     * @param filterContent 文字过滤内容
     * @return 是否设置成功
     */
    boolean setFilterContent(String filterContent);

    /**
     * 删除文字过滤历史
     *
     * @return 是否删除成功
     */
    boolean deleteFilterHistory();

    /**
     * 获得设置的文字过滤内容
     *
     * @return 文字内容
     */
    String getFilterContent();

    /**
     * 获得设置的过滤类型
     *
     * @return 过滤类型
     */
    PostFilter.Filter getFilter();
}
