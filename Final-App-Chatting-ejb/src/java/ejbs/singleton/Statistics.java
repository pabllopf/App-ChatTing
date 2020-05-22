package ejbs.singleton;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import models.LogMessage;

@Singleton
public class Statistics implements StatisticsRemote {
    HashMap<String, Integer> map;

    @PostConstruct
    @Override
    @Lock(LockType.WRITE)
    public void init() {
        System.out.println("Statistics::init - @PostConstruct Singleton LockType.WRITE");
        map = new HashMap<>();
        map.put("UserCountSignUp", 0);
        map.put("UserLogged", 0);
    }
    
    
    @Override
    @Lock(LockType.WRITE)
    public void countNewLoggin(){
        System.out.println("Statistics::countNewLoggin - @Override Singleton LockType.WRITE");
        if(map.isEmpty()){
            map.put("UserLogged", 1);
        }else{
            Integer n = map.get("UserLogged");
            map.put("UserLogged", n + 1);
        }
    }
    
    @Override
    @Lock(LockType.READ)
    public int getCounterOfLoggings(){
       
        System.out.println("Statistics::getCounterOfLoggings - @Override Singleton LockType.READ");
        if(map.isEmpty()){
            return 0;
        }else{
            return map.get("UserLogged"); 
        }
    }
    
    @Override
    @Lock(LockType.WRITE)
    public void countNewsSingUp(){
        
        System.out.println("Statistics::countNewsSingUp - @Override Singleton LockType.WRITE");
        if(map.isEmpty()){
            map.put("UserCountSignUp", 1);
        }else{
            Integer n = map.get("UserCountSignUp");
            map.put("UserCountSignUp", n + 1);
        }
    }
    
    @Override
    @Lock(LockType.READ)
    public int getCounterOfSingUp(){
        
        System.out.println("Statistics::getCounterOfSingUp - @Override Singleton LockType.READ");
        if(map.isEmpty()){
            return 0;
        }else{
            return map.get("UserCountSignUp"); 
        }
    }
 
     
    @PreDestroy
    @Override
    public void destroy() {
        System.out.println("Statistics::destroy - @PreDestroy Singleton");
    }
    
}
