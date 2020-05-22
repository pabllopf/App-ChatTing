package ejbs.singleton;

import java.util.LinkedList;
import javax.ejb.Remote;
import javax.ejb.Timer;
import models.LogMessage;

@Remote
public interface LogRemote {
    public void init();
    public void destroy();
    
    public void add(LogMessage logMessage);
    public LinkedList<LogMessage> getAllLogs();
    public void addLogMessage(Timer timer);
    public void timeService(Timer time);
    public void setTimeToTimeServide(int ms);
}
