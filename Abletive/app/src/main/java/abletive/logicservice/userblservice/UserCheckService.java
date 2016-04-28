package abletive.logicservice.userblservice;

import abletive.vo.DailyCheckinVO;

/**
 * 用户签到服务接口
 * Created by Alan on 2016/4/27.
 */
public interface UserCheckService {

    /**
     * 签到
     * @param userID 用户ID
     * @return 签到VO
     */
    DailyCheckinVO checkIn(String userID);
}
