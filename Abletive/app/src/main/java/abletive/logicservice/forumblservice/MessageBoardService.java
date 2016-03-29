package abletive.logicservice.forumblservice;

import java.util.ArrayList;

import abletive.vo.MessageBoardVO;

/**
 * 留言板逻辑接口
 *
 * @author Alan
 */
public interface MessageBoardService {
    /**
     * 获得留言板内容
     *
     * @param page 第几页
     * @return 留言板数组
     */
    public ArrayList<MessageBoardVO> getMessageBoard(int page);

    /**
     * 留言
     *
     * @param message 留言内容
     * @return 是否留言成功
     */
    public boolean leaveMessage(String message);
}
