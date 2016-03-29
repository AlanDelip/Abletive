package abletive.logicservice.internetblservice;

import java.util.ArrayList;

import abletive.po.MessagePO;

/**
 * 消息相关网络逻辑接口
 *
 * @author Alan
 */
public interface MessageHttpService {

    /**
     * 从网络获得消息
     *
     * @param userID 获得消息的用户ID
     * @return 消息列表
     */
    ArrayList<MessagePO> getMessage(String userID);

    /**
     * 发送消息
     *
     * @param userID  对方userID
     * @param message 发送消息内容
     * @return 是否成功发送
     */
    boolean sendMessage(String userID, String message);
}
