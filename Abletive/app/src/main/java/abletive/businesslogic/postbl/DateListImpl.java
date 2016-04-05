package abletive.businesslogic.postbl;

import java.util.ArrayList;

import abletive.businesslogic.blutil.PostTransformer;
import abletive.businesslogic.internetbl.PostHttpImpl;
import abletive.logicservice.internetblservice.PostHttpService;
import abletive.logicservice.postblservice.ListService;
import abletive.po.HttpDatePostPO;
import abletive.po.PostPO;
import abletive.vo.TypeListVO;
import abletive.vo.PostListVO;

/**
 * 日期文章列表搜索逻辑
 */
public class DateListImpl implements ListService {
    private PostHttpService postHttpBl;

    public DateListImpl() {
        postHttpBl = new PostHttpImpl();
    }

    @Override
    public ArrayList<PostListVO> getResultList(int page, String date) {
        HttpDatePostPO httpDatePostPO = postHttpBl.getDateResult(page, date);
        ArrayList<PostPO> posts = httpDatePostPO.getPosts();
        return PostTransformer.getPostList(posts);
    }

    @Override
    public ArrayList<TypeListVO> getList() {
        return null;
    }

}
