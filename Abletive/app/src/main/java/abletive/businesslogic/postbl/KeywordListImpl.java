package abletive.businesslogic.postbl;

import java.util.ArrayList;

import abletive.businesslogic.blutil.PostTransformer;
import abletive.businesslogic.internetbl.PostHttpImpl;
import abletive.logicservice.internetblservice.PostHttpService;
import abletive.logicservice.postblservice.ListService;
import abletive.po.HttpSearchPO;
import abletive.po.PostPO;
import abletive.vo.TypeListVO;
import abletive.vo.PostListVO;

/**
 * 文章搜索逻辑
 */
public class KeywordListImpl implements ListService {

    private PostHttpService postHttpBl;

    public KeywordListImpl() {
        postHttpBl = new PostHttpImpl();
    }

    @Override
    public ArrayList<PostListVO> getResultList(int page, String keyword) {
        HttpSearchPO httpSearchPO = postHttpBl.getKeywordResult(page, keyword);
        ArrayList<PostPO> posts = httpSearchPO.getPosts();
        return PostTransformer.getPostList(posts);
    }

    @Override
    public ArrayList<TypeListVO> getList() {
        return null;
    }

}
