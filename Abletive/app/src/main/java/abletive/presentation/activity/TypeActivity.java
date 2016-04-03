package abletive.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import abletive.businesslogic.postbl.CategoryListImpl;
import abletive.businesslogic.postbl.TagListImpl;
import abletive.logicservice.postblservice.ListService;
import abletive.po.CategoryPO;
import abletive.po.TagPO;
import abletive.presentation.widget.CategoryAdapter;
import abletive.presentation.widget.TagTitleAdapter;
import alandelip.abletivedemo.R;

public class TypeActivity extends AppCompatActivity {

    ListService listService;
    ListView listView;
    ArrayAdapter adapter;
    ArrayList list;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        initToolBar();
        initListView();
    }

    private void initListView() {
        listView = (ListView) findViewById(R.id.result_list);
        type = getIntent().getStringExtra("type");
        switch (type) {
            case "tag":
                listService = new TagListImpl();
                list = new ArrayList<TagPO>();
                adapter = new TagTitleAdapter(TypeActivity.this, R.layout.tag_item, list);
                break;
            case "category":
                listService = new CategoryListImpl();
                list = new ArrayList<CategoryPO>();
                adapter = new CategoryAdapter(TypeActivity.this, R.layout.tag_item, list);
                break;
        }
        list = listService.getList();
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (type) {
                    case "tag":
                        TagPO tagPO = (TagPO) parent.getItemAtPosition(position);
                        SearchActivity.newInstance(TypeActivity.this, tagPO.getTitle(), tagPO.getId());
                        break;
                    case "category":
                        CategoryPO categoryPO = (CategoryPO) parent.getItemAtPosition(position);
                        SearchActivity.newInstance(TypeActivity.this, categoryPO.getTitle(), categoryPO.getId());
                        break;
                }
            }
        });
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.launch_logo);
        toolbar.setTitle(R.string.tag);
        toolbar.setSubtitle(getString(R.string.app_sub));
        setSupportActionBar(toolbar);
    }

}
