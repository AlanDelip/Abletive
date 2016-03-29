package abletive.logicservice.forumblservice;

import java.util.ArrayList;

import abletive.vo.MemConVO;
import abletive.vo.MemberVO;

/**
 * 会员逻辑接口
 *
 * @author Alan
 */
public interface MemberService {
    /**
     * 获取会员内容列表
     *
     * @param page 第几页
     * @return 会员内容列表数组
     */
    ArrayList<MemConVO> getMemConList(int page);

    /**
     * 获取会员内容
     *
     * @param memberID
     * @return 会员内容
     */
    MemberVO getMemContent(String memberID);
}
