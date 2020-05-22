package ejbs.stateful;

import java.util.ArrayList;
import javax.ejb.Remote;
import models.Message;

@Remote
public interface MessagePackRemote {
    public void init();
    public void passivate();
    public void active();
    public void add(Message message);
    public boolean containt(Message message);
    public ArrayList<Message> getMessages();
    public void cleanAll();
    public void destroy();
}
