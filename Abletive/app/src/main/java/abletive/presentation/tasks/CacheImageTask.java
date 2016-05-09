package abletive.presentation.tasks;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import abletive.presentation.activity.MainActivity;

/**
 * 从IMAGE_CACHE中寻找已经储存的bitmap
 * Created by Alan on 2016/5/9.
 */
public class CacheImageTask extends AsyncTask<Void, Void, Void> {

    private String url;
    private Bitmap bitmap;
    private CacheImageTaskCallBack cacheImageTaskCallBack;

    public CacheImageTask(String url) {
        this.url = url;
    }

    @Override
    protected Void doInBackground(Void... params) {
        bitmap = MainActivity.IMAGE_CACHE.get(url).getData();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (bitmap != null) {
            cacheImageTaskCallBack.updateImage(bitmap);
        }
    }

    public void setCacheImageTaskCallBack(CacheImageTaskCallBack cacheImageTaskCallBack) {
        this.cacheImageTaskCallBack = cacheImageTaskCallBack;
    }

    public interface CacheImageTaskCallBack {
        /**
         * 已经找到cache中保存的图片，对主界面进行更新
         *
         * @param loadedImage 加载完成的图片
         */
        void updateImage(Bitmap loadedImage);
    }
}
