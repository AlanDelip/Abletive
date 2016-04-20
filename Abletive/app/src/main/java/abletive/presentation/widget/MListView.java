package abletive.presentation.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 适配ScrollView的ListView
 * Created by Alan on 2016/4/19.
 */
public class MListView extends ListView {
    public MListView(Context context) {
        super(context);
    }

    public MListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
