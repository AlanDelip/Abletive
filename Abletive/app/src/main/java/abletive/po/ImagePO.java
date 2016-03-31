package abletive.po;

/**
 * 图片数据
 * Created by Alan on 2016/3/30.
 */
public class ImagePO {
    String url;
    double width;
    double height;

    public ImagePO(String url, double width, double height) {
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
