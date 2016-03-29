package abletive.logicservice.messageblservice;

/**
 * 发送消息接口
 *
 * @author Alan
 */
public interface MessageSenderService {
    /**
     * 发送消息
     *
     * @param userID  发送的用户ID
     * @param message 发送的消息内容
     * @return 是否发送成功
     */
    boolean sendMessage(String userID, String message);

}
