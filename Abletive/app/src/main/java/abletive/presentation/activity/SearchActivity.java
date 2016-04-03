package abletive.presentation.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import abletive.presentation.uiutil.WidgetTool;
import abletive.presentation.widget.PostTitleAdapter;
import abletive.vo.PostListVO;
import alandelip.abletivedemo.R;
import cn.pedant.SweetAlert.SweetAlertDialog;
import httpservice.HttpImpl;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "Abletive";

    private static int page = 1;
    private String keyWord, date;
    private int id;
    private PostTitleAdapter postTitleAdapter;
    private ListView mListView;
    private ArrayList<PostListVO> postList = new ArrayList<PostListVO>();

    public static void newInstance(Context context, String keyWord, int id) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra("keyWord", keyWord);
        intent.putExtra("id", id);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.in_from_bottom, R.anim.out_to_top);
    }

    public static void newInstance(Context context, String date) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra("date", date);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.in_from_bottom, R.anim.out_to_top);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        keyWord = getIntent().getStringExtra("keyWord");
        date = getIntent().getStringExtra("date");
        id = getIntent().getIntExtra("id", 0);

        initToolBar();
        initListView();
    }

    private void initToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("搜索结果:" + keyWord);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initListView() {
        mListView = (ListView) findViewById(R.id.result_list);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PostListVO postTitle = (PostListVO) parent.getItemAtPosition(position);
                WebActivity.newInstance(SearchActivity.this, postTitle.getUrl(), postTitle.getTitle());
            }
        });
        if (date != null && date.length() != 0) {
            new DatePostTask().execute();
        } else {
            new InitTask().execute();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        page = 1;
    }

    class InitTask extends AsyncTask<Void, Void, ArrayList<PostListVO>> {

        SweetAlertDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = WidgetTool.getDefaultDialog(SearchActivity.this);
            progressDialog.show();
        }

        @Override
        protected ArrayList<PostListVO> doInBackground(Void... params) {
            return new HttpImpl(getString(R.string.get_tag_posts)).getTagPost(id, 1);
        }

        @Override
        protected void onPostExecute(ArrayList<PostListVO> tagPostList) {
            progressDialog.dismiss();
            if (tagPostList != null) {
                postList = tagPostList;
                postTitleAdapter = new PostTitleAdapter(SearchActivity.this, R.layout.post_list, postList);
                mListView.setAdapter(postTitleAdapter);
            } else {
                Toast.makeText(SearchActivity.this, getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
            }
        }
    }

    class NextPageTask extends AsyncTask<Void, Void, ArrayList<PostListVO>> {

        @Override
        protected void onPreExecute() {
            //TODO 上拉显示更多
        }

        @Override
        protected ArrayList<PostListVO> doInBackground(Void... params) {
            return new HttpImpl(getString(R.string.get_category_posts)).getTagPost(id, page);
        }

        @Override
        protected void onPostExecute(ArrayList<PostListVO> postTitleList) {
            if (postTitleList != null) {
                if (postTitleList.size() == 0) {
                    Toast.makeText(SearchActivity.this, getString(R.string.reach_last), Toast.LENGTH_SHORT).show();
                } else {
                    postList.addAll(postTitleList);
                    postTitleAdapter.notifyDataSetChanged();
                    page++;
                }
            } else {
                Toast.makeText(SearchActivity.this, getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
            }
        }
    }

    class DatePostTask extends AsyncTask<Void, Void, ArrayList<PostListVO>> {
        SweetAlertDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = WidgetTool.getDefaultDialog(SearchActivity.this);
            progressDialog.show();
        }

        @Override
        protected ArrayList<PostListVO> doInBackground(Void... post) {
            return new HttpImpl(getString(R.string.get_date_posts)).getDatePost(date, 1);
        }

        @Override
        protected void onPostExecute(ArrayList<PostListVO> postTitleList) {
            if (postTitleList != null) {

            }
        }
    }
}
