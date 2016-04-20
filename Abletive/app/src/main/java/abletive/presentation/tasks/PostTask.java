package abletive.presentation.tasks;

import android.os.AsyncTask;

import abletive.businesslogic.postbl.PostImpl;
import abletive.logicservice.postblservice.PostService;
import abletive.vo.PostVO;

/**
 * 获得文章具体内容任务
 * Created by Alan on 2016/4/19.
 */
public class PostTask extends AsyncTask<Void, Void, Void> {

    String id, cookie;
    PostService postBl;
    PostVO postVO;
    PostTaskCallBack postTaskCallBack;

    public PostTask(String id, String cookie) {
        this.id = id;
        this.cookie = cookie;
        postBl = new PostImpl();
    }

    @Override
    protected Void doInBackground(Void... params) {
        postVO = postBl.getPost(id, cookie);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        postTaskCallBack.setPost(postVO);
    }

    public void setPostTaskCallBack(PostTaskCallBack postTaskCallBack) {
        this.postTaskCallBack = postTaskCallBack;
    }

    public interface PostTaskCallBack {
        void setPost(PostVO postVO);
    }
}
