package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;

import abletive.businesslogic.forumbl.RankBoardImpl;
import abletive.logicservice.forumblservice.RankBoardService;
import abletive.presentation.uiutil.WidgetTool;
import abletive.vo.RankVO;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 获取排行榜任务
 * Created by Alan on 2016/5/6.
 */
public class RankBoardTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private int limit;
    private SweetAlertDialog dialog;
    private ArrayList<RankVO> rankVOList;
    private RankBoardService rankBoardBl;
    private RankBoardTaskCallBack rankBoardTaskCallBack;

    public RankBoardTask(Context context, int limit) {
        this.context = context;
        this.limit = limit;
        rankBoardBl = new RankBoardImpl();
    }

    @Override
    protected void onPreExecute() {
        dialog = WidgetTool.getDialog(context, "获取排行榜...");
        dialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        rankVOList = rankBoardBl.getRankList(limit);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        dialog.dismissWithAnimation();
        if (rankVOList != null) {
            rankBoardTaskCallBack.setRankList(rankVOList);
        }else{
            Toast.makeText(context, "获取排行榜失败，请检查网络", Toast.LENGTH_SHORT).show();
        }
    }

    public void setRankBoardTaskCallBack(RankBoardTaskCallBack rankBoardTaskCallBack){
        this.rankBoardTaskCallBack = rankBoardTaskCallBack;
    }

    public interface RankBoardTaskCallBack {
        /**
         * 设置排行榜列表
         *
         * @param rankList 获得的排行榜列表
         */
        void setRankList(ArrayList<RankVO> rankList);
    }
}
