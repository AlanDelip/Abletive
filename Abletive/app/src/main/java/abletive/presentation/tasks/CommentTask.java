package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import abletive.businesslogic.postbl.PostImpl;
import abletive.logicservice.postblservice.PostService;
import abletive.presentation.uiutil.WidgetTool;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 评论任务
 * Created by Alan on 2016/5/1.
 */
public class CommentTask extends AsyncTask<Void, Void, Void> {

    private SweetAlertDialog dialog;
    private Context context;
    private String userID, postID, comment, email;
    private PostService postBl;
    private boolean isCommented;

    public CommentTask(Context context, String userID, String postID, String comment, String email) {
        this.context = context;
        this.userID = userID;
        this.postID = postID;
        this.comment = comment;
        this.email = email;
        postBl = new PostImpl();
    }

    @Override
    protected void onPreExecute() {
        dialog = WidgetTool.getDialog(context, "评论中...");
        dialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        isCommented = postBl.comment(userID, postID, comment, email);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        dialog.dismissWithAnimation();
        if (isCommented) {
            Toast.makeText(context, "评论成功!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "评论失败，请检查网络~", Toast.LENGTH_SHORT).show();
        }
    }
}
