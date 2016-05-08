package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import abletive.businesslogic.blutil.PostTransformer;
import abletive.businesslogic.internetbl.PostHttpImpl;
import abletive.logicservice.internetblservice.PostHttpService;
import abletive.po.HttpPostContentPO;
import abletive.po.PostPO;
import abletive.presentation.uiutil.WidgetTool;
import abletive.vo.PostListVO;
import alandelip.abletivedemo.R;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 在收藏中获得文章详情的任务
 * Created by Alan on 2016/5/5.
 */
public class CollectionPostTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private SweetAlertDialog dialog;
    private String postID;
    private PostListVO postListVO;
    private PostHttpService postBl;
    private CollectionPostTaskCallBack collectionPostTaskCallBack;

    public CollectionPostTask(Context context, String postID) {
        this.context = context;
        this.postID = postID;
        postBl = new PostHttpImpl();

    }

    @Override
    protected void onPreExecute() {
        dialog = WidgetTool.getDialog(context, "正在加载文章...");
        dialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        HttpPostContentPO httpPostPO = postBl.getPost(postID);
        if (httpPostPO != null) {
            PostPO postPO = httpPostPO.getPost();
            postListVO = PostTransformer.getPost(postPO);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        dialog.dismissWithAnimation();
        if (postListVO != null) {
            collectionPostTaskCallBack.setPost(postListVO);
        } else {
            Toast.makeText(context, context.getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
        }
    }

    public void setCollectionPostTaskCallBack(CollectionPostTaskCallBack collectionPostTaskCallBack) {
        this.collectionPostTaskCallBack = collectionPostTaskCallBack;
    }

    public interface CollectionPostTaskCallBack {
        /**
         * 获得文章对象
         *
         * @param postListVO 常用的文章对象
         */
        void setPost(PostListVO postListVO);
    }
}
