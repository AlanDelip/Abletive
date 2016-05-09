package abletive.presentation.tasks;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import abletive.businesslogic.blutil.UserTransformer;
import abletive.businesslogic.postbl.PostImpl;
import abletive.logicservice.postblservice.PostService;

/**
 * 图片任务
 * Created by Alan on 2016/4/19.
 */
public class ImageTask extends AsyncTask<Void, Void, Void> {

    String imageUrl;
    ImageTaskCallBack imageTaskCallBack;
    PostService postBl;
    Bitmap image;

    public ImageTask(String imageUrl) {
        imageUrl = UserTransformer.transfer(imageUrl);
        this.imageUrl = imageUrl;
        postBl = new PostImpl();
    }

    @Override
    protected Void doInBackground(Void... params) {
        image = postBl.getThumbnail(imageUrl);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        imageTaskCallBack.setImage(image);
    }

    public void setImageTaskCallBack(ImageTaskCallBack imageTaskCallBack) {
        this.imageTaskCallBack = imageTaskCallBack;
    }

    public interface ImageTaskCallBack {
        void setImage(Bitmap image);
    }
}
