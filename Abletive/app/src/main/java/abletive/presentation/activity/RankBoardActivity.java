package abletive.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import abletive.presentation.tasks.RankBoardTask;
import abletive.presentation.widget.RankItemAdapter;
import abletive.vo.RankVO;
import alandelip.abletivedemo.R;

public class RankBoardActivity extends AppCompatActivity {

    private static final String ARG_LIMIT = "limit";

    /**
     * @param context 上下文
     * @param limit   获取多少个排行
     */
    public static void newInstance(Context context, int limit) {
        Intent intent = new Intent(context, RankBoardActivity.class);
        intent.putExtra(ARG_LIMIT, limit);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_board);

        initViews();
    }

    private void initViews() {
        initToolBar();
        initRankList();
    }

    private void initRankList() {
        final ListView rankListView = (ListView) findViewById(R.id.rank_list);
        RankBoardTask rankBoardTask = new RankBoardTask(this, getIntent().getIntExtra(ARG_LIMIT, 30));
        rankBoardTask.setRankBoardTaskCallBack(new RankBoardTask.RankBoardTaskCallBack() {
            @Override
            public void setRankList(ArrayList<RankVO> rankList) {
                rankListView.setAdapter(
                        new RankItemAdapter(
                                RankBoardActivity.this, R.layout.rank_item, rankList));
            }
        });
        rankBoardTask.execute();
        rankListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RankVO rankVO = (RankVO) parent.getItemAtPosition(position);
                PersonalPageActivity.newInstance(RankBoardActivity.this, rankVO.getUserID());
            }
        });

    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
