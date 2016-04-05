package abletive.businesslogic.postbl;

import java.util.ArrayList;

import abletive.businesslogic.blutil.PostTransformer;
import abletive.businesslogic.internetbl.PostHttpImpl;
import abletive.logicservice.internetblservice.PostHttpService;
import abletive.logicservice.postblservice.ListService;
import abletive.po.HttpTagPO;
import abletive.po.HttpTagPostPO;
import abletive.po.PostPO;
import abletive.po.TagPO;
import abletive.vo.TypeListVO;
import abletive.vo.PostListVO;

/**
 * 标签搜索逻辑
 */
public class TagListImpl implements ListService {

    private PostHttpService postHttpBl;

    public TagListImpl() {
        postHttpBl = new PostHttpImpl();
    }

    @Override
    public ArrayList<PostListVO> getResultList(int page, String tagID) {
        HttpTagPostPO httpTagPostPO = postHttpBl.getTagResult(page, tagID);
        ArrayList<PostPO> posts = httpTagPostPO.getPosts();
        return PostTransformer.getPostList(posts);
    }

    @Override
    public ArrayList<TypeListVO> getList() {
        HttpTagPO httpTagPO = postHttpBl.getTagList();
        ArrayList<TagPO> tagPOList = httpTagPO.getTags();
        ArrayList<TypeListVO> tagVOList = new ArrayList<>();
        for (TagPO tagPO : tagPOList) {
            TypeListVO tagListVO =
                    new TypeListVO(tagPO.getId() + "",
                            tagPO.getTitle(),
                            tagPO.getDescription(),
                            tagPO.getPostCount() + "");
            tagVOList.add(tagListVO);
        }
        return tagVOList;
    }
}
