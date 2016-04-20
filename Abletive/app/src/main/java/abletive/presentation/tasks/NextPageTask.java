package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;

import java.util.ArrayList;

import abletive.logicservice.postblservice.ListService;
import abletive.vo.PostListVO;
import alandelip.abletivedemo.R;

/**
 * 下一页任务,适配于目前所有的活动
 *
 * @author Alan
 * @version 1.5
 */
public class NextPageTask extends AsyncTask<Integer, Void, ArrayList<PostListVO>> {

    Context context;
    ListService listService;
    ListView listView;
    ArrayList<PostListVO> postList;
    ArrayAdapter adapter;
    MaterialRefreshLayout refreshLayout;
    String param;
    NextPageCallBack nextPageCallBack;

    /**
     * @param context       上下文
     * @param param         传入参数
     * @param listView      列表部件
     * @param adapter       列表适配
     * @param postList      列表数组
     * @param refreshLayout 刷新部件
     * @param listService   列表接口
     */
    public NextPageTask(Context context, String param, ListView listView, ArrayAdapter adapter, ArrayList<PostListVO> postList, MaterialRefreshLayout refreshLayout, ListService listService) {
        this.context = context;
        this.listView = listView;
        this.postList = postList;
        this.adapter = adapter;
        this.refreshLayout = refreshLayout;
        this.listService = listService;
        this.param = param;
    }

    @Override
    protected ArrayList<PostListVO> doInBackground(Integer... page) {
        return listService.getResultList(page[0], param);
    }

    @Override
    protected void onPostExecute(ArrayList<PostListVO> addedList) {
        refreshLayout.finishRefreshLoadMore();
        if (addedList != null) {
            if (addedList.size() == 0) {
                Toast.makeText(context, context.getString(R.string.reach_last), Toast.LENGTH_SHORT).show();
            } else {
                if (postList == null) {
                    return;
                }
                postList.addAll(addedList);
                nextPageCallBack.setPostList(postList);
                nextPageCallBack.increasePage();
                adapter.notifyDataSetChanged();
            }
        } else {
            Toast.makeText(context, context.getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
        }
    }

    public void setNextPageCallBack(NextPageCallBack nextPageCallBack) {
        this.nextPageCallBack = nextPageCallBack;
    }

    public interface NextPageCallBack {
        void setPostList(ArrayList<PostListVO> nextPagePostList);

        void increasePage();
    }
}
