package httpservice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import alandelip.abletivedemo.R;
import abletive.presentation.uiutil.MApplication;
import abletive.po.PostTitlePO;
import abletive.vo.PostListVO;

/**
 * 文章工具
 * Created by Alan on 2016/3/7.
 */
public class PostTool {
    private final static String TAG = "Abletive";

    public static ArrayList<PostListVO> getPostTitle(PostTitlePO postTitlePO) throws IOException {
        int numInPage = postTitlePO.getCount();
        ArrayList<PostListVO> postTitleList = new ArrayList<PostListVO>();
        ArrayList<HashMap<String, Object>> posts = postTitlePO.getPosts();

        for (int i = 0; i < numInPage; i++) {
            //获得文章对象
            HashMap<String, Object> onePost = posts.get(i);

            //文章标题
            String title = (String) onePost.get("title");

            //发布日期
            String date = (String) onePost.get("date");

            //获得作者对象
            Map<String, String> author = (Map<String, String>) onePost.get("author");

            //作者名
            String authorName = author.get("nickname");

            //文章缩略图
            Bitmap thumb = BitmapFactory.decodeResource(MApplication.getContext().getResources(), R.drawable.thumbnail_sample);
            //TODO 应该有类的对应，迭代二建立
            Map<String, Object> tempThumb = (Map<String, Object>) onePost.get("thumbnail_images");
            Map<String, String> mediumThumb = null;
            String thumbUrl = "";
            if (tempThumb != null) {
                mediumThumb = (Map<String, String>) tempThumb.get("medium");
                thumbUrl = mediumThumb.get("url");
                thumb = new HttpImpl().getThumbnail(thumbUrl);
            }

            ArrayList<Map<String, String>> categoriesList = (ArrayList<Map<String, String>>) onePost.get("categories");
            String firstCategory = categoriesList.get(0).get("title");

            //评论数
            double commentsDouble = (double) onePost.get("comment_count");
            int comments = (int) commentsDouble;

            //浏览数
            Map<String, Object> customFields = (Map<String, Object>) onePost.get("custom_fields");
            ArrayList<Object> viewsDouble = (ArrayList<Object>) customFields.get("views");
            String views = (String) viewsDouble.get(0);

            //url
            String url = (String) onePost.get("url");

            PostListVO postTitle = new PostListVO(title, authorName, thumb, firstCategory, date, views, comments, url);
            postTitleList.add(postTitle);
        }
        return postTitleList;
    }

}
