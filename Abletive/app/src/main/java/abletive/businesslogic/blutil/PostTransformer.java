package abletive.businesslogic.blutil;

import java.util.ArrayList;

import abletive.po.PostPO;
import abletive.vo.PostListVO;

/**
 * 文章相关转换器
 *
 * @author Alan
 * @version 1.0
 */
public class PostTransformer {
    /**
     * 根据文章数据PostPO数组获得文章展示PostListVO数组
     *
     * @param postPOList 文章数据数组
     * @return 文章展示数组
     */
    public static ArrayList<PostListVO> getPostList(ArrayList<PostPO> postPOList) {
        ArrayList<PostListVO> postVOList = new ArrayList<>();
        for (PostPO post : postPOList) {
            PostListVO postListVO =
                    new PostListVO(post.getTitle_plain(), post.getExcerpt(),
                            post.getAuthor().getName(),
                            post.getThumbnail_image().getMedium().getUrl(),
                            post.getCategories().get(0).getTitle(),
                            post.getDate(),
                            post.getCustom_fields().getViews() + "",
                            post.getComment_count() + "",
                            post.getUrl());
            postVOList.add(postListVO);
        }
        return postVOList;
    }
}
