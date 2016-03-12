package widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import alandelip.abletivedemo.R;
import data.PostTitleVO;

/**
 * Created by Alan on 2016/3/7.
 */
public class PostTitleAdapter extends ArrayAdapter<PostTitleVO> {
    int resourceID;

    public PostTitleAdapter(Context context, int resource, List<PostTitleVO> objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PostTitleVO postTitle = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID, null);
        } else {
            view = convertView;
        }
        RoundImageView thumb = (RoundImageView) view.findViewById(R.id.thumb);
        thumb.setImageBitmap(postTitle.getThumb());

        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(postTitle.getTitle());

        TextView author = (TextView) view.findViewById(R.id.author);
        author.setText(postTitle.getAuthor());

        TextView time = (TextView) view.findViewById(R.id.time);
        time.setText(postTitle.getTime());

        TextView views = (TextView) view.findViewById(R.id.views);
        views.setText(postTitle.getViews());

        TextView comments = (TextView) view.findViewById(R.id.comments);
        comments.setText(postTitle.getComments() + "");

        TextView category = (TextView) view.findViewById(R.id.category);
        category.setText(postTitle.getCategory());

        return view;
    }
}
