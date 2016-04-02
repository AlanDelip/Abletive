package abletive.businesslogic.postbl;

import java.util.ArrayList;

import abletive.logicservice.postblservice.PostSearchService;
import abletive.vo.CategoryListVO;
import abletive.vo.PostListVO;
import abletive.vo.TagListVO;

/**
 * 文章搜索逻辑，根据关键字搜索
 *
 * @author Alan
 */
public class PostSearchImpl implements PostSearchService {

    /**
     * 文章过滤器
     */
    private PostFilter postFilter;

    public PostSearchImpl(PostFilter postFilter) {
        this.postFilter = postFilter;
    }

    @Override
    public ArrayList<PostListVO> getResult(int page) {
        String content = postFilter.getFilterContent();
        switch (postFilter.getFilter()) {
            case KEYWORD:
                return getKeywordResult(page, content);
            case TAG:
                return getTagResult(page, content);
            case CATEGORY:
                return getCategoryResult(page, content);
            case DATE:
                return getDateResult(page, content);
            case AUTHOR:
                return getAuthorResult(page, content);
            default:
                return null;
        }
    }

    @Override
    public ArrayList<PostListVO> getKeywordResult(int page, String keyword) {
        return null;
    }

    @Override
    public ArrayList<TagListVO> getTagList() {
        return null;
    }

    @Override
    public ArrayList<PostListVO> getTagResult(int page, String tagID) {
        return null;
    }

    @Override
    public ArrayList<CategoryListVO> getCategoryList() {
        return null;
    }

    @Override
    public ArrayList<PostListVO> getCategoryResult(int page, String categoryID) {
        return null;
    }

    @Override
    public ArrayList<PostListVO> getAuthorResult(int page, String authorID) {
        return null;
    }

    @Override
    public ArrayList<PostListVO> getDateResult(int page, String date) {
        return null;
    }
}
