package ejbs.singleton;

import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Singleton
public class StafulContainer implements StafulContainerRemote {
    HashMap<String, String> map;
    
    @PostConstruct
    @Override
    @Lock(LockType.WRITE)
    public void init() {
        System.out.println("StafulContainer::init - @PostConstruct Singleton LockType.WRITE");
        map = new HashMap<>();
    }
    
    @Override
    @Lock(LockType.WRITE)
    public void add(String name, String JNDI){
        System.out.println("StafulContainer::add - @Override Singleton LockType.WRITE");
        
        if(map.isEmpty()){
            map.put(name, JNDI);
        }else{
            if(map.get(name) == null){
                map.put(name, JNDI);
            }
        }
    }
    
    @Override
    @Lock(LockType.READ)
    public HashMap<String, String> getAll(){
        System.out.println("StafulContainer::getAll - @Override Singleton LockType.READ");
        return map;
    }
    
    @PreDestroy
    @Override
    public void destroy() {
        System.out.println("StafulContainer::destroy - @PreDestroy Singleton");
    }
}
