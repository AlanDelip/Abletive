package httpservice;

import android.graphics.Bitmap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import data.PostPO;
import data.PostTitle;

/**
 * Created by Alan on 2016/3/7.
 */
public class PostTool {

    public static ArrayList<PostTitle> getPostTitle(PostPO postPO) throws IOException {
        int totalNum = postPO.getCount_total();
        ArrayList<PostTitle> postTitleList = new ArrayList<PostTitle>();
        ArrayList<HashMap<String, Object>> posts = postPO.getPosts();

        for (int i = 0; i < totalNum; i++) {
            HashMap<String, Object> onePost = posts.get(i);
            String title = (String) onePost.get("title");
            String date = (String) onePost.get("date");
            Map<String, String> author = (Map<String, String>) onePost.get("author");
            String authorName = author.get("nickname");

            //TODO 用户头像返回问题
            Bitmap avatar;
            if (author.get("avatar").startsWith("<")) {
                break;
            } else {
                avatar = new HttpImpl("get_posts").getAvatar(author.get("avatar"));
            }

            //TODO 评论数量
            String commentsCount = (String) onePost.get("comments_count");

            String url = (String) onePost.get("url");

            PostTitle postTitle = new PostTitle(title, authorName, avatar, date, commentsCount, url);
            postTitleList.add(postTitle);
        }
        return postTitleList;

    }
}
