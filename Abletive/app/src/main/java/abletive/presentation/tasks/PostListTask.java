package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;

import java.util.ArrayList;

import abletive.businesslogic.postbl.PostImpl;
import abletive.logicservice.postblservice.PostService;
import abletive.presentation.widget.PostTitleAdapter;
import abletive.vo.PostListVO;
import alandelip.abletivedemo.R;

/**
 * 文章列表任务
 *
 * @author Alan
 * @version 1.5
 *          Created by Alan on 2016/4/3.
 */
public class PostListTask extends AsyncTask<Integer, Void, Void> {

    Context context;
    ListView listview;
    MaterialRefreshLayout refreshLayout;
    PostService postBl;
    ArrayList<PostListVO> postList;
    int page;

    public PostListTask(Context context, ListView listview, ArrayList<PostListVO> postList, MaterialRefreshLayout refreshLayout) {
        this.context = context;
        this.postList = postList;
        this.listview = listview;
        this.refreshLayout = refreshLayout;

        postBl = new PostImpl();
    }

    @Override
    protected void onPreExecute() {
        refreshLayout.autoRefresh();
    }

    @Override
    protected Void doInBackground(Integer... page) {
        postList = postBl.getPostList(1, "");
        this.page = page[0];
        return null;
    }

    @Override
    protected void onPostExecute(Void param) {
        refreshLayout.finishRefresh();
        if (postList != null) {
            listview.setAdapter(new PostTitleAdapter(context, R.layout.post_list, postList));
            page = 2;
        } else {
            Toast.makeText(context, context.getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
        }
    }
}
