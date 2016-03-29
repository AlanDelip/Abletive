package abletive.businesslogic.userbl;

import abletive.logicservice.userblservice.UserSignService;
import abletive.vo.SignupVO;
import abletive.vo.UserVO;

/**
 * 用户登录、登出、注册逻辑
 *
 * @author Alan
 */
public class UserSignImpl implements UserSignService{
    @Override
    public UserVO signin(String userID, String password) {
        return null;
    }

    @Override
    public boolean signout() {
        return false;
    }

    @Override
    public UserVO signup(SignupVO signupVO) {
        return null;
    }
}
