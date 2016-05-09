package abletive.presentation.uiutil;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

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

    /**
     * 创建单选框
     *
     * @param context  上下文
     * @param title    标题
     * @param items    内容列表
     * @param listener 监听
     * @return 单选弹框
     */
    public static AlertDialog getChoiceDialog(Context context, String title, String[] items,
                                              DialogInterface.OnClickListener listener,
                                              DialogInterface.OnClickListener positiveListener) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setSingleChoiceItems(items, 0, listener)
                .setPositiveButton("保存", positiveListener)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
    }

    /**
     * 创建编辑弹窗
     *
     * @param context  上下文
     * @param title    标题
     * @param editText  EditText
     * @param listener 监听
     * @return 编辑弹窗
     */
    public static AlertDialog getTextDialog(Context context, String title, EditText editText, DialogInterface.OnClickListener listener) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setView(editText)
                .setPositiveButton("保存", listener)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
    }
}
