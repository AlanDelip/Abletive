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
        ViewHolder viewHolder = null;
        FollowUserVO userVO = getItem(position);
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(resource, null);
            viewHolder = new ViewHolder();
            viewHolder.background = convertView.findViewById(R.id.user_item);
            viewHolder.backgroundImage = (ImageView) convertView.findViewById(R.id.background);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            viewHolder.userName = (TextView) convertView.findViewById(R.id.username);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            //保存ViewHolder
            convertView.setTag(viewHolder);
        }

        viewHolder.userName.setText(userVO.getName());
        viewHolder.description.setText(userVO.getDescription());

        //处理头像缓存
        String avatarUrl = userVO.getAvatarUrl();
        if (avatarUrl.startsWith("<")) {
            avatarUrl = UserTransformer.fetchImg(avatarUrl);
        }
        MainActivity.IMAGE_CACHE.get(avatarUrl, viewHolder.avatar);
        MainActivity.IMAGE_CACHE.get(avatarUrl, viewHolder.backgroundImage);

//        //根据头像设置背景和文字颜色
//            viewHolder.backgroundImage.setImageBitmap(loadedImage);
//            Palette palette = Palette.from(loadedImage).generate();
//            Palette.Swatch muteLight = palette.getLightMutedSwatch(),
//                    vibrantLight = palette.getLightVibrantSwatch();
//            if (muteLight != null) {
//                viewHolder.background.setBackgroundColor(muteLight.getRgb());
//                viewHolder.userName.setTextColor(muteLight.getTitleTextColor());
//                viewHolder.description.setTextColor(muteLight.getBodyTextColor());
//            }
//            if (vibrantLight != null) {
//                viewHolder.background.setBackgroundColor(vibrantLight.getRgb());
//                viewHolder.userName.setTextColor(vibrantLight.getTitleTextColor());
//                viewHolder.description.setTextColor(vibrantLight.getBodyTextColor());
//            }

        return convertView;
    }

    private static class ViewHolder {
        View background;
        ImageView backgroundImage;
        ImageView avatar;
        TextView userName;
        TextView description;
    }
}
