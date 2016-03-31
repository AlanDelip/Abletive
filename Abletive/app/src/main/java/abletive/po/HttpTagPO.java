package abletive.po;

import java.util.ArrayList;

/**
 * get_tag_index回复
 * Created by Alan on 2016/3/30.
 */
public class HttpTagPO {
    int count;
    ArrayList<TagPO> tags;

    public HttpTagPO(int count, ArrayList<TagPO> tags) {
        this.count = count;
        this.tags = tags;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<TagPO> getTags() {
        return tags;
    }

    public void setTags(ArrayList<TagPO> tags) {
        this.tags = tags;
    }
}
