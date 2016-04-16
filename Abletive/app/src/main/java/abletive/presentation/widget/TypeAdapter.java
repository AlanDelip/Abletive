package abletive.presentation.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import abletive.vo.TypeListVO;
import alandelip.abletivedemo.R;

/**
 * 类别、标签列表适配器
 * Created by Alan on 2016/3/13.
 */
public class TypeAdapter extends ArrayAdapter<TypeListVO> {
    int resourceID;

    public TypeAdapter(Context context, int resource, List<TypeListVO> objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TypeListVO typeListVO = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID, null);
        } else {
            view = convertView;
        }

        TextView tagTitle = (TextView) view.findViewById(R.id.title);
        tagTitle.setText(typeListVO.getTitle());

        TextView tagDescription = (TextView) view.findViewById(R.id.description);
        tagDescription.setText(typeListVO.getDescription());

        TextView postNum = (TextView) view.findViewById(R.id.post_num);
        postNum.setText(typeListVO.getPostCount());

        return view;

    }
}
