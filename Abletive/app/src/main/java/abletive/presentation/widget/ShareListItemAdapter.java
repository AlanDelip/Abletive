package abletive.presentation.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import abletive.vo.ShareVO;
import alandelip.abletivedemo.R;

/**
 * 分享列表适配器
 * Created by Alan on 2016/5/19.
 */
public class ShareListItemAdapter extends ArrayAdapter<ShareVO> {
    private int resource;

    public ShareListItemAdapter(Context context, int resource, List<ShareVO> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ShareVO shareVO = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, null);
        }
        ImageView shareImage = (ImageView) convertView.findViewById(R.id.share_image);
        shareImage.setImageBitmap(shareVO.getShareBitmap());

        TextView shareName = (TextView) convertView.findViewById(R.id.share_name);
        shareName.setText(shareVO.getShareName());

        return convertView;
    }
}
