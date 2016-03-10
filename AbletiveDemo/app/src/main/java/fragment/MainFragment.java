package fragment;

import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import java.util.ArrayList;

import alandelip.abletivedemo.R;
import alandelip.abletivedemo.activity.WebActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import data.PostTitle;
import httpservice.HttpImpl;
import widget.PostTitleAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static boolean isFirstLaunched = true;


    /**
     * 文章列表内容
     */
    ArrayList<PostTitle> postTitleList;
    ListView listView;
    PostTitleAdapter postTitleAdapter;
    MaterialRefreshLayout materialRefreshLayout;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

            FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            materialRefreshLayout = (MaterialRefreshLayout) getView().findViewById(R.id.refresh);
            materialRefreshLayout.setIsOverLay(false);
            materialRefreshLayout.setWaveShow(true);
            materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
                @Override
                public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                    new PostTitleTask().execute();
                }
            });

            initListView();
        }
    }


    private void initListView() {

        listView = (ListView) getView().findViewById(R.id.posts_list);
        new PostTitleTask().execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PostTitle postTitle = (PostTitle) parent.getItemAtPosition(position);
                WebActivity.actionStart(getContext(), postTitle.getUrl(), postTitle.getTitle());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirstLaunched = true;
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
                progressDialog.setCancelable(false);
                progressDialog.show();
            }
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            postTitleList = new HttpImpl("get_posts").getPostTitleList();
            return (postTitleList != null && postTitleList.size() != 0);
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
                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                isFirstLaunched = false;
            }
        }
    }
}
