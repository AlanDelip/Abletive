package abletive.presentation.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import abletive.presentation.activity.MainActivity;
import abletive.presentation.activity.PostActivity;
import abletive.vo.PostListVO;
import alandelip.abletivedemo.R;
import cn.trinea.android.common.util.ListUtils;

/**
 * ViewPager的适配器
 * Created by Alan on 2016/4/19.
 */
public class ImagePagerAdapter extends RecyclingPagerAdapter {

    private Context context;
    private List<PostListVO> postVOList;

    private int size;
    private boolean isInfiniteLoop;

    public ImagePagerAdapter(Context context, List<PostListVO> postVOList) {
        this.context = context;
        this.postVOList = postVOList;
        this.size = ListUtils.getSize(postVOList);
        isInfiniteLoop = false;
    }

    @Override
    public int getCount() {
        // Infinite loop
        return isInfiniteLoop ? Integer.MAX_VALUE : ListUtils.getSize(postVOList);
    }

    /**
     * get really position
     *
     * @param position
     * @return
     */
    private int getPosition(int position) {
        return isInfiniteLoop ? position % size : position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup container) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.viewpager_item, null);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder = new ViewHolder();
        holder.imageView = (ImageView) view.findViewById(R.id.viewpager_image);
        MainActivity.IMAGE_CACHE.get(postVOList.get(getPosition(position)).getThumbUrl(), holder.imageView);
        holder.textView = (TextView) view.findViewById(R.id.viewpager_text);
        holder.textView.setText(postVOList.get(getPosition(position)).getTitle());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostActivity.newInstance(context, postVOList.get(getPosition(position)).toHashMap());
            }
        });
        view.setTag(holder);
        return view;
    }

    /**
     * @return the isInfiniteLoop
     */
    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    /**
     * @param isInfiniteLoop the isInfiniteLoop to set
     */
    public ImagePagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }

    private static class ViewHolder {

        ImageView imageView;
        TextView textView;
    }
}
