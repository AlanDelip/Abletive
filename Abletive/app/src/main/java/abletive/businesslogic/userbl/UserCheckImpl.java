package abletive.businesslogic.userbl;

import java.util.ArrayList;

import abletive.businesslogic.blutil.UserTransformer;
import abletive.businesslogic.internetbl.UserHttpImpl;
import abletive.logicservice.internetblservice.UserHttpService;
import abletive.logicservice.userblservice.UserCheckService;
import abletive.po.HttpDailyCheckinPO;
import abletive.vo.DailyCheckinVO;
import abletive.vo.UserVO;

/**
 * 用户签到逻辑
 * Created by Alan on 2016/4/27.
 */
public class UserCheckImpl implements UserCheckService {

    UserHttpService userBl;

    public UserCheckImpl() {
        userBl = new UserHttpImpl();
    }

    @Override
    public DailyCheckinVO checkIn(String userID) {
        HttpDailyCheckinPO httpDailyCheckinPO = userBl.dailyCheckin(userID);
        ArrayList<UserVO> userList = null;
        String msg = "", credit = "";
        if (httpDailyCheckinPO != null) {
            if (httpDailyCheckinPO.getSuccess() == 1) {
                msg = httpDailyCheckinPO.getMsg();
                credit = httpDailyCheckinPO.getCredits() + "";
                userList =
                        UserTransformer.getUserVOList(httpDailyCheckinPO.getCheckin_list());
            } else {
                msg = "已经签过到啦!";
                credit = "0";
            }
        }
        return new DailyCheckinVO(msg, credit, userList);
    }
}
