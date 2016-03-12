package fragment;

import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

import alandelip.abletivedemo.R;
import alandelip.abletivedemo.activity.WebActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import data.PostTitleVO;
import httpservice.HttpImpl;
import widget.PostTitleAdapter;

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

    /**
     * 文章列表内容
     */
    ArrayList<PostTitleVO> postTitleList;
    ListView listView;
    PostTitleAdapter postTitleAdapter;
    MaterialRefreshLayout materialRefreshLayout;

    private OnFragmentInteractionListener mListener;

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
                Toast.makeText(getContext(), fab1.getLabelText(), Toast.LENGTH_SHORT).show();
                fabMenu.close(true);
            }
        });

        final com.github.clans.fab.FloatingActionButton fab2 =
                (FloatingActionButton) fabMenu.findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), fab2.getLabelText(), Toast.LENGTH_SHORT).show();
                fabMenu.close(true);
            }
        });

    }

    /**
     * <strong>初始化ListView</strong><br>
     * 设置内容，自动从网络获取
     */
    private void initListView() {

        listView = (ListView) getView().findViewById(R.id.posts_list);
        new PostTitleTask().execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PostTitleVO postTitle = (PostTitleVO) parent.getItemAtPosition(position);
                WebActivity.actionStart(getContext(), postTitle.getUrl(), postTitle.getTitle());
            }
        });
    }

    /**
     * <strong>初始化刷新布局</strong><br>
     * 设置模式，监听
     */
    private void initRefreshLayout() {
        materialRefreshLayout = (MaterialRefreshLayout) getView().findViewById(R.id.refresh);
        materialRefreshLayout.setIsOverLay(false);
        materialRefreshLayout.setWaveShow(true);
        materialRefreshLayout.setLoadMore(true);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                new PostTitleTask().execute();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                new PostNextPage().execute();
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

    class PostTitleTask extends AsyncTask<Void, Void, Boolean> {

        SweetAlertDialog progressDialog;

        @Override
        protected void onPreExecute() {
            if (isFirstLaunched) {
                progressDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
                progressDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                progressDialog.setTitleText("Loading");
                progressDialog.setCancelable(true);
                progressDialog.show();
            }
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            postTitleList = new HttpImpl("get_posts").getPostTitleList();
            return (postTitleList != null);
        }

        @Override
        protected void onPostExecute(Boolean postCondition) {
            if (postCondition) {
                if (isFirstLaunched) {
                    progressDialog.dismiss();
                    isFirstLaunched = false;
                } else {
                    materialRefreshLayout.finishRefresh();
                }
                postTitleAdapter = new PostTitleAdapter(getContext(), R.layout.post_title, postTitleList);
                listView.setAdapter(postTitleAdapter);
            } else {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                isFirstLaunched = false;
            }
        }
    }

    class PostNextPage extends AsyncTask<Void, Void, Boolean> {

        ArrayList<PostTitleVO> addedPostTitleList;

        @Override
        protected Boolean doInBackground(Void... params) {
            addedPostTitleList = new HttpImpl("get_posts", page).getPostTitleList();
            return (addedPostTitleList != null && addedPostTitleList.size() != 0);
        }

        @Override
        protected void onPostExecute(Boolean postCondition) {
            if (postCondition) {
                materialRefreshLayout.finishRefreshLoadMore();
                postTitleList.addAll(addedPostTitleList);
                postTitleAdapter.notifyDataSetChanged();
                page++;
            } else {
                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
