package abletive.presentation.tasks;

import android.os.AsyncTask;

import java.util.ArrayList;

import abletive.businesslogic.postbl.PostImpl;
import abletive.logicservice.postblservice.PostService;
import abletive.vo.PostListVO;

/**
 * 获得置顶文章任务
 * Created by Alan on 2016/4/19.
 */
public class StickyPostTask extends AsyncTask<Void, Void, Void> {

    ArrayList<PostListVO> postList;
    StickyPostCallBack stickyPostCallBack;

    @Override
    protected Void doInBackground(Void... params) {
        PostService postBl = new PostImpl();
        postList = postBl.getStickyPosts();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        stickyPostCallBack.refreshStickyPost(postList);
    }

    public void setStickyPostCallBack(StickyPostCallBack stickyPostCallBack) {
        this.stickyPostCallBack = stickyPostCallBack;
    }

    public interface StickyPostCallBack {
        void refreshStickyPost(ArrayList<PostListVO> stickyPosts);
    }
}
