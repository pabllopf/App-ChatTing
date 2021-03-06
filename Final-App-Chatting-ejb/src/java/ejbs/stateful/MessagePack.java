package ejbs.stateful;

import ejbs.singleton.LogRemote;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import models.LogMessage;
import models.Message;

@Stateful
public class MessagePack implements MessagePackRemote {
    private ArrayList<Message> messages;
    
    @PostConstruct
    @Override
    public void init() {
        System.out.println("MessagePack::init - @PostConstruct Stateful");
        messages = new ArrayList<>();
        
   
    }

    @PrePassivate
    @Override
    public void passivate() {
        System.out.println("MessagePack::passivate - @PrePassivate Stateful");
    }

    @PostActivate
    @Override
    public void active() {
        System.out.println("MessagePack::active - @PostActivate Stateful");
    }

    @Override
    public void add(Message message) {
        System.out.println("MessagePack::add - @Override Stateful");
        
        for(Message mes: messages){
            if(mes.getId().equals(message.getId())){
                return;
            }
        }
        
        messages.add(message);
        
    }
    
    @Override
    public boolean containt(Message message) {
        System.out.println("MessagePack::containt::message:: "+ message.getContent() +" - @Override Stateful");
        return messages.contains(message);
    }
    
    @Override
    public ArrayList<Message> getMessages() {
        System.out.println("MessagePack::getMessages - @Override Stateful");
        return messages;
    }
    
    @Remove
    @Override
    public void cleanAll() {
        System.out.println("MessagePack::remove::cleanAll - @Remove Stateful");
        messages.clear();
    }
    
    @PreDestroy
    @Override
    public void destroy() {
        System.out.println("MessagePack::destroy - @PreDestroy Stateful");
    }
}
