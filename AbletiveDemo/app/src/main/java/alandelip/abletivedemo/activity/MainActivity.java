package alandelip.abletivedemo.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import alandelip.abletivedemo.R;
import data.PostTitle;
import httpservice.HttpImpl;
import widget.PostTitleAdapter;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Abletive";

    /**
     * 文章列表内容
     */
    ArrayList<PostTitle> postTitleList;
    /**
     *
     */
    ListView listView;
    PostTitleAdapter postTitleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initListView();
    }

    private void initListView() {
        listView = (ListView) findViewById(R.id.posts_list);
        new PostTitleTask().execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PostTitle postTitle = (PostTitle) parent.getItemAtPosition(position);
                WebActivity.actionStart(MainActivity.this, postTitle.getUrl(), postTitle.getTitle());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class PostTitleTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this, ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Abletive 正在获取列表...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            postTitleList = new HttpImpl("get_posts").getPostTitleList();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            postTitleAdapter = new PostTitleAdapter(MainActivity.this, R.layout.post_title, postTitleList);
            if (postTitleAdapter != null) {
                listView.setAdapter(postTitleAdapter);
            }
        }
    }
}
