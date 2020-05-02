package ejbs;

import database.ChatHandler;
import database.MessageHandler;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import models.Message;

@Stateful
public class CurrentChat implements CurrentChatRemote {

    @PostConstruct
    @Override
    public void init(){
        System.out.println("Chat::init - @PostConstruct stateful");
    }
    
    @Override
    public ArrayList<Message> getMessages(String nameChat) {
        System.out.println("Chat::getMessages - @Override stateful");
        return new MessageHandler().getAllMessages(nameChat);
    }
    
    @Override
    public void sendMessage(String nameChat, Message message) {
        System.out.println("Chat::sendMessage - @Override stateful");
        new MessageHandler().create(message, nameChat);
    }
    
    @PreDestroy
    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
