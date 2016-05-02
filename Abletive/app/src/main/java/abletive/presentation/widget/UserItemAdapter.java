package abletive.presentation.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import abletive.businesslogic.blutil.UserTransformer;
import abletive.presentation.activity.MainActivity;
import abletive.vo.FollowUserVO;
import alandelip.abletivedemo.R;

/**
 * 用户头像列表适配器
 * Created by Alan on 2016/4/30.
 */
public class UserItemAdapter extends ArrayAdapter<FollowUserVO> {

    private int resource;

    public UserItemAdapter(Context context, int resource, List<FollowUserVO> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        FollowUserVO userVO = getItem(position);
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resource, null);
        } else {
            view = convertView;
        }

        ImageView avatar = (ImageView) view.findViewById(R.id.avatar);
        String avatarUrl = userVO.getAvatarUrl();
        if (avatarUrl.startsWith("<")) {
            avatarUrl = UserTransformer.fetchImg(avatarUrl);
        }
        MainActivity.IMAGE_CACHE.get(avatarUrl, avatar);
        Bitmap loadedImage = MainActivity.IMAGE_CACHE.get(avatarUrl).getData();

        TextView userName = (TextView) view.findViewById(R.id.username),
                description = (TextView) view.findViewById(R.id.description);
        userName.setText(userVO.getName());
        description.setText(userVO.getDescription());

        //根据头像设置背景和文字颜色
        View background = view.findViewById(R.id.user_item);
        Palette palette = Palette.from(loadedImage).generate();
        Palette.Swatch muteLight = palette.getLightMutedSwatch(),
                vibrantLight = palette.getLightVibrantSwatch();
        if (muteLight != null) {
            background.setBackgroundColor(muteLight.getRgb());
            userName.setTextColor(muteLight.getTitleTextColor());
            description.setTextColor(muteLight.getBodyTextColor());
        }
        if (vibrantLight != null) {
            background.setBackgroundColor(vibrantLight.getRgb());
            userName.setTextColor(vibrantLight.getTitleTextColor());
            description.setTextColor(vibrantLight.getBodyTextColor());
        }

        return view;
    }
}
