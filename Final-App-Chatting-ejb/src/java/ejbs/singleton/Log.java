package ejbs.singleton;

import java.util.HashMap;
import java.util.LinkedList;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import models.LogMessage;

@Singleton
public class Log implements LogRemote {
    private LinkedList<LogMessage> logs;
    
    private int size;
    
    private int mss;
    
    @Resource
    private TimerService timerLog;
    
    @PostConstruct
    @Override
    @Lock(LockType.WRITE)
    public void init() {
        System.out.println("Log::init - @PostConstruct Singleton LockType.WRITE");
        logs = new LinkedList<>();
        size = 0;
    }
    
    @Override
    @Lock(LockType.WRITE)
    public void add(LogMessage logMessage){
        System.out.println("Log::add - @Override Singleton LockType.WRITE");
        logs.add(logMessage);
    }
    
    
    @Override
    @Schedule(second="*/5", minute="*", hour="*")
    @Lock(LockType.WRITE)
    public void addLogMessage(Timer timer){
        if(size == logs.size()){
            this.add(new LogMessage("Log::addLogMessage - @Schedule Singleton LockType.WRITE timer 5s"));
            System.out.println("Log::addLogMessage - @Schedule Singleton LockType.WRITE timer 5s");
        }else{
            size = logs.size();
        }
    }
    
    @Override
    @Lock(LockType.WRITE)
    @Timeout
    public void timeService(Timer timer){
        System.out.println("Log::timeService - @Timeout Singleton LockType.WRITE timer at " + mss + "ms");
        this.add(new LogMessage("Log::timeService - @Schedule Singleton LockType.WRITE timer at " + mss + "ms"));
    } 
    
    @Override
    @Lock(LockType.WRITE)
    public void setTimeToTimeServide(int ms){
        System.out.println("Log::setTimeToTimeServide - @Override Singleton LockType.WRITE timer config set to " + ms + " ms");
        this.add(new LogMessage("Log::setTimeToTimeServide - @Override Singleton LockType.WRITE timer config set to " + ms + " ms"));
        timerLog.createSingleActionTimer(ms, new TimerConfig());
        mss = ms;
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
