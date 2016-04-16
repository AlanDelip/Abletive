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
import abletive.logicservice.postblservice.ListService;
import abletive.presentation.widget.TypeAdapter;
import abletive.vo.TypeListVO;
import alandelip.abletivedemo.R;

public class TypeActivity extends AppCompatActivity {

    /**
     * @param context     上下文
     * @param list        类别列表
     * @param listService 列表服务
     */
    public static void newInstance(Context context, ArrayList<TypeListVO> list, ListService listService) {
        Intent intent = new Intent(context, TypeActivity.class);
        ClientLogic.getInstance().setTypeList(list);
        ClientLogic.getInstance().setListService(listService);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        initToolBar();
        initListView();
    }

    private void initListView() {
        ListView listView = (ListView) findViewById(R.id.result_list);

        final ListService listService = ClientLogic.getInstance().getListService();
        ArrayList<TypeListVO> list = listService.getList();
        ArrayAdapter adapter = new TypeAdapter(TypeActivity.this, R.layout.tag_item, list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

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
        toolbar.setNavigationIcon(R.drawable.launch_logo);
        toolbar.setTitle(R.string.tag);
        toolbar.setSubtitle(getString(R.string.app_sub));
        setSupportActionBar(toolbar);
    }

}
