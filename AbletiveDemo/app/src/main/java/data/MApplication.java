package data;

import android.app.Application;
import android.content.Context;

import alandelip.abletivedemo.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Alan on 2016/3/7.
 */
public class MApplication extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        CalligraphyConfig.initDefault("fonts/fzlt.ttf", R.attr.fontPath);
    }
}
