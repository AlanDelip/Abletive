package abletive.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import abletive.businesslogic.blutil.ClientLogic;
import abletive.businesslogic.postbl.CategoryListImpl;
import abletive.businesslogic.postbl.TagListImpl;
import abletive.logicservice.postblservice.ListService;
import abletive.presentation.tasks.TypeTask;
import abletive.presentation.widget.TypeAdapter;
import abletive.vo.TypeListVO;
import alandelip.abletivedemo.R;

public class TypeActivity extends AppCompatActivity {

    private ArrayList<TypeListVO> typeList;
    private ListService listService;

    /**
     * @param context     上下文
     * @param listService 列表服务
     */
    public static void newInstance(Context context, ListService listService) {
        Intent intent = new Intent(context, TypeActivity.class);
        ClientLogic.getInstance().setListService(listService);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        listService = ClientLogic.getInstance().getListService();
        initToolBar();
        initListView();
    }

    private void initListView() {
        final ListView listView = (ListView) findViewById(R.id.result_list);
        TypeTask typeTask = new TypeTask(TypeActivity.this, listService);
        typeTask.setTypeTaskCallBack(new TypeTask.TypeTaskCallBack() {
            @Override
            public void setTypeList(ArrayList<TypeListVO> typeList) {
                ArrayAdapter adapter = new TypeAdapter(TypeActivity.this, R.layout.tag_item, typeList);
                listView.setAdapter(adapter);
            }
        });
        typeTask.execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TypeListVO typeListVO = (TypeListVO) parent.getItemAtPosition(position);
                SearchActivity.newInstance(TypeActivity.this, typeListVO.getTitle(), typeListVO.getId(), listService);

            }
        });
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (listService instanceof CategoryListImpl) {
            toolbar.setTitle(R.string.tag);
        } else if (listService instanceof TagListImpl) {
            toolbar.setTitle(R.string.category);
        }
        setSupportActionBar(toolbar);
    }

}
