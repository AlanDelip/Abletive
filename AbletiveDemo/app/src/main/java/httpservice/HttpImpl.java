package httpservice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import data.PostPO;
import data.PostTitleVO;

/**
 * 负责网络传输的类
 * Created by Alan on 2016/3/7.
 */
public class HttpImpl {
    private static final String TAG = "Abletive";
    String webSite = "http://abletive.com/api/";

    public HttpImpl(String request, int page) {
        webSite = new HttpBuilder(webSite).addField(request).addParam("page", page).build();
    }

    public HttpImpl(String request) {
        webSite = new HttpBuilder(webSite).addField(request).build();
        Log.d(TAG, "HttpImpl: "+webSite);
    }

    public HttpImpl() {
    }

    public ArrayList<PostTitleVO> getPostTitleList() {
        try {
            URL url = new URL(webSite);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String content, result = "";
            while ((content = bufferedReader.readLine()) != null) {
                result += content;
            }

            PostPO postPO = null;
            if (result.length() != 0) {
                postPO = JSONHandler.getPosts(result);
            }

            ArrayList<PostTitleVO> postTitleList = null;
            if (postPO != null && postPO.getStatus().equals("ok")) {
                postTitleList = PostTool.getPostTitle(postPO);
            }

            return postTitleList;

        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Bitmap getAvatar(String avatarUrl) throws IOException {
        URL url = new URL(avatarUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = httpURLConnection.getInputStream();

        Bitmap avatar = BitmapFactory.decodeStream(inputStream);
        return avatar;
    }
}


