package abletive.presentation.fragment;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

import alandelip.abletivedemo.R;
import abletive.presentation.activity.SearchActivity;
import abletive.presentation.activity.WebActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import abletive.po.CategoryPO;
import abletive.vo.PostTitleVO;
import abletive.po.TagPO;
import httpservice.HttpImpl;
import abletive.presentation.widget.CategoryAdapter;
import abletive.presentation.widget.PostTitleAdapter;
import abletive.presentation.widget.TagTitleAdapter;
import abletive.presentation.uiutil.WidgetTool;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    private static final String TAG = "Abletive";

    private static boolean isFirstLaunched = true;

    private static int page = 2;

    private static int searchPage = 1;
    /**
     * 文章列表内容
     */
    ArrayList<PostTitleVO> postTitleList;
    ListView mListView;
    PostTitleAdapter postTitleAdapter;
    MaterialRefreshLayout materialRefreshLayout;
    private ListViewType listViewType = ListViewType.MAIN;
    private SearchType searchType = SearchType.ALL;
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

        if (isFirstLaunched) {
            initToolBar();

            initFAB();

            initListView();
        }

        initRefreshLayout();

        initSearchView();
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

        ImageView toolbarImage = (ImageView) getView().findViewById(R.id.toolbar_image);
        toolbarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "It works!", Toast.LENGTH_SHORT).show();
            }
        });
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
                mListView.setAdapter(postTitleAdapter);
                setListViewListener(ListViewType.MAIN);
                Toast.makeText(getContext(), fab1.getLabelText(), Toast.LENGTH_SHORT).show();
                fabMenu.close(true);
            }
        });

        final com.github.clans.fab.FloatingActionButton fab2 =
                (FloatingActionButton) fabMenu.findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TagTask().execute();
                Toast.makeText(getContext(), fab2.getLabelText(), Toast.LENGTH_SHORT).show();
                fabMenu.close(true);
            }
        });

        final com.github.clans.fab.FloatingActionButton fab3 =
                (FloatingActionButton) fabMenu.findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CategoryTask().execute();
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
        new PostTitleTask().execute();
        setListViewListener(ListViewType.MAIN);
    }

    /**
     * 当表格中内容改变时，修改监听方法
     *
     * @param listViewType 列表内容类型
     */
    private void setListViewListener(ListViewType listViewType) {

        switch (listViewType) {
            case SEARCH:
            case MAIN:
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        PostTitleVO postTitle = (PostTitleVO) parent.getItemAtPosition(position);
                        WebActivity.newInstance(getContext(), postTitle.getUrl(), postTitle.getTitle());
                    }
                });
                break;
            case TAG:
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TagPO tag = (TagPO) parent.getItemAtPosition(position);
                        SearchActivity.newInstance(getContext()
                                , ((TagPO) parent.getItemAtPosition(position)).getTitle()
                                , ((TagPO) parent.getItemAtPosition(position)).getId());
                    }
                });
                break;
            case CATEGORY:
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CategoryPO category = (CategoryPO) parent.getItemAtPosition(position);
                        //TODO 同Tag新建同一个Activity,跳转至过滤后的文章列表界面
                        Toast.makeText(getContext(), "TODO", Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }

    /**
     * <strong>初始化刷新布局</strong><br>
     * 设置模式，监听
     */
    private void initRefreshLayout() {
        materialRefreshLayout = (MaterialRefreshLayout) getView().findViewById(R.id.refresh);
        materialRefreshLayout.setLoadMore(true);
        View view = View.inflate(getContext(), R.layout.tab_item_view, null);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
//                new PostTitleTask().execute();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                new PostNextPageTask().execute();
            }
        });
    }

    /**
     * <strong>初始化搜索栏</strong>
     * 设置监听，搜索栏部件
     */
    private void initSearchView() {
        //搜索按钮
        android.support.design.widget.FloatingActionButton searchFab =
                (android.support.design.widget.FloatingActionButton) getView().findViewById(R.id.fab);
        searchFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatingSearchView mSearchView = (FloatingSearchView) getView().findViewById(R.id.floating_search_view);
                if (mSearchView.getVisibility() == View.VISIBLE) {
                    mSearchView.setVisibility(View.GONE);
                } else {
                    mSearchView.setVisibility(View.VISIBLE);
                }
            }
        });

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
                    searchType = SearchType.ALL;
                } else if (item.getItemId() == R.id.target_author) {
                    searchType = SearchType.AUTHOR;
                } else {
                    new SearchTask().execute(searchType);
                }
            }
        });
        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
            }

            @Override
            public void onSearchAction() {
                new SearchTask().execute(searchType);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirstLaunched = true;
        page = 2;
    }

    /**
     * 列表显示的内容类型枚举
     */
    private enum ListViewType {
        MAIN, TAG, CATEGORY, SEARCH;
    }

    /**
     * 搜索类型枚举
     */
    private enum SearchType {
        AUTHOR, ALL;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /**
     * 获得文章列表网络任务
     */
    class PostTitleTask extends AsyncTask<Void, Void, Boolean> {

        SweetAlertDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = WidgetTool.getDefaultDialog(getContext());
            if (isFirstLaunched) {
                progressDialog.show();
            }
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            postTitleList = new HttpImpl(getString(R.string.get_posts)).getPostTitleList();
            return (postTitleList != null);
        }

        @Override
        protected void onPostExecute(Boolean postCondition) {
            progressDialog.dismiss();
            materialRefreshLayout.finishRefresh();
            if (postCondition) {
                if (isFirstLaunched) {
                    isFirstLaunched = false;
                }
                postTitleAdapter = new PostTitleAdapter(getContext(), R.layout.post_title, postTitleList);
                mListView.setAdapter(postTitleAdapter);
                page = 2;
            } else {
                Toast.makeText(getContext(), getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
                isFirstLaunched = false;
            }
        }
    }

    class PostNextPageTask extends AsyncTask<Void, Void, ArrayList<PostTitleVO>> {

        @Override
        protected ArrayList<PostTitleVO> doInBackground(Void... params) {
            ArrayList<PostTitleVO> addedPostTitleList = null;
            if (listViewType == ListViewType.MAIN) {
                addedPostTitleList = new HttpImpl(getString(R.string.get_posts), page).getPostTitleList();
            } else if (listViewType == ListViewType.SEARCH) {
                addedPostTitleList = new HttpImpl(getString(R.string.get_search_results)).getResultPosts(currentQuery, searchPage);
            }
            return addedPostTitleList;
        }

        @Override
        protected void onPostExecute(ArrayList<PostTitleVO> addedList) {
            materialRefreshLayout.finishRefreshLoadMore();
            if (addedList != null) {
                if (addedList.size() == 0) {
                    Toast.makeText(getContext(), getString(R.string.reach_last), Toast.LENGTH_SHORT).show();
                } else {
                    postTitleList.addAll(addedList);
                    postTitleAdapter.notifyDataSetChanged();
                    if (listViewType == ListViewType.MAIN) {
                        page++;
                    } else if (listViewType == ListViewType.SEARCH) {
                        searchPage++;
                    }
                }
            } else {
                Toast.makeText(getContext(), getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
            }
        }
    }

    class TagTask extends AsyncTask<Void, Void, ArrayList<TagPO>> {

        SweetAlertDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = WidgetTool.getDefaultDialog(getContext());
            progressDialog.show();
        }

        @Override
        protected ArrayList<TagPO> doInBackground(Void... params) {
            return new HttpImpl(getString(R.string.get_tag_index)).getTag();
        }

        @Override
        protected void onPostExecute(ArrayList<TagPO> tagList) {
            progressDialog.dismiss();
            if (tagList != null) {
                TagTitleAdapter tagTitleAdapter = new TagTitleAdapter(getContext(), R.layout.tag_item, tagList);
                mListView.setAdapter(tagTitleAdapter);

                setListViewListener(ListViewType.TAG);
            } else {
                Toast.makeText(getContext(), getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
            }
        }
    }

    class CategoryTask extends AsyncTask<Void, Void, ArrayList<CategoryPO>> {

        SweetAlertDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = WidgetTool.getDefaultDialog(getContext());
            progressDialog.show();
        }

        @Override
        protected ArrayList<CategoryPO> doInBackground(Void... params) {
            return new HttpImpl(getString(R.string.get_category_index)).getCategory();
        }

        @Override
        protected void onPostExecute(ArrayList<CategoryPO> categoryList) {
            progressDialog.dismiss();
            if (categoryList != null) {
                CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), R.layout.tag_item, categoryList);
                mListView.setAdapter(categoryAdapter);

                setListViewListener(ListViewType.CATEGORY);
            } else {
                Toast.makeText(getContext(), getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
            }
        }
    }

    class SearchTask extends AsyncTask<SearchType, Void, ArrayList<PostTitleVO>> {
        SweetAlertDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = WidgetTool.getDefaultDialog(getContext());
            progressDialog.show();
        }

        @Override
        protected ArrayList<PostTitleVO> doInBackground(SearchType... searchType) {
            if (searchType[0] == SearchType.ALL) {
                return new HttpImpl(getString(R.string.get_search_results)).getResultPosts(currentQuery, searchPage);
            } else if (searchType[0] == SearchType.AUTHOR) {
                return new HttpImpl(getString(R.string.get_author_posts)).getResultPosts(currentQuery, searchPage);
            } else {
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<PostTitleVO> postTitleList) {
            progressDialog.dismiss();
            if (postTitleList != null) {
                PostTitleAdapter searchPostAdapter = new PostTitleAdapter(getContext(), R.layout.post_title, postTitleList);
                mListView.setAdapter(searchPostAdapter);

                listViewType = ListViewType.SEARCH;
                searchPage++;
            } else {
                Toast.makeText(getContext(), getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
            }
        }
    }

}