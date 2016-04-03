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
import abletive.vo.PostListVO;
import abletive.vo.TagListVO;

/**
 * 标签搜索逻辑
 */
public class TagListImpl implements ListService<TagListVO> {

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
    public ArrayList<TagListVO> getList() {
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
}
