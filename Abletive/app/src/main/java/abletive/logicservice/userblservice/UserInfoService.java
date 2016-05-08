package abletive.logicservice.userblservice;

import java.util.ArrayList;

import abletive.vo.CreditListVO;
import abletive.vo.FollowUserVO;
import abletive.vo.PersonalPageVO;
import abletive.vo.PostCollectionVO;
import abletive.vo.UserCommentVO;

/**
 * 用户信息服务
 * Created by Alan on 2016/4/22.
 */
public interface UserInfoService {

    /**
     * 获得个人资料详情
     *
     * @param userID        查看的用户ID
     * @param currentUserID 目前登录的用户ID
     */
    PersonalPageVO getPersonalPage(String userID, String currentUserID);

    /**
     * 获得指定用户的关注者列表
     *
     * @param userID        查看的用户ID
     * @param currentUserID 目前的用户ID
     * @param type          粉丝还是关注者（following/follower）
     * @param page          第几页
     * @return 用户对象列表
     */
    ArrayList<FollowUserVO> getFollowList(String userID, String currentUserID, String type, int page);

    /**
     * 关注
     *
     * @param userID        关注的用户ID
     * @param currentUserID 当前用户ID
     * @return 是否成功
     */
    boolean follow(String userID, String currentUserID);

    /**
     * 取消关注
     *
     * @param userID        关注的用户ID
     * @param currentUserID 当前用户ID
     * @return 是否成功
     */
    boolean unfollow(String userID, String currentUserID);

    /**
     * 获得收藏文章列表
     *
     * @param userID 用户ID
     * @param page   页码
     * @return 收藏文章列表
     */
    ArrayList<PostCollectionVO> getPostCollectionList(String userID, int page);

    /**
     * 获得积分列表
     *
     * @param userID 用户ID
     * @param page   页码
     * @return 积分列表
     */
    ArrayList<CreditListVO> getCreditList(String userID, int page);

    /**
     * 获得个人主页评论列表
     *
     * @param userID 用户ID
     * @param page   页码
     * @return 评论列表
     */
    ArrayList<UserCommentVO> getUserCommentList(String userID, int page);


}
