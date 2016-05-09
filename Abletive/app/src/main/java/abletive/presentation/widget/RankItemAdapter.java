package abletive.presentation.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import abletive.businesslogic.blutil.UserTransformer;
import abletive.presentation.activity.MainActivity;
import abletive.vo.RankVO;
import alandelip.abletivedemo.R;

/**
 * 积分列表适配器
 * Created by Alan on 2016/5/6.
 */
public class RankItemAdapter extends ArrayAdapter<RankVO> {
    int resource;

    public RankItemAdapter(Context context, int resource, List<RankVO> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RankVO rankVO = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, null);
        }
        TextView rankText = (TextView) convertView.findViewById(R.id.rank_text);
        rankText.setText("#" + (position + 1));

        String avatarUrl = rankVO.getAvatarUrl();
        avatarUrl = UserTransformer.transfer(avatarUrl);

        ImageView avatar = (ImageView) convertView.findViewById(R.id.avatar);
        MainActivity.IMAGE_CACHE.get(avatarUrl, avatar);

        ImageView background = (ImageView) convertView.findViewById(R.id.background);
        MainActivity.IMAGE_CACHE.get(avatarUrl, background);

        TextView userNameText = (TextView) convertView.findViewById(R.id.username);
        userNameText.setText(rankVO.getUserName());

        TextView creditView = (TextView) convertView.findViewById(R.id.credit);
        creditView.setText("积分:" + rankVO.getCredit());

        return convertView;

    }
}
