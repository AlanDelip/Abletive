package abletive.businesslogic.blutil;

import android.app.Activity;

import java.util.ArrayList;

import abletive.logicservice.postblservice.ListService;
import abletive.vo.TypeListVO;

/**
 * 逻辑信息保存中转类，协助策略模式
 */
public class ClientLogic {
    /**
     * 逻辑信息的单例
     */
    private static ClientLogic clientLogic = new ClientLogic();

    /**
     * 列表服务
     */
    private ListService listService;

    /**
     * 类别列表
     */
    private ArrayList<TypeListVO> typeList;

    private Activity MainActivity;

    private boolean isSignup;

    public Activity getMainActivity() {
        return MainActivity;
    }

    public void setMainActivity(Activity mainActivity) {
        MainActivity = mainActivity;
    }

    private ClientLogic() {
    }

    public static ClientLogic getInstance() {
        return clientLogic;
    }

    public ListService getListService() {
        return listService;
    }

    public void setListService(ListService listService) {
        this.listService = listService;
    }

    public void setTypeList(ArrayList<TypeListVO> typeList) {
        this.typeList = typeList;
    }

    public void setSignup(boolean signup) {
        this.isSignup = signup;
    }

    public boolean isSignup() {
        return isSignup;
    }
}
