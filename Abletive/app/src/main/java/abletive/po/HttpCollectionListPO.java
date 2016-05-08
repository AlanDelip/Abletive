package abletive.po;

import java.util.ArrayList;

/**
 * 收藏文章列表
 * Created by Alan on 2016/5/5.
 */
public class HttpCollectionListPO {
    int count;
    ArrayList<PostCollectionPO> collection_list;

    public HttpCollectionListPO(int count, ArrayList<PostCollectionPO> collection_list) {
        this.count = count;
        this.collection_list = collection_list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<PostCollectionPO> getCollection_list() {
        return collection_list;
    }

    public void setCollection_list(ArrayList<PostCollectionPO> collection_list) {
        this.collection_list = collection_list;
    }
}
