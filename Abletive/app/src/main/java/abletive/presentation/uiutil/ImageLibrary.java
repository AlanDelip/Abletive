package abletive.presentation.uiutil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import alandelip.abletivedemo.R;

/**
 * 静态加载默认图片
 * Created by Alan on 2016/3/30.
 */
public class ImageLibrary {

    /**
     * 默认文章图片
     */
    public final static Bitmap default_title_thumb = BitmapFactory.decodeResource(MApplication.getContext().getResources(), R.drawable.thumbnail_sample);

}
