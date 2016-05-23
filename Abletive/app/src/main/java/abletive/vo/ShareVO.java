package abletive.vo;

import android.graphics.Bitmap;

/**
 * 分享列表item
 * Created by Alan on 2016/5/19.
 */
public class ShareVO {
    Bitmap shareBitmap;
    String shareName;

    public ShareVO(Bitmap shareBitmap, String shareName) {
        this.shareBitmap = shareBitmap;
        this.shareName = shareName;
    }

    public Bitmap getShareBitmap() {
        return shareBitmap;
    }

    public void setShareBitmap(Bitmap shareBitmap) {
        this.shareBitmap = shareBitmap;
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }
}
