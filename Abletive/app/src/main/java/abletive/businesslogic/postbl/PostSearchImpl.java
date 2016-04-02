package abletive.businesslogic.postbl;

import java.util.ArrayList;

import abletive.businesslogic.internetbl.PostHttpImpl;
import abletive.logicservice.internetblservice.PostHttpService;
import abletive.logicservice.postblservice.PostSearchService;
import abletive.po.CategoryPO;
import abletive.po.HttpAuthorPostPO;
import abletive.po.HttpCategoryPO;
import abletive.po.HttpCategoryPostPO;
import abletive.po.HttpDatePostPO;
import abletive.po.HttpSearchPO;
import abletive.po.HttpTagPO;
import abletive.po.HttpTagPostPO;
import abletive.po.PostPO;
import abletive.po.TagPO;
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

    private PostHttpService postHttpBl;

    public PostSearchImpl(PostFilter postFilter) {
        this();
        this.postFilter = postFilter;
    }

    private PostSearchImpl() {
        postHttpBl = new PostHttpImpl();
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
        HttpSearchPO httpSearchPO = postHttpBl.getKeywordResult(page, keyword);
        ArrayList<PostPO> posts = httpSearchPO.getPosts();
        return getPostList(posts);
    }

    @Override
    public ArrayList<TagListVO> getTagList() {
        HttpTagPO httpTagPO = postHttpBl.getTagList();
        ArrayList<TagPO> tagPOList = httpTagPO.getTags();
        ArrayList<TagListVO> tagVOList = new ArrayList<>();
        for (TagPO tagPO : tagPOList) {
            TagListVO tagListVO =
                    new TagListVO(tagPO.getTitle(),
                            tagPO.getDescription(),
                            tagPO.getPostCount());
            tagVOList.add(tagListVO);
        }
        return tagVOList;
    }

    @Override
    public ArrayList<PostListVO> getTagResult(int page, String tagID) {
        HttpTagPostPO httpTagPostPO = postHttpBl.getTagResult(page, tagID);
        ArrayList<PostPO> posts = httpTagPostPO.getPosts();
        return getPostList(posts);
    }

    @Override
    public ArrayList<CategoryListVO> getCategoryList() {
        HttpCategoryPO httpCategoryPO = postHttpBl.getCategoryList();
        ArrayList<CategoryPO> categoryPOList = httpCategoryPO.getCategories();
        ArrayList<CategoryListVO> categoryVOList = new ArrayList<>();
        for (CategoryPO categoryPO : categoryPOList) {
            CategoryListVO categoryListVO =
                    new CategoryListVO(categoryPO.getTitle(),
                            categoryPO.getDescription(),
                            categoryPO.getParent() + "",
                            categoryPO.getPost_count() + "");
            categoryVOList.add(categoryListVO);
        }
        return categoryVOList;
    }

    @Override
    public ArrayList<PostListVO> getCategoryResult(int page, String categoryID) {
        HttpCategoryPostPO httpCategoryPostPO = postHttpBl.getCategoryResult(page, categoryID);
        ArrayList<PostPO> posts = httpCategoryPostPO.getPosts();
        return getPostList(posts);
    }

    @Override
    public ArrayList<PostListVO> getAuthorResult(int page, String authorID) {
        HttpAuthorPostPO httpAuthorPostPO = postHttpBl.getAuthorResult(page, authorID);
        ArrayList<PostPO> posts = httpAuthorPostPO.getPosts();
        return getPostList(posts);
    }

    @Override
    public ArrayList<PostListVO> getDateResult(int page, String date) {
        HttpDatePostPO httpDatePostPO = postHttpBl.getDateResult(page, date);
        ArrayList<PostPO> posts = httpDatePostPO.getPosts();
        return getPostList(posts);
    }

    /**
     * 根据文章数据PostPO数组获得文章展示PostListVO数组
     *
     * @param postPOList 文章数据数组
     * @return 文章展示数组
     */
    private ArrayList<PostListVO> getPostList(ArrayList<PostPO> postPOList) {
        ArrayList<PostListVO> postVOList = new ArrayList<>();
        for (PostPO post : postPOList) {
            PostListVO postListVO =
                    new PostListVO(post.getTitle_plain(),
                            post.getAuthor().getName(),
                            post.getThumbnail_image().getMedium().getUrl(),
                            post.getCategories().get(0).getTitle(),
                            post.getDate(),
                            post.getCustom_fields().getViews() + "",
                            post.getComment_count() + "",
                            post.getUrl());
            postVOList.add(postListVO);
        }
        return postVOList;
    }
}
