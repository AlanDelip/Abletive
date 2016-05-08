package abletive.businesslogic.forumbl;

import java.util.ArrayList;

import abletive.businesslogic.internetbl.ForumHttpImpl;
import abletive.logicservice.forumblservice.RankBoardService;
import abletive.logicservice.internetblservice.ForumHttpService;
import abletive.po.HttpRankListPO;
import abletive.po.RankPO;
import abletive.vo.RankVO;

/**
 * 积分排行榜逻辑
 *
 * @author Alan
 */
public class RankBoardImpl implements RankBoardService {

    private ForumHttpService forumBl;

    public RankBoardImpl() {
        forumBl = new ForumHttpImpl();
    }

    @Override
    public ArrayList<RankVO> getRankList(int limit) {
        HttpRankListPO httpRankListPO = forumBl.getRankList(limit);
        ArrayList<RankVO> rankVOList = new ArrayList<>();
        ArrayList<RankPO> rankPOList = httpRankListPO.getCredit_list();
        if (rankPOList != null) {
            for (RankPO po : rankPOList) {
                rankVOList.add(po.toRankVO());
            }
        }
        return rankVOList;
    }
}
