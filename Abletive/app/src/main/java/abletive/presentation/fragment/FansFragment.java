package abletive.presentation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;

import abletive.presentation.activity.PersonalPageActivity;
import abletive.presentation.tasks.FollowListTask;
import abletive.presentation.widget.UserItemAdapter;
import abletive.vo.FollowUserVO;
import alandelip.abletivedemo.R;

public class FansFragment extends Fragment {
    private static final String ARG_USERID = "userID";
    private View currentView;
    private String userID;
    private ArrayList<FollowUserVO> userVOList;
    private int page = 1;
    private UserItemAdapter userItemAdapter;
    private MaterialRefreshLayout refreshLayout;

    public FansFragment() {
        // Required empty public constructor
    }

    public static FansFragment newInstance(String userID) {
        FansFragment fragment = new FansFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERID, userID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userID = getArguments().getString(ARG_USERID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fans, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        currentView = getView();

        //获得第一页内容
        getPage(1);
    }

    /**
     * 获得某页内容
     *
     * @param requiredPage 需要获得第几页
     */
    private void getPage(final int requiredPage) {
        FollowListTask followListTask = new FollowListTask(getContext(), userID,
                getContext().getString(R.string.follower), requiredPage);
        followListTask.setFollowTaskCallBack(new FollowListTask.FollowTaskCallBack() {
            @Override
            public void init(ArrayList<FollowUserVO> userVOList) {
                //处理刷新
                if (refreshLayout == null) {
                    initRefreshLayout();
                } else {
                    refreshLayout.finishRefresh();
                    refreshLayout.finishRefreshLoadMore();
                }
                //处理返回列表
                if (userVOList.size() == 0) {
                    Toast.makeText(getContext(),
                            getContext().getString(R.string.reach_last), Toast.LENGTH_SHORT).show();
                    return;
                }
                //如果加载下一页就添加列表，否则重新创建列表
                if (FansFragment.this.userVOList != null && requiredPage != 1) {
                    FansFragment.this.userVOList.addAll(userVOList);
                } else {
                    FansFragment.this.userVOList = userVOList;
                }
                //刷新列表显示
                if (userItemAdapter == null) {
                    initListView();
                } else {
                    userItemAdapter.notifyDataSetChanged();
                }
                //更新页码
                page = requiredPage + 1;
            }
        });
        followListTask.execute();
    }

    /**
     * 初始化刷新
     */
    private void initRefreshLayout() {
        refreshLayout = (MaterialRefreshLayout) currentView.findViewById(R.id.refresh);
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                //刷新获得第一页
                getPage(1);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //获得下一页
                getPage(page);
            }
        });
    }

    private void initListView() {
        ListView followerList = (ListView) currentView.findViewById(R.id.fans_user_list);
        userItemAdapter = new UserItemAdapter(getContext(), R.layout.user_item, userVOList);
        followerList.setAdapter(userItemAdapter);
        followerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FollowUserVO userVO = (FollowUserVO) parent.getItemAtPosition(position);
                PersonalPageActivity.newInstance(getContext(), userVO.getId());
            }
        });
    }
}
