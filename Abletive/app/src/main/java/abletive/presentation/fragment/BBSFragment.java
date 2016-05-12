package abletive.presentation.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import abletive.businesslogic.blutil.ClientLogic;
import abletive.presentation.activity.RankBoardActivity;
import abletive.presentation.uiutil.WidgetTool;
import alandelip.abletivedemo.R;

/**
 * 社区界面碎片
 */
public class BBSFragment extends Fragment {

    private View currentView;
    private Activity mainActivity;

    public BBSFragment() {
        // Required empty public constructor
    }

    public static BBSFragment newInstance(Activity activity) {
        BBSFragment bbsFragment = new BBSFragment();
        ClientLogic.getInstance().setMainActivity(activity);
        return bbsFragment;
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

        TextView emailText = (TextView) currentView.findViewById(R.id.email_text);
        emailText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(getContext());
                editText.setText("有任何问题，请给1178073068@qq.com发送邮件，或者联系qq~希望能把Abletive做得更好！");
                AlertDialog dialog = WidgetTool.getTextDialog(getContext(), "给程序员Alan留言:", editText, "OK~", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
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
