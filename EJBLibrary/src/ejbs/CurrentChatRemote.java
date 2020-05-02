package ejbs;

import java.util.ArrayList;
import javax.ejb.Remote;
import models.Message;

@Remote
public interface CurrentChatRemote {
    public void init();
    public void destroy(); 
    public ArrayList<Message> getMessages(String nameChat);
    public void sendMessage(String nameChat, Message message);
}
