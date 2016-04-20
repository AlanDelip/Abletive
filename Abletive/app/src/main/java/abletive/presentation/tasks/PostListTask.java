package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;

import java.util.ArrayList;

import abletive.logicservice.postblservice.ListService;
import abletive.vo.PostListVO;
import alandelip.abletivedemo.R;

/**
 * 文章列表任务
 *
 * @author Alan
 * @version 1.6
 *          Created by Alan on 2016/4/3.
 */
public class PostListTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private MaterialRefreshLayout refreshLayout;
    private ListService listBl;
    private ArrayList<PostListVO> postList;
    private String param;
    private PostListCallBack postListCallBack;

    public PostListTask(Context context, MaterialRefreshLayout refreshLayout, String param, ListService listService) {
        this.context = context;
        this.refreshLayout = refreshLayout;
        this.listBl = listService;
        this.param = param;
    }

    @Override
    protected Void doInBackground(Void... page) {
        postList = listBl.getResultList(1, param);
        return null;
    }

    @Override
    protected void onPostExecute(Void param) {
        refreshLayout.finishRefresh();
        if (postList != null) {
            postListCallBack.setPostList(postList);
            postListCallBack.setAdapter();
            postListCallBack.increasePage();
        } else {
            Toast.makeText(context, context.getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
        }
    }

    public void setPostListCallBack(PostListCallBack postListCallBack) {
        this.postListCallBack = postListCallBack;
    }

    public interface PostListCallBack {
        /**
         * 设置postList
         */
        void setPostList(ArrayList<PostListVO> callBackPostList);

        /**
         * 更新页码
         */
        void increasePage();

        void setAdapter();
    }
}
