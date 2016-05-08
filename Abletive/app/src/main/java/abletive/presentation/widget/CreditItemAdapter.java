package abletive.presentation.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import abletive.vo.CreditListVO;
import alandelip.abletivedemo.R;

/**
 * 积分列表适配器
 * Created by Alan on 2016/5/6.
 */
public class CreditItemAdapter extends ArrayAdapter<CreditListVO> {

    private int resource;

    public CreditItemAdapter(Context context, int resource, List<CreditListVO> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //TODO 可以考虑用正则提取列表内容中的积分数值，然后根据积分大小设置不同的背景文字颜色
        CreditListVO creditListVO = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, null);
        }

        TextView timeView = (TextView) convertView.findViewById(R.id.time);
        timeView.setText(creditListVO.getDate());

        TextView contentView = (TextView) convertView.findViewById(R.id.content);
        contentView.setText(creditListVO.getContent());

        return convertView;
    }
}
