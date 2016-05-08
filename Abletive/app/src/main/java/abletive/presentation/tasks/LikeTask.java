package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import abletive.businesslogic.postbl.PostImpl;
import abletive.logicservice.postblservice.PostService;

/**
 * 点赞任务
 * Created by Alan on 2016/5/6.
 */
public class LikeTask extends AsyncTask<Void, Void, Integer> {
    private Context context;
    private PostService postBl;
    private String userID, postID;

    public LikeTask(Context context, String postID, String userID) {
        this.context = context;
        this.postID = postID;
        this.userID = userID;
        postBl = new PostImpl();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return postBl.like(postID, userID);
    }

    @Override
    protected void onPostExecute(Integer credits) {
        if (credits != 0) {
            Toast.makeText(context, "点赞成功，获得" + credits + "积分!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "已经点过赞啦~", Toast.LENGTH_SHORT).show();
        }

    }
}
