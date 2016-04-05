package abletive.presentation.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
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

import abletive.presentation.tasks.PostListTask;
import abletive.presentation.uiutil.WidgetTool;
import abletive.presentation.widget.PostListAdapter;
import abletive.vo.PostListVO;
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
     * 文章列表内容
     */
    ArrayList<PostListVO> postList;
    ListView mListView;
    PostListAdapter postListAdapter;
    MaterialRefreshLayout refreshLayout;
    private int page = 1;
    //    private ListViewType listViewType = ListViewType.MAIN;
//    private SearchType searchType = SearchType.ALL;
    private String currentQuery;

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
        Toolbar toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
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
                (com.github.clans.fab.FloatingActionMenu) getView().findViewById(R.id.fab_menu);
        final com.github.clans.fab.FloatingActionButton fab1 =
                (FloatingActionButton) fabMenu.findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListView.setAdapter(postListAdapter);
//                setListViewListener(ListViewType.MAIN);
                Toast.makeText(getContext(), fab1.getLabelText(), Toast.LENGTH_SHORT).show();
                fabMenu.close(true);
            }
        });

        final com.github.clans.fab.FloatingActionButton fab2 =
                (FloatingActionButton) fabMenu.findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new TagTask().execute();
                Toast.makeText(getContext(), fab2.getLabelText(), Toast.LENGTH_SHORT).show();
                fabMenu.close(true);
            }
        });

        final com.github.clans.fab.FloatingActionButton fab3 =
                (FloatingActionButton) fabMenu.findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new CategoryTask().execute();
                Toast.makeText(getContext(), fab3.getLabelText(), Toast.LENGTH_SHORT).show();
                fabMenu.close(true);
            }
        });

        final com.github.clans.fab.FloatingActionButton fab4 =
                (FloatingActionButton) fabMenu.findViewById(R.id.fab4);
        fab4.setOnClickListener(new View.OnClickListener() {
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

                Toast.makeText(getContext(), fab4.getLabelText(), Toast.LENGTH_SHORT).show();
                fabMenu.close(true);
            }
        });

    }

    /**
     * <strong>初始化ListView</strong><br>
     * 设置内容，自动从网络获取
     */
    private void initListView() {
        mListView = (ListView) getView().findViewById(R.id.posts_list);
        new PostListTask(getContext(), mListView, postList, refreshLayout).execute(page);
    }

//    /**
//     * 当表格中内容改变时，修改监听方法
//     *
//     * @param listViewType 列表内容类型
//     */
//    private void setListViewListener(ListViewType listViewType) {
//
//        switch (listViewType) {
//            case SEARCH:
//            case MAIN:
//                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        PostListVO postTitle = (PostListVO) parent.getItemAtPosition(position);
//                        WebActivity.newInstance(getContext(), postTitle.getUrl(), postTitle.getTitle());
//                    }
//                });
//                break;
//            case TAG:
//                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        TagPO tag = (TagPO) parent.getItemAtPosition(position);
//                        SearchActivity.newInstance(getContext()
//                                , ((TagPO) parent.getItemAtPosition(position)).getTitle()
//                                , ((TagPO) parent.getItemAtPosition(position)).getId());
//                    }
//                });
//                break;
//            case CATEGORY:
//                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        CategoryPO category = (CategoryPO) parent.getItemAtPosition(position);
//                        //TODO 同Tag新建同一个Activity,跳转至过滤后的文章列表界面
//                        Toast.makeText(getContext(), "TODO", Toast.LENGTH_SHORT).show();
//                    }
//                });
//        }
//    }

    /**
     * <strong>初始化刷新布局</strong><br>
     * 设置模式，监听
     */
    private void initRefreshLayout() {
        refreshLayout = (MaterialRefreshLayout) getView().findViewById(R.id.refresh);
        refreshLayout.setLoadMore(true);
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                new PostListTask(getContext(), mListView, postList, refreshLayout).execute(1);
                page = 1;
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //TODO 加载下一页
            }
        });
    }

    /**
     * <strong>初始化搜索栏</strong><br>
     * 设置监听，搜索栏部件
     */
    private void initSearchView() {
        //TODO 搜索按钮
//        searchFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FloatingSearchView mSearchView = (FloatingSearchView) getView().findViewById(R.id.floating_search_view);
//                if (mSearchView.getVisibility() == View.VISIBLE) {
//                    mSearchView.setVisibility(View.GONE);
//                } else {
//                    mSearchView.setVisibility(View.VISIBLE);
//                }
//            }
//        });

        //搜索框
        final FloatingSearchView mSearchView = (FloatingSearchView) getView().findViewById(R.id.floating_search_view);
        mSearchView.setShowSearchKey(true);

        //<监听>根据输入的字符提前检索
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                //TODO 根据输入字符自动检索
                currentQuery = mSearchView.getQuery();
            }
        });

        //<监听>列表item的点击事件
        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {

            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.target_all) {
//                    searchType = SearchType.ALL;
                } else if (item.getItemId() == R.id.target_author) {
//                    searchType = SearchType.AUTHOR;
                } else {
//                    new SearchTask().execute(searchType);
                }
            }
        });
        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
            }

            @Override
            public void onSearchAction() {
//                new SearchTask().execute(searchType);
            }
        });

    }

//    /**
//     * 列表显示的内容类型枚举
//     */
//    private enum ListViewType {
//        MAIN, TAG, CATEGORY, SEARCH;
//    }
//
//    /**
//     * 搜索类型枚举
//     */
//    private enum SearchType {
//        AUTHOR, ALL;
//    }


//    /**
//     * 获得文章列表网络任务
//     */
//    class PostTitleTask extends AsyncTask<Void, Void, Boolean> {
//
//        @Override
//        protected void onPreExecute() {
//            refreshLayout.autoRefresh();
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... params) {
//            postList = new HttpImpl(getString(R.string.get_posts)).getPostTitleList();
//            return (postList != null);
//        }
//
//        @Override
//        protected void onPostExecute(Boolean postCondition) {
//            refreshLayout.finishRefresh();
//            if (postCondition) {
//                postListAdapter = new PostTitleAdapter(getContext(), R.layout.post_list, postList);
//                mListView.setAdapter(postListAdapter);
//                page = 2;
//            } else {
//                Toast.makeText(getContext(), getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

//    class TagTask extends AsyncTask<Void, Void, ArrayList<TagPO>> {
//
//        SweetAlertDialog progressDialog;
//
//        @Override
//        protected void onPreExecute() {
//            progressDialog = WidgetTool.getDefaultDialog(getContext());
//            progressDialog.show();
//        }
//
//        @Override
//        protected ArrayList<TagPO> doInBackground(Void... params) {
//            return new HttpImpl(getString(R.string.get_tag_index)).getTag();
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<TagPO> tagList) {
//            progressDialog.dismiss();
//            if (tagList != null) {
//                TagTitleAdapter tagTitleAdapter = new TagTitleAdapter(getContext(), R.layout.tag_item, tagList);
//                mListView.setAdapter(tagTitleAdapter);
//
//                setListViewListener(ListViewType.TAG);
//            } else {
//                Toast.makeText(getContext(), getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    class CategoryTask extends AsyncTask<Void, Void, ArrayList<CategoryPO>> {
//
//        SweetAlertDialog progressDialog;
//
//        @Override
//        protected void onPreExecute() {
//            progressDialog = WidgetTool.getDefaultDialog(getContext());
//            progressDialog.show();
//        }
//
//        @Override
//        protected ArrayList<CategoryPO> doInBackground(Void... params) {
//            return new HttpImpl(getString(R.string.get_category_index)).getCategory();
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<CategoryPO> categoryList) {
//            progressDialog.dismiss();
//            if (categoryList != null) {
//                CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), R.layout.tag_item, categoryList);
//                mListView.setAdapter(categoryAdapter);
//
//                setListViewListener(ListViewType.CATEGORY);
//            } else {
//                Toast.makeText(getContext(), getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    class SearchTask extends AsyncTask<SearchType, Void, ArrayList<PostListVO>> {
//        SweetAlertDialog progressDialog;
//
//        @Override
//        protected void onPreExecute() {
//            progressDialog = WidgetTool.getDefaultDialog(getContext());
//            progressDialog.show();
//        }
//
//        @Override
//        protected ArrayList<PostListVO> doInBackground(SearchType... searchType) {
//            if (searchType[0] == SearchType.ALL) {
//                return new HttpImpl(getString(R.string.get_search_results)).getResultPosts(currentQuery, searchPage);
//            } else if (searchType[0] == SearchType.AUTHOR) {
//                return new HttpImpl(getString(R.string.get_author_posts)).getResultPosts(currentQuery, searchPage);
//            } else {
//                return null;
//            }
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<PostListVO> postTitleList) {
//            progressDialog.dismiss();
//            if (postTitleList != null) {
//                PostTitleAdapter searchPostAdapter = new PostTitleAdapter(getContext(), R.layout.post_list, postTitleList);
//                mListView.setAdapter(searchPostAdapter);
//
//                listViewType = ListViewType.SEARCH;
//                searchPage++;
//            } else {
//                Toast.makeText(getContext(), getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

}
