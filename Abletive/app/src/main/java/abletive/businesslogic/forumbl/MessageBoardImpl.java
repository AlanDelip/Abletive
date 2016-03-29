package abletive.businesslogic.forumbl;

import java.util.ArrayList;

import abletive.logicservice.forumblservice.MessageBoardService;
import abletive.vo.MessageBoardVO;

/**
 * 留言板逻辑
 *
 * @author Alan
 */
public class MessageBoardImpl implements MessageBoardService{
    @Override
    public ArrayList<MessageBoardVO> getMessageBoard(int page) {
        return null;
    }

    @Override
    public boolean leaveMessage(String message) {
        return false;
    }
}
