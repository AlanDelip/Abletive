package abletive.businesslogic.postbl;

import java.util.ArrayList;

import abletive.logicservice.postblservice.ListService;
import abletive.logicservice.postblservice.PostService;
import abletive.vo.PostListVO;
import abletive.vo.TypeListVO;

/**
 * 获得主页文章列表
 * @author Alan
 * @version 1.0
 */
public class PostListImpl implements ListService {

    private PostService postBl;

    public PostListImpl() {
        postBl = new PostImpl();
    }

    @Override
    public ArrayList<PostListVO> getResultList(int page, String cookie) {
        return postBl.getPostList(page,cookie);
    }

    @Override
    public ArrayList<TypeListVO> getList() {
        return null;
    }
}
