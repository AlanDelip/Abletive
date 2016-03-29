package abletive.presentation.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import alandelip.abletivedemo.R;
import abletive.po.CategoryPO;

/**
 * 类别列表适配器
 * Created by Alan on 2016/3/13.
 */
public class CategoryAdapter extends ArrayAdapter<CategoryPO> {
    int resourceID;

    public CategoryAdapter(Context context, int resource, List<CategoryPO> objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoryPO categoryPO = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID, null);
        } else {
            view = convertView;
        }

        TextView tagTitle = (TextView) view.findViewById(R.id.title);
        tagTitle.setText(categoryPO.getTitle());

        TextView tagDescription = (TextView) view.findViewById(R.id.description);
        tagDescription.setText(categoryPO.getDescription());

        TextView postNum = (TextView) view.findViewById(R.id.post_num);
        postNum.setText(categoryPO.getPostCount() + "");

        return view;

    }
}
