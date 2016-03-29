package abletive.businesslogic.internetbl;

import java.util.ArrayList;

import abletive.logicservice.internetblservice.MessageHttpService;
import abletive.po.MessagePO;

/**
 * 消息相关网络逻辑
 * @author Alan
 */
public class MessageHttpImpl implements MessageHttpService{
    @Override
    public ArrayList<MessagePO> getMessage(String userID) {
        return null;
    }

    @Override
    public boolean sendMessage(String userID, String message) {
        return false;
    }
}
