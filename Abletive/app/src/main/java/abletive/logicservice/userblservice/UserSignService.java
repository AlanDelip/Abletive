package abletive.logicservice.userblservice;

import abletive.vo.SignupVO;
import abletive.vo.UserVO;

/**
 * 用户登录、登出、注册相关接口
 *
 * @author Alan
 */
public interface UserSignService {

    /**
     * 登录
     *
     * @param userID   用户ID
     * @param password 密码
     * @return 用户信息
     */
    UserVO signin(String userID, String password);

    /**
     * 登出
     *
     * @return 是否成功登出
     */
    boolean signout();

    /**
     * 注册
     *
     * @param signupVO 注册信息
     * @return 用户信息
     */
    UserVO signup(SignupVO signupVO);

}
