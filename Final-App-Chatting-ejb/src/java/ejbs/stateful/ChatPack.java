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
import models.Chat;
import models.LogMessage;

@Stateful
public class ChatPack implements ChatPackRemote {
    private ArrayList<Chat> chats;
    
    @PostConstruct
    @Override
    public void init() {
        System.out.println("ChatPack::init - @PostConstruct Stateful");
        chats = new ArrayList<>();
    }

    @PrePassivate
    @Override
    public void passivate() {
        System.out.println("ChatPack::passivate - @PrePassivate Stateful");
    }

    @PostActivate
    @Override
    public void active() {
        System.out.println("ChatPack::active - @PostActivate Stateful");
    }
    
    @Remove
    @Override
    public void cleanAll() {
        System.out.println("ChatPack::remove::cleanAll - @Remove Stateful");
        chats.clear();
    }
    
    @PreDestroy
    @Override
    public void destroy() {
        System.out.println("ChatPack::destroy - @PreDestroy Stateful");
    }
    
    @Override
    public void add(Chat chat){
        System.out.println("ChatPack::add::chat::"+ chat.getName() +" - @Override Stateful");
        
        for(Chat c: chats){
            if(c.getName().equals(chat.getName())){
                return;
            }
        }
        
        chats.add(chat);
    }
    
    @Override
    public ArrayList<Chat> getAll(){
        System.out.println("ChatPack::getAll - @Override Stateful");
        return chats;
    }
}
