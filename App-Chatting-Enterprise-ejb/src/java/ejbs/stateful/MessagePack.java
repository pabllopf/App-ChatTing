package ejbs.stateful;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import models.Message;

@Stateful
public class MessagePack implements MessagePackRemote {
    private ArrayList<Message> messages;
    
    @PostConstruct
    @Override
    public void init() {
        System.out.println("MessagePack::init - @PostConstruct Stateful");
        messages = new ArrayList<Message>();
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
        messages.add(message);
    }
    
    @Remove
    @Override
    public void remove(Message message) {
        System.out.println("MessagePack::remove - @Remove Stateful");
        messages.remove(message);
    }
    
    @Override
    public void confirm(Message message){
        System.out.println("MessagePack::confirm - @Override Stateful");
        //deletemessages od database
    }

    @PreDestroy
    @Override
    public void destroy() {
        System.out.println("MessagePack::destroy - @PreDestroy Stateful");
    }
}
