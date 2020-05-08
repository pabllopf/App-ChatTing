package ejbs.singleton;

import java.util.LinkedList;
import javax.ejb.Remote;
import models.LogMessage;

@Remote
public interface LogRemote {
    public void init();
    public void destroy();
    
    public void add(LogMessage logMessage);
    public LinkedList<LogMessage> getAllLogs();
}
