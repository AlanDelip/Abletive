package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;

import abletive.businesslogic.userbl.UserInfoImpl;
import abletive.logicservice.userblservice.UserInfoService;
import abletive.presentation.uiutil.WidgetTool;
import abletive.vo.CreditListVO;
import alandelip.abletivedemo.R;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 获取积分列表任务
 * Created by Alan on 2016/5/6.
 */
public class CreditListTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private String userID;
    private int page;
    private UserInfoService userBl;
    private SweetAlertDialog dialog;
    private ArrayList<CreditListVO> creditVOList;
    private CreditListTaskCallBack creditListTaskCallBack;

    public CreditListTask(Context context, String userID, int page) {
        this.context = context;
        this.userID = userID;
        this.page = page;
        userBl = new UserInfoImpl();
    }

    @Override
    protected void onPreExecute() {
        if (page == 1) {
            dialog = WidgetTool.getDialog(context, "获取积分列表中...");
            dialog.show();
        }
    }

    @Override
    protected Void doInBackground(Void... params) {
        creditVOList = userBl.getCreditList(userID, page);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (dialog != null) {
            dialog.dismissWithAnimation();
        }
        if (creditVOList != null) {
            creditListTaskCallBack.updateCreditList(creditVOList);
        } else {
            Toast.makeText(context, context.getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
        }

    }


    public void setCreditListTaskCallBack(CreditListTaskCallBack creditListTaskCallBack) {
        this.creditListTaskCallBack = creditListTaskCallBack;
    }

    public interface CreditListTaskCallBack {
        /**
         * 更新积分列表
         *
         * @param creditList 积分列表
         */
        void updateCreditList(ArrayList<CreditListVO> creditList);
    }
}
