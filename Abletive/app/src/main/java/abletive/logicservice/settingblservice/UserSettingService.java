package abletive.logicservice.settingblservice;

import abletive.vo.UserSettingVO;

/**
 * 用户设置逻辑接口
 *
 * @author Alan
 */
public interface UserSettingService {
    /**
     * 获得用户设定
     *
     * @return 用户设定内容
     */
    UserSettingVO getUserSetting();
}
