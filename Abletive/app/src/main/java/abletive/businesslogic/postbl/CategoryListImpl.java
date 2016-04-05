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
import abletive.vo.TypeListVO;
import abletive.vo.PostListVO;

/**
 * 类别搜索逻辑
 */
public class CategoryListImpl implements ListService {
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
    public ArrayList<TypeListVO> getList() {
        HttpCategoryPO httpCategoryPO = postHttpBl.getCategoryList();
        ArrayList<CategoryPO> categoryPOList = httpCategoryPO.getCategories();
        ArrayList<TypeListVO> categoryVOList = new ArrayList<>();
        for (CategoryPO categoryPO : categoryPOList) {
            TypeListVO typeListVO =
                    new TypeListVO(categoryPO.getId() + "",
                            categoryPO.getTitle(),
                            categoryPO.getDescription(),
                            categoryPO.getPost_count() + "");
            categoryVOList.add(typeListVO);
        }
        return categoryVOList;
    }

}
