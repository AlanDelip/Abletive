package abletive.businesslogic.userbl;

import java.util.ArrayList;

import abletive.businesslogic.blutil.PostTransformer;
import abletive.businesslogic.blutil.UserTransformer;
import abletive.businesslogic.internetbl.UserHttpImpl;
import abletive.logicservice.internetblservice.UserHttpService;
import abletive.logicservice.userblservice.UserInfoService;
import abletive.po.CreditPO;
import abletive.po.FollowUserPO;
import abletive.po.HttpCollectionListPO;
import abletive.po.HttpCreditPO;
import abletive.po.HttpPersonalPagePO;
import abletive.po.HttpUserCommentPO;
import abletive.po.MembershipPO;
import abletive.po.PostCollectionPO;
import abletive.po.UserCommentListPO;
import abletive.vo.CreditListVO;
import abletive.vo.FollowUserVO;
import abletive.vo.MemberVO;
import abletive.vo.PersonalPageVO;
import abletive.vo.PostCollectionVO;
import abletive.vo.UserCommentVO;

/**
 * 用户具体信息逻辑
 * Created by Alan on 2016/4/29.
 */
public class UserInfoImpl implements UserInfoService {

    UserHttpService userBl;

    public UserInfoImpl() {
        userBl = new UserHttpImpl();
    }

    @Override
    public PersonalPageVO getPersonalPage(String userID, String currentUserID) {
        HttpPersonalPagePO httpPersonalPagePO =
                userBl.getPersonalPage(userID, currentUserID);
        if (httpPersonalPagePO != null) {

            //用户关注状态
            String followState = "";
            int followStateInt = httpPersonalPagePO.getFollow_status();
            if (followStateInt == 1) {
                followState = "未关注";
            } else if (followStateInt == 2) {
                followState = "已关注";
            } else {
                followState = "互相关注";
            }
            String postCount = "共" + httpPersonalPagePO.getPosts_count() + "篇";
            String postComment = "共" + httpPersonalPagePO.getComments_count() + "次";
            String credits = httpPersonalPagePO.getCredit() + "";
            String postCollection = "共" + httpPersonalPagePO.getCollects_count() + "篇";
            String shopOrders = "共" + httpPersonalPagePO.getOrders_count() + "笔";

            //会员信息
            MemberVO memberVO = null;
            MembershipPO membershipPO = httpPersonalPagePO.getMembership();
            if (membershipPO != null) {
                memberVO = new MemberVO(membershipPO.getId() + "", membershipPO.getUser_type(),
                        membershipPO.getStartTime(), membershipPO.getEndTime(),
                        membershipPO.getUser_status());
            }

            //设置成员天数
            String registerDays = "成为社区一员已经" + httpPersonalPagePO.getRegistered_days() + "天了";

            //设置性别
            String gender = "未知";
            if (httpPersonalPagePO.getGender().equals("male")) {
                gender = "男";
            } else if (httpPersonalPagePO.getGender().equals("female")) {
                gender = "女";
            }

            String avatarUrl = httpPersonalPagePO.getAvatar();
            avatarUrl = UserTransformer.transfer(avatarUrl);

            return new PersonalPageVO(avatarUrl, followState, httpPersonalPagePO.getFollowing_count(),
                    httpPersonalPagePO.getFollower_count(), postCount,
                    postComment, credits, postCollection, memberVO,
                    gender, registerDays, shopOrders,
                    httpPersonalPagePO.getUser_info());
        } else {
            return null;
        }

    }

    @Override
    public ArrayList<FollowUserVO> getFollowList(String userID, String currentUserID, String type, int page) {
        ArrayList<FollowUserPO> userPOList = userBl.getFollowList(userID, currentUserID, type, page);
        ArrayList<FollowUserVO> userVOList = new ArrayList<>();
        if (userPOList != null) {
            for (FollowUserPO po : userPOList) {
                userVOList.add(po.toFollowUserVO());
            }
        }
        return userVOList;
    }

    @Override
    public boolean follow(String userID, String currentUserID) {
        int state = userBl.follow(userID, currentUserID, "follow");
        if (state == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean unfollow(String userID, String currentUserID) {
        int state = userBl.follow(userID, currentUserID, "unfollow");
        if (state == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<PostCollectionVO> getPostCollectionList(String userID, int page) {
        HttpCollectionListPO httpCollectionListPO = userBl.getPostCollectionList(userID, page);
        if (httpCollectionListPO != null) {
            ArrayList<PostCollectionPO> postCollectionList = httpCollectionListPO.getCollection_list();
            return PostTransformer.getCollectionPostList(postCollectionList);
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<CreditListVO> getCreditList(String userID, int page) {
        HttpCreditPO httpCreditPO = userBl.getCreditList(userID, page);
        ArrayList<CreditListVO> creditList = new ArrayList<>();
        ArrayList<CreditPO> creditPOList = httpCreditPO.getCredit_list();
        for (CreditPO po : creditPOList) {
            creditList.add(po.toCreditListVO());
        }
        return creditList;
    }

    @Override
    public ArrayList<UserCommentVO> getUserCommentList(String userID, int page) {
        HttpUserCommentPO httpUserCommentPO = userBl.getUserCommentList(userID, page);
        ArrayList<UserCommentListPO> commentPOList = httpUserCommentPO.getComment_list();
        ArrayList<UserCommentVO> commentList = new ArrayList<>();
        for (UserCommentListPO po : commentPOList) {
            commentList.add(po.toUserCommentVO());
        }
        return commentList;
    }


}
