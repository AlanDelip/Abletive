package abletive.presentation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;

import abletive.businesslogic.blutil.ClientLogic;
import abletive.logicservice.postblservice.ListService;
import abletive.presentation.tasks.NextPageTask;
import abletive.presentation.tasks.PostListTask;
import abletive.presentation.widget.PostListAdapter;
import abletive.vo.PostListVO;
import abletive.vo.PostVO;
import alandelip.abletivedemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnSearchResultClickedListener} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_KEYWORD = "keyword";
    private static final String ARG_ID = "id";

    private String keyWord;
    private String id;
    private ListService listService;

    private OnSearchResultClickedListener mListener;

    private View currentView;
    private AppCompatActivity parentActivity;

    private ListView mListView;
    private ArrayList<PostListVO> postList;
    private PostListAdapter adpater;
    private MaterialRefreshLayout refreshLayout;
    private int page = 1;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param keyWord     关键字，日期，标签名，类别名
     * @param id          标签ID，类别ID，关键字和日期为keyWord
     * @param listService 列表逻辑
     * @return A new instance of fragment SearchFragment.
     */
    public static SearchFragment newInstance(String keyWord, String id, ListService listService) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_KEYWORD, keyWord);
        args.putString(ARG_ID, id);
        fragment.setArguments(args);
        ClientLogic.getInstance().setListService(listService);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            keyWord = getArguments().getString(ARG_KEYWORD);
            id = getArguments().getString(ARG_ID);
        }
        listService = ClientLogic.getInstance().getListService();
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) currentView.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.launch_logo);
        toolbar.setTitle("搜索结果:" + keyWord);
        toolbar.setSubtitle(getString(R.string.app_sub));
        parentActivity.setSupportActionBar(toolbar);
        if (parentActivity.getSupportActionBar() != null) {
            parentActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initListView() {
        mListView = (ListView) currentView.findViewById(R.id.result_list);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PostListVO postListVO = (PostListVO) parent.getItemAtPosition(position);
//                PostVO postVO = new PostVO(postListVO.getTitle(),)
            }
        });
        new PostListTask(getContext(), mListView, postList, refreshLayout, id, listService).execute(page);
    }

    private void initRefreshLayout() {
        refreshLayout = (MaterialRefreshLayout) currentView.findViewById(R.id.refresh);
        refreshLayout.setLoadMore(true);
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                page = 1;
                new PostListTask(getContext(), mListView, postList, refreshLayout, id, listService).execute(page);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                new NextPageTask(getContext(), id, mListView, adpater, postList, refreshLayout, listService)
                        .execute(page);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.fragment_search, container, false);
        return currentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initToolBar();
        initListView();
        initRefreshLayout();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSearchResultClickedListener) {
            mListener = (OnSearchResultClickedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        parentActivity = (AppCompatActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * 当选择搜索列表内容时，跳转至相应的文章内容界面
     */
    public interface OnSearchResultClickedListener {
        /**
         * 跳转至WebFragment显示文章内容
         *
         * @param postVO 文章内容
         */
        void jumpToWebFragment(PostVO postVO);
    }
}
