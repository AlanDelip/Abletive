package abletive.businesslogic.internetbl;

import abletive.logicservice.internetblservice.UserHttpService;
import abletive.po.UserPO;
import abletive.vo.SignupVO;

/**
 * 用户相关网络逻辑
 * @author Alan
 */
public class UserHttpImpl implements UserHttpService{
    @Override
    public UserPO signin(String userID, String password) {
        return null;
    }

    @Override
    public UserPO signup(SignupVO signupVO) {
        return null;
    }
}
