package abletive.businesslogic.postbl;

import java.util.ArrayList;

import abletive.businesslogic.blutil.PostTransformer;
import abletive.businesslogic.internetbl.PostHttpImpl;
import abletive.logicservice.internetblservice.PostHttpService;
import abletive.logicservice.postblservice.ListService;
import abletive.po.HttpAuthorPostPO;
import abletive.po.PostPO;
import abletive.vo.PostListVO;

/**
 * 作者文章搜索逻辑
 */
public class AuthorListImpl implements ListService<PostListVO> {

    private PostHttpService postHttpBl;

    public AuthorListImpl() {
        postHttpBl = new PostHttpImpl();
    }

    @Override
    public ArrayList<PostListVO> getResultList(int page, String authorID) {
        HttpAuthorPostPO httpAuthorPostPO = postHttpBl.getAuthorResult(page, authorID);
        ArrayList<PostPO> posts = httpAuthorPostPO.getPosts();
        return PostTransformer.getPostList(posts);
    }

    @Override
    public ArrayList<PostListVO> getList() {
        return null;
    }
}
