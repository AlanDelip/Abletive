package abletive.presentation.fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import abletive.presentation.activity.SearchActivity;
import abletive.presentation.activity.TypeActivity;
import abletive.presentation.tasks.NextPageTask;
import abletive.presentation.tasks.PostListTask;
import abletive.presentation.uiutil.WidgetTool;
import abletive.presentation.widget.PostListAdapter;
import abletive.vo.PostListVO;
import abletive.vo.TypeListVO;
import alandelip.abletivedemo.R;

/**
 * 主界面碎片
 *
 * @author Alan
 * @version 1.0
 */
public class MainFragment extends Fragment {

    private static final String TAG = "Abletive";

    /**
     * 本Fragment依附的Activity的contentView
     */
    View currentView;
    ArrayList<PostListVO> postList;
    ListView mListView;
    PostListAdapter postListAdapter;
    MaterialRefreshLayout refreshLayout;
    int page = 1;
    String currentQuery;
    FloatingSearchView mSearchView;

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

        initSearchView();

        initFAB();
    }

    /**
     * <strong>初始化ToolBar</strong><br>
     * 设置图标，标题，颜色，图片的监听
     */
    private void initToolBar() {
        Toolbar toolbar = (Toolbar) currentView.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.launch_logo);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setSubtitle(getString(R.string.app_sub));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

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
                ArrayList<TypeListVO> typeVOList = tagListBl.getList();
                Toast.makeText(getContext(), fabTag.getLabelText(), Toast.LENGTH_SHORT).show();
                fabMenu.close(true);
                TypeActivity.newInstance(getContext(), typeVOList, tagListBl);
            }
        });

        final com.github.clans.fab.FloatingActionButton fabCategory =
                (FloatingActionButton) fabMenu.findViewById(R.id.category);
        fabCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListService categoryListBl = new CategoryListImpl();
                ArrayList<TypeListVO> typeVOList = categoryListBl.getList();
                Toast.makeText(getContext(), fabCategory.getLabelText(), Toast.LENGTH_SHORT).show();
                fabMenu.close(true);
                TypeActivity.newInstance(getContext(), typeVOList, categoryListBl);
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
                        String date = year + "-" + WidgetTool.amplify(monthOfYear);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                datePickerDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == DialogInterface.BUTTON_POSITIVE) {
                            ListService dateListBl = new DateListImpl();
                            ArrayList<TypeListVO> typeVOList = dateListBl.getList();
                            Toast.makeText(getContext(), fabDate.getLabelText(), Toast.LENGTH_SHORT).show();
                            fabMenu.close(true);
                            TypeActivity.newInstance(getContext(), typeVOList, dateListBl);
                        }
                        return true;
                    }
                });
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
        new PostListTask(getContext(), mListView, postList, refreshLayout, "", new PostListImpl()).execute(page);
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
                new PostListTask(getContext(), mListView, postList, refreshLayout, "", new PostListImpl()).execute(page);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //TODO 加载下一页时，文章cookie未传入
                new NextPageTask(getContext(), "", mListView, postListAdapter, postList, refreshLayout, new PostListImpl()).execute(page);
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
        mSearchView.setDismissOnOutsideClick(true);

        //<监听>根据输入的字符提前检索
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                currentQuery = mSearchView.getQuery();
                //TODO 根据输入字符自动检索
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
            mSearchView.setVisibility(View.VISIBLE);
        }
        return true;
    }
}
