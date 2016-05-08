package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import abletive.businesslogic.postbl.PostImpl;
import abletive.logicservice.postblservice.PostService;

/**
 * 收藏文章任务
 * Created by Alan on 2016/5/6.
 */
public class CollectTask extends AsyncTask<Void, Void, Boolean> {

    private Context context;
    private String userID, postID, act;
    private PostService postBl;
    private String actString;

    public CollectTask(Context context, String userID, String postID, String act) {
        this.context = context;
        this.userID = userID;
        this.postID = postID;
        this.act = act;
        if (act.equals("add")) {
            actString = "添加收藏";
        } else {
            actString = "取消收藏";
        }
        postBl = new PostImpl();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return postBl.collect(postID, userID, act);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (aBoolean) {
            Toast.makeText(context, actString + "成功!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, actString + "失败，请检查网络", Toast.LENGTH_SHORT).show();
        }
    }
}
