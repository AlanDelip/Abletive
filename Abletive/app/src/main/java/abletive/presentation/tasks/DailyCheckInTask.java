package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import abletive.businesslogic.userbl.UserCheckImpl;
import abletive.logicservice.userblservice.UserCheckService;
import abletive.vo.DailyCheckinVO;
import alandelip.abletivedemo.R;

/**
 * 每日签到
 * Created by Alan on 2016/5/6.
 */
public class DailyCheckInTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private String userID;
    private UserCheckService userBl;
    private DailyCheckinVO dailyCheckinVO;

    public DailyCheckInTask(Context context, String userID) {
        this.context = context;
        this.userID = userID;
        userBl = new UserCheckImpl();
    }

    @Override
    protected Void doInBackground(Void... params) {
        dailyCheckinVO = userBl.checkIn(userID);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (dailyCheckinVO != null) {
            Toast.makeText(context, dailyCheckinVO.getMsg(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, context.getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
        }

    }
}
