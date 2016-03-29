package abletive.businesslogic.internetbl;

import java.util.ArrayList;

import abletive.businesslogic.postbl.PostFilter;
import abletive.logicservice.internetblservice.PostHttpService;
import abletive.po.PostListPO;
import abletive.po.PostPO;

/**
 * 文章相关网络逻辑
 *
 * @author Alan
 */
public class PostHttpImpl implements PostHttpService{
    @Override
    public ArrayList<PostListPO> getPostList(int page) {
        return null;
    }

    @Override
    public PostPO getPost(String postID) {
        return null;
    }

    @Override
    public ArrayList<PostListPO> getResult(PostFilter filter, int page) {
        return null;
    }
}
