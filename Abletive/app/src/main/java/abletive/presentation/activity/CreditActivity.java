package abletive.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;

import abletive.presentation.tasks.CreditListTask;
import abletive.presentation.widget.CreditItemAdapter;
import abletive.vo.CreditListVO;
import alandelip.abletivedemo.R;

public class CreditActivity extends AppCompatActivity {

    private static final String ARG_USERNAME = "user_name", ARG_USERID = "user_id";
    private MaterialRefreshLayout refreshLayout;
    private String userName, userID;
    private ArrayList<CreditListVO> creditList;
    private CreditItemAdapter creditItemAdapter;
    private int page = 1;

    public static void newInstance(Context context, String userName, String userID) {
        Intent intent = new Intent(context, CreditActivity.class);
        intent.putExtra(ARG_USERNAME, userName);
        intent.putExtra(ARG_USERID, userID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        if (getIntent() != null) {
            userName = getIntent().getStringExtra(ARG_USERNAME);
            userID = getIntent().getStringExtra(ARG_USERID);
        }

        initViews();

    }

    private void initViews() {
        initToolBar();
        getPage(1);
    }

    /**
     * 获得某页内容
     *
     * @param requiredPage 需要获得第几页
     */
    private void getPage(final int requiredPage) {
        CreditListTask creditListTask = new CreditListTask(this, userID, requiredPage);
        creditListTask.setCreditListTaskCallBack(new CreditListTask.CreditListTaskCallBack() {
            @Override
            public void updateCreditList(ArrayList<CreditListVO> creditList) {
                //处理刷新
                if (refreshLayout == null) {
                    initRefreshLayout();
                } else {
                    refreshLayout.finishRefresh();
                    refreshLayout.finishRefreshLoadMore();
                }
                //处理返回列表
                if (creditList.size() == 0) {
                    Toast.makeText(CreditActivity.this,
                            getString(R.string.reach_last), Toast.LENGTH_SHORT).show();
                    return;
                }

                //如果加载下一页就添加列表，否则重新创建列表
                if (CreditActivity.this.creditList != null && requiredPage != 1) {
                    CreditActivity.this.creditList.addAll(creditList);
                } else {
                    CreditActivity.this.creditList = creditList;
                }
                //刷新列表显示
                if (creditItemAdapter == null) {
                    initListView();
                } else {
                    creditItemAdapter.notifyDataSetChanged();
                }
                //更新页码
                page = requiredPage + 1;
            }
        });
        creditListTask.execute();
    }

    private void initListView() {
        ListView creditListView = (ListView) findViewById(R.id.credit_list);
        creditItemAdapter = new CreditItemAdapter(this, R.layout.credit_item, creditList);
        creditListView.setAdapter(creditItemAdapter);
        creditListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO 积分点击
            }
        });
    }

    private void initRefreshLayout() {
        refreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh);
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                //刷新获得第一页
                getPage(1);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //获得下一页
                getPage(page);
            }
        });
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        String title = userName + "的积分列表";
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }

}
