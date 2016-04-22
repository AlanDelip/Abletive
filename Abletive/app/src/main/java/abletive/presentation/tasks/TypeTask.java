package abletive.presentation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;

import abletive.logicservice.postblservice.ListService;
import abletive.presentation.uiutil.WidgetTool;
import abletive.vo.TypeListVO;
import alandelip.abletivedemo.R;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 类别和标签的列表任务
 *
 * @author Alan
 * @version 1.0
 */
public class TypeTask extends AsyncTask<Void, Void, ArrayList<TypeListVO>> {

    Context context;
    ListService listService;
    SweetAlertDialog dialog;
    TypeTaskCallBack typeTaskCallBack;

    public TypeTask(Context context, ListService listService) {
        this.context = context;
        this.listService = listService;
    }

    @Override
    protected void onPreExecute() {
        dialog = WidgetTool.getDefaultDialog(context);
        dialog.show();
    }

    @Override
    protected ArrayList<TypeListVO> doInBackground(Void... params) {
        return listService.getList();
    }

    @Override
    protected void onPostExecute(ArrayList<TypeListVO> list) {
        dialog.dismissWithAnimation();
        if (list.size() != 0) {
            //打开类别活动
            typeTaskCallBack.setTypeList(list);
        } else {
            Toast.makeText(context, context.getString(R.string.internet_failure), Toast.LENGTH_SHORT).show();
        }
    }

    public void setTypeTaskCallBack(TypeTaskCallBack typeTaskCallBack) {
        this.typeTaskCallBack = typeTaskCallBack;
    }

    public interface TypeTaskCallBack {
        void setTypeList(ArrayList<TypeListVO> typeList);
    }
}
