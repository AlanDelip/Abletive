package abletive.businesslogic.postbl;

import android.graphics.Bitmap;

import java.util.ArrayList;

import abletive.businesslogic.blutil.PostTransformer;
import abletive.businesslogic.internetbl.PostHttpImpl;
import abletive.logicservice.internetblservice.PostHttpService;
import abletive.logicservice.postblservice.PostService;
import abletive.po.CommentPO;
import abletive.po.HttpCommentPO;
import abletive.po.HttpPostContentPO;
import abletive.po.HttpPostPO;
import abletive.po.PostPO;
import abletive.vo.CommentListVO;
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
        if (httpPostPO == null) {
            return null;
        }
        ArrayList<PostListVO> postList = new ArrayList<PostListVO>();
        ArrayList<PostPO> postPOList = httpPostPO.getPosts();

        for (PostPO postPO : postPOList) {
            postList.add(PostTransformer.getPost(postPO));
        }
        return postList;
    }

    @Override
    public PostVO getPost(String postID, String cookie) {
        HttpPostContentPO httpPostContentPO = postHttpBl.getPost(postID, cookie);
        PostPO postPO = httpPostContentPO.getPost();
        ArrayList<CommentPO> commentPOList = postPO.getComments();
        ArrayList<CommentListVO> commentList = new ArrayList<>();
        for (CommentPO po : commentPOList) {
            commentList.add(po.toCommentListVO());
        }

        //根据parentID寻找parentName
        for (int i = 0; i < commentList.size(); i++) {
            CommentListVO commentListVO = commentList.get(i);
            String parentID = commentListVO.getParent();
            for (CommentPO po : commentPOList) {
                if (parentID.equals(po.getId() + "")) {
                    commentListVO.setParentName("对" + po.getAuthor().getName() + "的回复");
                    break;
                } else {
                    commentListVO.setParentName("对文章的回复");
                }
            }
        }

        return new PostVO(postPO.getTitle_plain(),
                postPO.getCustom_fields().getViews() + "",
                postPO.getComment_count() + "",
                postPO.getCategories().get(0).getTitle(),
                postPO.getDate(),
                postPO.getContent(),
                postPO.getCustom_fields().getUm_post_likes() + "",
                postPO.getCustom_fields().getUm_post_collects() + "",
                postPO.getAuthor().getId() + "",
                postPO.getAuthor().getAvatar(),
                postPO.getUrl(),
                commentList);
    }

    @Override
    public ArrayList<PostListVO> getStickyPosts() {
        HttpPostPO httpPostPO = postHttpBl.getPostList(1, "", false);
        if (httpPostPO == null) {
            return null;
        }
        ArrayList<PostListVO> postList = new ArrayList<PostListVO>();
        ArrayList<PostPO> postPOList = httpPostPO.getPosts();

        for (int i = 0; i < 5; i++) {
            PostPO postPO = postPOList.get(i);
            postList.add(PostTransformer.getPost(postPO));
        }
        return postList;
    }

    @Override
    public PostVO getPost(String postID) {
        return getPost(postID, "");
    }

    @Override
    public Bitmap getThumbnail(String thumbnailUrl) {
        return postHttpBl.getThumbnail(thumbnailUrl);
    }

    @Override
    public boolean comment(String userID, String postID, String parentID, String comment, String email) {
        HttpCommentPO httpCommentPO = postHttpBl.comment(userID, postID, parentID, comment, email);
        return httpCommentPO.getStatus().equals("ok");
    }

    @Override
    public int like(String postID, String userID) {
        return postHttpBl.like(postID, userID);
    }

    @Override
    public boolean collect(String postID, String userID, String act) {
        return postHttpBl.collect(postID, userID, act);
    }
}
