package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;

import abletive.businesslogic.userbl.UserInfoImpl;
import abletive.logicservice.userblservice.UserInfoService;
import abletive.presentation.uiutil.WidgetTool;
import abletive.vo.PostCollectionVO;
import alandelip.abletivedemo.R;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 获得收藏文章任务
 * Created by Alan on 2016/5/5.
 */
public class PostCollectionTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private String userID;
    private int page;
    private SweetAlertDialog dialog;
    private UserInfoService userBl;
    private ArrayList<PostCollectionVO> postCollectionList;
    private CollectionPostTaskCallBack collectionPostTaskCallBack;

    public PostCollectionTask(Context context, String userID, int page) {
        this.context = context;
        this.userID = userID;
        this.page = page;
        userBl = new UserInfoImpl();
    }

    @Override
    protected void onPreExecute() {
        if (page == 1) {
            dialog = WidgetTool.getDialog(context, "获取收藏中...");
            dialog.show();
        }
    }

    @Override
    protected Void doInBackground(Void... params) {
        postCollectionList = userBl.getPostCollectionList(userID, page);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (dialog != null) {
            dialog.dismissWithAnimation();
        }
        if (postCollectionList != null) {
            collectionPostTaskCallBack.updatePostList(postCollectionList);
        } else {
            Toast.makeText(context, context.getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
        }
    }

    public void setPostCollectionList(CollectionPostTaskCallBack collectionPostTaskCallBack) {
        this.collectionPostTaskCallBack = collectionPostTaskCallBack;
    }

    public interface CollectionPostTaskCallBack {
        /**
         * 更新收藏列表
         *
         * @param postCollectionList 收藏列表
         */
        void updatePostList(ArrayList<PostCollectionVO> postCollectionList);
    }
}
