package abletive.presentation.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import abletive.presentation.activity.MainActivity;
import abletive.presentation.uiutil.ImageLibrary;
import abletive.vo.PostCollectionVO;
import alandelip.abletivedemo.R;

/**
 * 收藏列表适配
 * Created by Alan on 2016/5/5.
 */
public class CollectionListAdapter extends ArrayAdapter<PostCollectionVO> {
    int resourceID;

    public CollectionListAdapter(Context context, int resource, List<PostCollectionVO> objects) {
        super(context, resource, objects);
        this.resourceID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PostCollectionVO postCollectionVO = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceID, null);
        }

        ImageView thumb = (ImageView) convertView.findViewById(R.id.thumb);
        thumb.setImageBitmap(ImageLibrary.default_title_thumb);
        MainActivity.IMAGE_CACHE.get(postCollectionVO.getThumbUrl(), thumb);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(postCollectionVO.getTitle());

        TextView excerptView = (TextView) convertView.findViewById(R.id.description);
        excerptView.setText(postCollectionVO.getExcerpt());

        TextView author = (TextView) convertView.findViewById(R.id.author);
        author.setText(postCollectionVO.getAuthor());

        TextView time = (TextView) convertView.findViewById(R.id.time);
        time.setText(postCollectionVO.getTime());

        TextView views = (TextView) convertView.findViewById(R.id.views);
        views.setText(postCollectionVO.getViews());

        TextView comments = (TextView) convertView.findViewById(R.id.comments);
        comments.setText(postCollectionVO.getComments());

        TextView category = (TextView) convertView.findViewById(R.id.category);
        category.setText(postCollectionVO.getCategory());

        return convertView;
    }
}
