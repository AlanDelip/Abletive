package widget;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import alandelip.abletivedemo.R;
import data.PostTitle;

/**
 * Created by Alan on 2016/3/7.
 */
public class PostTitleAdapter extends ArrayAdapter<PostTitle> {
    int resourceID;

    public PostTitleAdapter(Context context, int resource, List<PostTitle> objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PostTitle postTitle = getItem(position);
        Log.d("Abletive", "getView: " + position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID, null);
        } else {
            view = convertView;
        }
        RoundImageView avatar = (RoundImageView) view.findViewById(R.id.avatar);
        avatar.setImageBitmap(postTitle.getAvatar());

        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(postTitle.getTitle());

        TextView author = (TextView) view.findViewById(R.id.author);
        author.setText(postTitle.getAuthor());

        TextView time = (TextView) view.findViewById(R.id.time);
        time.setText(postTitle.getTime());

        TextView viewerNum = (TextView) view.findViewById(R.id.viewerNum);
        viewerNum.setText(postTitle.getViewerNum() + "");

        return view;
    }
}
