package widget;

import android.content.Context;
import android.graphics.Color;

import alandelip.abletivedemo.R;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 小部件的工具类
 * Created by Alan on 2016/3/12.
 */
public class WidgetTool {

    /**
     * 获得默认的ProgressDialog
     *
     * @param context 上下文对象
     * @return 默认的ProgressDialog
     */
    public static SweetAlertDialog getDefaultDialog(Context context) {
        SweetAlertDialog progressDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        progressDialog.setTitleText(context.getString(R.string.loading));
        progressDialog.setCancelable(true);
        return progressDialog;
    }

    public static String amplify(int date) {
        return amplify(date + "");
    }

    public static String amplify(String date) {
        if (date.length() == 1) {
            date = "0" + date;
        }
        return date;
    }
}
