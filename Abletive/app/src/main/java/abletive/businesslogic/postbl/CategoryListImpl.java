package abletive.businesslogic.postbl;

import java.util.ArrayList;

import abletive.businesslogic.blutil.PostTransformer;
import abletive.businesslogic.internetbl.PostHttpImpl;
import abletive.logicservice.internetblservice.PostHttpService;
import abletive.logicservice.postblservice.ListService;
import abletive.po.CategoryPO;
import abletive.po.HttpCategoryPO;
import abletive.po.HttpCategoryPostPO;
import abletive.po.PostPO;
import abletive.vo.CategoryListVO;
import abletive.vo.PostListVO;

/**
 * 类别搜索逻辑
 */
public class CategoryListImpl implements ListService<CategoryListVO> {
    private PostHttpService postHttpBl;

    public CategoryListImpl() {
        postHttpBl = new PostHttpImpl();
    }

    @Override
    public ArrayList<PostListVO> getResultList(int page, String categoryID) {
        HttpCategoryPostPO httpCategoryPostPO = postHttpBl.getCategoryResult(page, categoryID);
        ArrayList<PostPO> posts = httpCategoryPostPO.getPosts();
        return PostTransformer.getPostList(posts);
    }

    @Override
    public ArrayList<CategoryListVO> getList() {
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

}
