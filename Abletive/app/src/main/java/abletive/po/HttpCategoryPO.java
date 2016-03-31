package abletive.po;

import java.util.ArrayList;

/**
 * get_category_index回复
 * Created by Alan on 2016/3/30.
 */
public class HttpCategoryPO {
    int count;
    ArrayList<CategoryPO> categories;

    public HttpCategoryPO(int count, ArrayList<CategoryPO> categories) {
        this.count = count;
        this.categories = categories;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<CategoryPO> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<CategoryPO> categories) {
        this.categories = categories;
    }
}
