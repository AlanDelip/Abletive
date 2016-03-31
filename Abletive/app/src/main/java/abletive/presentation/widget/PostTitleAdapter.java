package abletive.presentation.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import abletive.presentation.uiutil.ImageLibrary;
import abletive.vo.PostListVO;
import alandelip.abletivedemo.R;
import httpservice.HttpImpl;

/**
 * 文章标题列表适配器
 * Created by Alan on 2016/3/7.
 */
public class PostTitleAdapter extends ArrayAdapter<PostListVO> {
    int resourceID;

    public PostTitleAdapter(Context context, int resource, List<PostListVO> objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PostListVO postTitle = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID, null);
        } else {
            view = convertView;
        }

        ImageView thumb = (ImageView) view.findViewById(R.id.thumb);
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

    /**
     * 异步获取图片
     *
     * @author Alan
     * @version 1.0
     */
    class ImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... thumbUrl) {
            if (thumbUrl[0] != null) {
                if (thumbUrl[0].length() == 0) {
                    return new HttpImpl().getThumbnail(thumbUrl[0]);
                }
            }
            return ImageLibrary.default_title_thumb;
        }
    }
}
