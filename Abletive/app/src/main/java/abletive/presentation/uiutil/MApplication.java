package abletive.presentation.uiutil;

import android.app.Application;
import android.content.Context;

import java.io.IOException;
import java.util.Properties;

/**
 * 全局可获取的数据区
 * Created by Alan on 2016/3/7.
 */
public class MApplication extends Application {

    private static Context context;
    private static String serverIP;

    public static Context getContext() {
        return context;
    }

    public static String getServerIP() {
        return serverIP;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        initContext();
        initHostIP();

    }

    private void initContext() {
        context = getApplicationContext();
    }

    private void initHostIP() {
        Properties IPAddress = new Properties();
        try {
            IPAddress.load(getContext().getAssets().open("ip_address.properties"));
            serverIP = IPAddress.getProperty("ip");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
