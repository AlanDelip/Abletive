package abletive.businesslogic.blutil;

import java.util.ArrayList;

import abletive.po.AuthorPO;
import abletive.po.CategoryPO;
import abletive.po.CustomFieldsPO;
import abletive.po.ImagePO;
import abletive.po.PostCollectionPO;
import abletive.po.PostPO;
import abletive.po.ThumbnailImagePO;
import abletive.vo.PostCollectionVO;
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
        if (postPOList != null) {
            for (PostPO post : postPOList) {
                PostListVO postListVO = getPost(post);
                postVOList.add(postListVO);
            }
        }
        return postVOList;
    }

    /**
     * 根据文章数据PostPO获得文章展示PostListVO
     *
     * @param postPO 文章数据
     * @return 文章展示
     */
    public static PostListVO getPost(PostPO postPO) {
        AuthorPO authorPO = postPO.getAuthor();
        ArrayList<CategoryPO> categoryPO = postPO.getCategories();
        CustomFieldsPO customFieldsPO = postPO.getCustom_fields();
        ThumbnailImagePO thumbnailImagePO = postPO.getThumbnail_image();
        String stringCommentCount = postPO.getComment_count() + "";

        //默认图片
        String imageUrl = "";
        if (thumbnailImagePO != null) {
            ImagePO mediumImage = thumbnailImagePO.getMedium();
            //判断获得的数据是否有误
            if (mediumImage == null) {
                mediumImage = thumbnailImagePO.getThumbnail();
                if (mediumImage != null) {
                    imageUrl = mediumImage.getUrl();
                }
            } else {
                imageUrl = mediumImage.getUrl();
            }
        }
        return new PostListVO(postPO.getId() + "", postPO.getTitle_plain(),
                postPO.getExcerpt(), authorPO.getName(),
                imageUrl, categoryPO.get(0).getTitle(),
                postPO.getDate(), customFieldsPO.getViews() + "",
                stringCommentCount, postPO.getUrl());
    }

    /**
     * 获得文章收藏
     *
     * @param postCollectionPO 文章收藏PO
     * @return 文章收藏VO
     */
    public static PostCollectionVO getCollectionPost(PostCollectionPO postCollectionPO) {
        return new PostCollectionVO(postCollectionPO.getId() + "",
                postCollectionPO.getTitle(),
                postCollectionPO.getAuthor(),
                postCollectionPO.getThumbnail(),
                postCollectionPO.getCategory(),
                postCollectionPO.getDate(),
                postCollectionPO.getViews() + "",
                postCollectionPO.getComment_count() + "",
                postCollectionPO.getExcerpt());
    }

    /**
     * 获得文章收藏列表
     *
     * @param postCollectionPOList 文章收藏列表PO
     * @return 文章收藏列表VO
     */
    public static ArrayList<PostCollectionVO> getCollectionPostList(ArrayList<PostCollectionPO> postCollectionPOList) {
        ArrayList<PostCollectionVO> postCollectionVOList = new ArrayList<>();
        for (PostCollectionPO po : postCollectionPOList) {
            postCollectionVOList.add(getCollectionPost(po));
        }
        return postCollectionVOList;
    }
}
