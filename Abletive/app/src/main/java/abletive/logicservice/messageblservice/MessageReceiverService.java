package abletive.logicservice.messageblservice;

import java.util.ArrayList;

import abletive.vo.MessageVO;

/**
 * 接收消息接口
 *
 * @author Alan
 */
public interface MessageReceiverService {
    /**
     * 接收消息
     *
     * @param userID 接收的用户ID
     * @return 消息数组
     */
    ArrayList<MessageVO> getMessage(String userID);

}
