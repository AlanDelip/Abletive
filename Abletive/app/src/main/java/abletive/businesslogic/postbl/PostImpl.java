package abletive.businesslogic.postbl;

import android.graphics.Bitmap;

import java.util.ArrayList;

import abletive.businesslogic.internetbl.PostHttpImpl;
import abletive.logicservice.internetblservice.PostHttpService;
import abletive.logicservice.postblservice.PostService;
import abletive.po.AuthorPO;
import abletive.po.CategoryPO;
import abletive.po.CustomFieldsPO;
import abletive.po.HttpPostContentPO;
import abletive.po.HttpPostPO;
import abletive.po.PostPO;
import abletive.po.ThumbnailImagePO;
import abletive.vo.PostListVO;
import abletive.vo.PostVO;

/**
 * 文章逻辑，获取文章列表和文章内容
 *
 * @author Alan
 */
public class PostImpl implements PostService {

    PostHttpService postHttpBl;

    public PostImpl() {
        postHttpBl = new PostHttpImpl();
    }


    @Override
    public ArrayList<PostListVO> getPostList(int page) {
        return getPostList(page, "");
    }

    @Override
    public ArrayList<PostListVO> getPostList(int page, String cookie) {
        HttpPostPO httpPostPO = postHttpBl.getPostList(page, cookie);

        ArrayList<PostListVO> postList = new ArrayList<PostListVO>();
        ArrayList<PostPO> postPOList = httpPostPO.getPosts();

        for (PostPO postPO : postPOList) {
            AuthorPO authorPO = postPO.getAuthor();
            ArrayList<CategoryPO> categoryPO = postPO.getCategories();
            CustomFieldsPO customFieldsPO = postPO.getCustom_fields();
            ThumbnailImagePO thumbnailImagePO = postPO.getThumbnail_image();
            String stringCommentCount = postPO.getComment_count() + "";
            String commentCount = stringCommentCount.substring(0, stringCommentCount.length() - 2);

            PostListVO postListVO =
                    new PostListVO(postPO.getTitle(), postPO.getExcerpt(), authorPO.getName(),
                            thumbnailImagePO.getMedium().getUrl(), categoryPO.get(0).getTitle(),
                            postPO.getDate(), customFieldsPO.getViews() + "",
                            commentCount, postPO.getUrl());
            postList.add(postListVO);
        }
        return postList;
    }

    @Override
    public PostVO getPost(String postID, String cookie) {
        HttpPostContentPO httpPostContentPO = postHttpBl.getPost(postID, cookie);
        //TODO PostVO需要哪些东西
        return null;
    }

    @Override
    public PostVO getPost(String postID) {
        return getPost(postID, "");
    }

    @Override
    public Bitmap getThumbnail(String thumbnailUrl) {
        return postHttpBl.getThumbnail(thumbnailUrl);
    }
}
