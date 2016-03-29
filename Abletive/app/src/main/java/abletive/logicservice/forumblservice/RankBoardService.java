package abletive.logicservice.forumblservice;

import java.util.ArrayList;

import abletive.vo.RankVO;

/**
 * 积分排行逻辑接口
 *
 * @author Alan
 */
public interface RankBoardService {
    /**
     * 获得积分榜列表
     *
     * @param page 第几页
     * @return 积分榜列表数组
     */
    ArrayList<RankVO> getRankList(int page);
}
