package ejbs.stateful;

import javax.ejb.Remote;
import models.Message;

@Remote
public interface MessagePackRemote {
    public void init();
    public void passivate();
    public void active();
    public void add(Message message);
    public void remove(Message message);
    public void confirm(Message message);
    public void destroy();
}
