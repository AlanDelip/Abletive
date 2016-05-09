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
import abletive.presentation.uiutil.ImageLibrary;
import abletive.vo.PostListVO;
import alandelip.abletivedemo.R;
//import httpservice.HttpImpl;

/**
 * 文章标题列表适配器
 * Created by Alan on 2016/3/7.
 */
public class PostListAdapter extends ArrayAdapter<PostListVO> {
    int resourceID;

    public PostListAdapter(Context context, int resource, List<PostListVO> objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PostListVO postListVO = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID, null);
        } else {
            view = convertView;
        }
        ImageView thumb = (ImageView) view.findViewById(R.id.thumb);
        thumb.setImageBitmap(ImageLibrary.default_title_thumb);
        String thumbUrl = postListVO.getThumbUrl();
        thumbUrl = UserTransformer.transfer(thumbUrl);

        MainActivity.IMAGE_CACHE.get(thumbUrl, thumb);

        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(postListVO.getTitle());

        TextView description = (TextView) view.findViewById(R.id.description);
        String descriptionText = postListVO.getDescription();
        if (descriptionText != null) {
            if (descriptionText.length() >= 3) {
                description.setText(postListVO.getDescription().substring(3));
            }
        }

        TextView author = (TextView) view.findViewById(R.id.author);
        author.setText(postListVO.getAuthor());

        TextView time = (TextView) view.findViewById(R.id.time);
        time.setText(postListVO.getTime());

        TextView views = (TextView) view.findViewById(R.id.views);
        views.setText(postListVO.getViews());

        TextView comments = (TextView) view.findViewById(R.id.comments);
        comments.setText(postListVO.getComments());

        TextView category = (TextView) view.findViewById(R.id.category);
        category.setText(postListVO.getCategory());
        return view;
    }
}
