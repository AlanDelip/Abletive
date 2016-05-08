package abletive.presentation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import abletive.presentation.activity.RankBoardActivity;
import alandelip.abletivedemo.R;

/**
 * 社区界面碎片
 */
public class BBSFragment extends Fragment {

    private View currentView;

    public BBSFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bbs, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        currentView = getView();
        initToolbar();
        initItems();

    }

    /**
     * 初始化点击响应
     */
    private void initItems() {
        TextView rankText = (TextView) currentView.findViewById(R.id.rank_text);
        rankText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RankBoardActivity.newInstance(getContext(), 30);
            }
        });

        TextView messageText = (TextView) currentView.findViewById(R.id.message_board_text);
        messageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 留言板暂时还没有API
                Toast.makeText(getContext(), "留言板功能将于下一个版本开放，敬请期待!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * <strong>初始化ToolBar</strong><br>
     * 设置标题居中，标题内容
     */
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) currentView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

}
