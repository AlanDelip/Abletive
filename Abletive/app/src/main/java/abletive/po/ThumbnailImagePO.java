package abletive.po;

/**
 * 文章缩略图数据
 * Created by Alan on 2016/3/30.
 */
public class ThumbnailImagePO {
    ImagePO full;
    ImagePO thumbnail;
    ImagePO medium;
    ImagePO large;

    public ThumbnailImagePO(ImagePO full, ImagePO thumbnail, ImagePO medium, ImagePO large) {
        this.full = full;
        this.thumbnail = thumbnail;
        this.medium = medium;
        this.large = large;
    }

    public ImagePO getFull() {
        return full;
    }

    public void setFull(ImagePO full) {
        this.full = full;
    }

    public ImagePO getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ImagePO thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ImagePO getMedium() {
        return medium;
    }

    public void setMedium(ImagePO medium) {
        this.medium = medium;
    }

    public ImagePO getLarge() {
        return large;
    }

    public void setLarge(ImagePO large) {
        this.large = large;
    }
}
