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
        new ImageTask(thumb, postListVO.getThumbUrl()).execute();

        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(postListVO.getTitle());

        TextView description = (TextView) view.findViewById(R.id.description);
        description.setText(postListVO.getDescription());

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

    /**
     * 异步获取图片
     *
     * @author Alan
     * @version 1.0
     */
    class ImageTask extends AsyncTask<Void, Void, Bitmap> {

        private ImageView imageView;
        private String thumbUrl;

        public ImageTask(ImageView imageView, String thumbUrl) {
            this.imageView = imageView;
            this.thumbUrl = thumbUrl;
        }

        @Override
        protected Bitmap doInBackground(Void... param) {
            if (thumbUrl != null) {
                if (thumbUrl.length() != 0) {
//                    return new HttpImpl().getThumbnail(thumbUrl);
                }
            }
            return ImageLibrary.default_title_thumb;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
            notifyDataSetChanged();
        }
    }
}
