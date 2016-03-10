package httpservice;

import android.graphics.Bitmap;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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
    private final static String TAG = "Abletive";

    public static ArrayList<PostTitle> getPostTitle(PostPO postPO) throws IOException {
        int numInPage = postPO.getCount();
        ArrayList<PostTitle> postTitleList = new ArrayList<PostTitle>();
        ArrayList<HashMap<String, Object>> posts = postPO.getPosts();

        for (int i = 0; i < numInPage; i++) {
            HashMap<String, Object> onePost = posts.get(i);
            String title = (String) onePost.get("title");
            String date = (String) onePost.get("date");
            Map<String, String> author = (Map<String, String>) onePost.get("author");
            String authorName = author.get("nickname");

            //TODO 用户头像返回问题
            Bitmap avatar = null;
            String tempAvatar = author.get("avatar");
            if (tempAvatar.startsWith("<")) {
                Document document = Jsoup.parse(tempAvatar);
                Elements imageSrc = document.getElementsByAttribute("src");
                Log.d(TAG, "getPostTitle: " + imageSrc.text());
                break;
            } else {
                avatar = new HttpImpl("get_posts").getAvatar(tempAvatar);
            }

            double commentsCountDouble = (double) onePost.get("comment_count");
            int commentsCount = (int) commentsCountDouble;

            Map<String, Object> customFields = (Map<String, Object>) onePost.get("custom_fields");
            ArrayList<Object> viewsDouble = (ArrayList<Object>) customFields.get("views");
            String views = (String) viewsDouble.get(0);

            String url = (String) onePost.get("url");

            PostTitle postTitle = new PostTitle(title, authorName, avatar, date, views, commentsCount, url);
            postTitleList.add(postTitle);
        }
        return postTitleList;

    }
}
