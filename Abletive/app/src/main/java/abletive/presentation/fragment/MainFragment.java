package abletive.presentation.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

import abletive.businesslogic.postbl.CategoryListImpl;
import abletive.businesslogic.postbl.DateListImpl;
import abletive.businesslogic.postbl.KeywordListImpl;
import abletive.businesslogic.postbl.PostListImpl;
import abletive.businesslogic.postbl.TagListImpl;
import abletive.logicservice.postblservice.ListService;
import abletive.presentation.activity.PostActivity;
import abletive.presentation.activity.SearchActivity;
import abletive.presentation.activity.TypeActivity;
import abletive.presentation.tasks.NextPageTask;
import abletive.presentation.tasks.PostListTask;
import abletive.presentation.tasks.StickyPostTask;
import abletive.presentation.uiutil.WidgetTool;
import abletive.presentation.widget.ImagePagerAdapter;
import abletive.presentation.widget.PostListAdapter;
import abletive.vo.PostListVO;
import alandelip.abletivedemo.R;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * 主界面碎片
 *
 * @author Alan
 * @version 2.0 实现了网络任务的分离调用
 */
public class MainFragment extends Fragment {

    private static final String TAG = "Abletive";

    private View currentView;
    private ArrayList<PostListVO> postList, stickyPostList;
    private ListView mListView;
    private PostListAdapter postListAdapter;
    private MaterialRefreshLayout refreshLayout;
    private int page = 1;
    private String currentQuery;
    private FloatingSearchView mSearchView;
    private AutoScrollViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        currentView = getView();

        initToolBar();

        initRefreshLayout();

        initListView();

        initViewPager();

        initSearchView();

        initFAB();
    }

    /**
     * <strong>初始化ToolBar</strong><br>
     * 设置图标，标题，颜色，图片的监听
     */
    private void initToolBar() {
        Toolbar toolbar = (Toolbar) currentView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
    }

    /**
     * <strong>初始化FloatingActionButton</strong><br>
     * 设置监听
     */
    private void initFAB() {
        final com.github.clans.fab.FloatingActionMenu fabMenu =
                (com.github.clans.fab.FloatingActionMenu) currentView.findViewById(R.id.fab_menu);
        final com.github.clans.fab.FloatingActionButton fabAll =
                (FloatingActionButton) fabMenu.findViewById(R.id.all);
        fabAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListView.setAdapter(postListAdapter);
                Toast.makeText(getContext(), fabAll.getLabelText(), Toast.LENGTH_SHORT).show();
                fabMenu.close(true);
            }
        });

        final com.github.clans.fab.FloatingActionButton fabTag =
                (FloatingActionButton) fabMenu.findViewById(R.id.tag);
        fabTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListService tagListBl = new TagListImpl();
                Toast.makeText(getContext(), fabTag.getLabelText(), Toast.LENGTH_SHORT).show();
                fabMenu.close(true);
                TypeActivity.newInstance(getContext(), tagListBl);
            }
        });

        final com.github.clans.fab.FloatingActionButton fabCategory =
                (FloatingActionButton) fabMenu.findViewById(R.id.category);
        fabCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListService categoryListBl = new CategoryListImpl();
                Toast.makeText(getContext(), fabCategory.getLabelText(), Toast.LENGTH_SHORT).show();
                fabMenu.close(true);
                TypeActivity.newInstance(getContext(), categoryListBl);
            }
        });

        final com.github.clans.fab.FloatingActionButton fabDate =
                (FloatingActionButton) fabMenu.findViewById(R.id.date);
        fabDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = year + "-" + WidgetTool.amplify(monthOfYear + 1);
                        ListService dateListBl = new DateListImpl();
                        Toast.makeText(getContext(), fabDate.getLabelText(), Toast.LENGTH_SHORT).show();
                        fabMenu.close(true);
                        SearchActivity.newInstance(getContext(), date, date, dateListBl);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }

    /**
     * <strong>初始化ListView</strong><br>
     * 设置内容，自动从网络获取
     */
    private void initListView() {
        mListView = (ListView) currentView.findViewById(R.id.posts_list);
        //TODO 用户登录后的cookie
        final PostListTask postListTask
                = new PostListTask(getContext(), refreshLayout, "", new PostListImpl());
        postListTask.setPostListCallBack(new PostListTask.PostListCallBack() {
            @Override
            public void setPostList(ArrayList<PostListVO> callBackPostList) {
                if (callBackPostList != null) {
                    postList = callBackPostList;
                }
            }

            @Override
            public void increasePage() {
                page = 2;
            }

            @Override
            public void setAdapter() {
                if (postList != null) {
                    postListAdapter = new PostListAdapter(getContext(), R.layout.postlist_item, postList);
                    mListView.setAdapter(postListAdapter);
                }
            }
        });
        postListTask.execute();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PostListVO selectedPostListVO = (PostListVO) parent.getItemAtPosition(position);
                PostActivity.newInstance(getContext(), selectedPostListVO.toHashMap());
            }
        });
    }

    /**
     * 初始化ViewPager，默认获得置顶帖
     */
    private void initViewPager() {
        viewPager = (AutoScrollViewPager) currentView.findViewById(R.id.viewpager);

        StickyPostTask stickyPostTask = new StickyPostTask();
        stickyPostTask.setStickyPostCallBack(new StickyPostTask.StickyPostCallBack() {
            @Override
            public void refreshStickyPost(ArrayList<PostListVO> stickyPosts) {
                stickyPostList = stickyPosts;
                viewPager.setAdapter(new ImagePagerAdapter(getContext(), stickyPostList));
                viewPager.setInterval(4000);
                viewPager.startAutoScroll();
            }
        });
        stickyPostTask.execute();
    }


    /**
     * <strong>初始化刷新布局</strong><br>
     * 设置模式，监听
     */
    private void initRefreshLayout() {
        refreshLayout = (MaterialRefreshLayout) currentView.findViewById(R.id.refresh);
        refreshLayout.setLoadMore(true);
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                //TODO 用户登录后的cookie
                final PostListTask postListTask
                        = new PostListTask(getContext(), refreshLayout, "", new PostListImpl());
                postListTask.setPostListCallBack(new PostListTask.PostListCallBack() {
                    @Override
                    public void setPostList(ArrayList<PostListVO> callBackPostList) {
                        postList = callBackPostList;
                    }

                    @Override
                    public void increasePage() {
                        page = 2;
                    }

                    @Override
                    public void setAdapter() {
                        if (postList != null) {
                            postListAdapter = new PostListAdapter(getContext(), R.layout.postlist_item, postList);
                            mListView.setAdapter(postListAdapter);
                        }
                    }
                });
                postListTask.execute();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //TODO 加载下一页时，文章cookie未传入
                NextPageTask nextPageTask
                        = new NextPageTask(getContext(), "", mListView, postListAdapter, postList, refreshLayout, new PostListImpl());
                nextPageTask.setNextPageCallBack(new NextPageTask.NextPageCallBack() {
                    @Override
                    public void setPostList(ArrayList<PostListVO> nextPagePostList) {
                        postList = nextPagePostList;
                    }

                    @Override
                    public void increasePage() {
                        page++;
                    }
                });
                nextPageTask.execute(page);
            }
        });
    }

    /**
     * <strong>初始化搜索栏</strong><br>
     * 设置监听，搜索栏部件
     */
    private void initSearchView() {
        //搜索框
        mSearchView = (FloatingSearchView) currentView.findViewById(R.id.floating_search_view);
        mSearchView.setOnHomeActionClickListener(new FloatingSearchView.OnHomeActionClickListener() {
            @Override
            public void onHomeClicked() {
                mSearchView.setVisibility(View.GONE);
            }
        });

        //<监听>根据输入的字符提前检索
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                currentQuery = mSearchView.getQuery();
                //TODO 根据输入字符自动检索
            }
        });

        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.action_search) {
                    SearchActivity.newInstance(getContext(), currentQuery, currentQuery, new KeywordListImpl());
                }
            }
        });

        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
            }

            @Override
            public void onSearchAction() {
                SearchActivity.newInstance(getContext(), currentQuery, currentQuery, new KeywordListImpl());
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            if (!mSearchView.isActivated()) {
                mSearchView.setVisibility(View.VISIBLE);
            } else {
                mSearchView.setVisibility(View.GONE);
            }
        }
        return true;
    }
}
