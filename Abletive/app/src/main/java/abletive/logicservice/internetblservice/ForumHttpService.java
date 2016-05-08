package abletive.logicservice.internetblservice;

import abletive.po.HttpRankListPO;

/**
 * 论坛相关的Http接口逻辑
 * Created by Alan on 2016/5/6.
 */
public interface ForumHttpService {

    /**
     * 获得排行榜
     *
     * @param limit 获取的个数
     * @return 排行榜数组原始数据
     */
    HttpRankListPO getRankList(int limit);

}
