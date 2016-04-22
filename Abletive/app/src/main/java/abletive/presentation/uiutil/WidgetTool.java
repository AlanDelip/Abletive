package abletive.presentation.uiutil;

import android.content.Context;
import android.graphics.Color;

import java.util.ArrayList;

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
        return getDialog(context, context.getString(R.string.loading));
    }

    public static SweetAlertDialog getDialog(Context context, String content) {
        SweetAlertDialog progressDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        progressDialog.setTitleText(content);
        progressDialog.setCancelable(true);
        return progressDialog;
    }


    public static String amplify(int date) {
        return amplify(date + "");
    }

    /**
     * 将日期补全为2位
     *
     * @param date 日期
     * @return String补全的日期
     */
    public static String amplify(String date) {
        if (date.length() == 1) {
            date = "0" + date;
        }
        return date;
    }

    /**
     * 是否为无内容列表
     *
     * @param arrayList 列表
     * @return 是否列表中无内容
     */
    public static boolean isEmpty(ArrayList<Object> arrayList) {
        return (arrayList.size() == 0);
    }
}
