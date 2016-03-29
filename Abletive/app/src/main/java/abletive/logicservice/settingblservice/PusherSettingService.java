package abletive.logicservice.settingblservice;

import abletive.vo.PusherVO;

/**
 * 推送设置接口
 *
 * @author Alan
 */
public interface PusherSettingService {
    /**
     * 获得推送设置
     *
     * @return 推送设置内容
     */
    PusherVO getPusherSetting();
}
