package ejbs.singleton;

import java.util.HashMap;
import java.util.LinkedList;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import models.LogMessage;

@Singleton
public class Log implements LogRemote {
    private LinkedList<LogMessage> logs;
    
    @PostConstruct
    @Override
    @Lock(LockType.WRITE)
    public void init() {
        System.out.println("Log::init - @PostConstruct Singleton LockType.WRITE");
        logs = new LinkedList<>();
    }
    
    @Override
    @Lock(LockType.WRITE)
    public void add(LogMessage logMessage){
        System.out.println("Log::add - @Override Singleton LockType.WRITE");
        logs.add(logMessage);
    }
    
    @Override
    @Lock(LockType.READ)
    public LinkedList<LogMessage> getAllLogs(){
        System.out.println("Log::getAllLogs - @Override Singleton LockType.READ");
        return logs;
    }
    
    @PreDestroy
    @Override
    public void destroy() {
        System.out.println("Log::destroy - @PreDestroy Singleton");
    }
}
