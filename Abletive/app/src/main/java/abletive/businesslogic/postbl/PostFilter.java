package abletive.businesslogic.postbl;

import abletive.logicservice.postblservice.PostFilterService;

/**
 * 文章搜索过滤器
 *
 * @author Alan
 * @version 1.0
 */
public class PostFilter implements PostFilterService {

    /**
     * 设置的过滤器类型
     */
    private Filter filter;

    /**
     * 设置的过滤内容
     */
    private String filterContent;

    public PostFilter(Filter filter) {
        this.filter = filter;
    }

    public PostFilter(String filterContent) {
        this.filterContent = filterContent;
    }

    @Override
    public boolean setFilter(Filter filter) {
        this.filter = filter;
        return true;
    }

    @Override
    public boolean setFilterContent(String filterContent) {
        this.filterContent = filterContent;
        //TODO 保存搜索历史
        return true;
    }

    @Override
    public boolean deleteFilterHistory() {
        //TODO 删除搜索历史
        return true;
    }

    @Override
    public String getFilterContent() {
        return filterContent;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    /**
     * 过滤类型枚举
     */
    public enum Filter {
        /**
         * 关键字
         */
        KEYWORD,
        /**
         * 标签
         */
        TAG,
        /**
         * 类别
         */
        CATEGORY,
        /**
         * 日期
         */
        DATE,
        /**
         * 作者
         */
        AUTHOR;
    }
}
