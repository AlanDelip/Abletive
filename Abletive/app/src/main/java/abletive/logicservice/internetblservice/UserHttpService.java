package abletive.logicservice.internetblservice;

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
    UserPO signin(String userID, String password);

    /**
     * 注册
     *
     * @param signupVO 注册信息
     * @return 用户数据
     */
    UserPO signup(SignupVO signupVO);
}
