package ejbs.stateless;

import javax.ejb.Remote;
import models.Chat;
import models.Message;

@Remote
public interface ChatHandlerRemote {
    public void init();
    public boolean existsChat(String nameChat);
    public boolean createChat(String nameChat);
    public Chat loadChat(String nameChat);
    public boolean sendMessageTo(String nameChat, Message message);
    public void destroy();
}
