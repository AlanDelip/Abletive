package abletive.businesslogic.postbl;

import java.util.ArrayList;

import abletive.logicservice.postblservice.PostService;
import abletive.vo.PostListVO;
import abletive.vo.PostVO;

/**
 * 文章逻辑，获取文章列表和文章内容
 *
 * @author Alan
 */
public class PostImpl implements PostService {
    @Override
    public ArrayList<PostListVO> getPostList(int page) {
        return null;
    }

    @Override
    public PostVO getPost(String postID) {
        return null;
    }
}
