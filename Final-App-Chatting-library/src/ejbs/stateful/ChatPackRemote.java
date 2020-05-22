package ejbs.stateful;
import java.util.ArrayList;
import javax.ejb.Remote;
import models.Chat;

@Remote
public interface ChatPackRemote {
    public void init();
    public void passivate();
    public void active();
    public void cleanAll();
    public void destroy();
    
     public void add(Chat chat);
     public ArrayList<Chat> getAll();
}
