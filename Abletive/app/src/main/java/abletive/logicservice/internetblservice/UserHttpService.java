package abletive.logicservice.internetblservice;

import abletive.po.HttpSignupPO;
import abletive.po.HttpUserPO;
import abletive.po.UserPO;
import abletive.vo.SignupVO;

/**
 * 用户相关网络逻辑接口
 *
 * @author Alan
 */
public interface UserHttpService {
    /**
     * 登录
     *
     * @param userID   用户ID
     * @param password 密码
     * @return 用户数据
     */
    HttpUserPO signin(String userID, String password);

    /**
     * 注册
     *
     * @param signupVO 注册信息
     * @return 用户数据
     */
    HttpSignupPO signup(SignupVO signupVO);

    /**
     * 获取指定用户信息
     *
     * @param userID 用户ID
     * @return 用户信息
     */
    UserPO getUserInfo(String userID);
}
