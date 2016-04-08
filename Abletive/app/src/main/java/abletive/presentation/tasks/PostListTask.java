package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;

import java.util.ArrayList;

import abletive.logicservice.postblservice.ListService;
import abletive.presentation.widget.PostListAdapter;
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
    ListService listBl;
    ArrayList<PostListVO> postList;
    int page;
    String param;

    public PostListTask(Context context, ListView listview, ArrayList<PostListVO> postList, MaterialRefreshLayout refreshLayout,String param,ListService listService) {
        this.context = context;
        this.postList = postList;
        this.listview = listview;
        this.refreshLayout = refreshLayout;
        this.listBl = listService;
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        refreshLayout.autoRefresh();
    }

    @Override
    protected Void doInBackground(Integer... page) {
        postList = listBl.getResultList(1, param);
        this.page = page[0];
        return null;
    }

    @Override
    protected void onPostExecute(Void param) {
        refreshLayout.finishRefresh();
        if (postList != null) {
            listview.setAdapter(new PostListAdapter(context, R.layout.post_list, postList));
            page = 2;//将页码设置成第二页
        } else {
            Toast.makeText(context, context.getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
        }
    }
}
