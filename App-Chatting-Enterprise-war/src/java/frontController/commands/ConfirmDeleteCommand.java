package frontController.commands;

import ejbs.stateful.MessagePackRemote;
import ejbs.stateless.ChatHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import models.Chat;
import models.Message;

public class ConfirmDeleteCommand extends AbstractCommand{

    @Override
    public void process() {
        MessagePackRemote packToDelete = (MessagePackRemote)request.getSession().getAttribute("messagePack");
        
        if(packToDelete == null){
            try {
                packToDelete = (MessagePackRemote) InitialContext.doLookup("java:global/App-Chatting-Enterprise/App-Chatting-Enterprise-ejb/MessagePack");
                request.getSession().setAttribute("messagePack", packToDelete);
            } catch (NamingException ex) {
                Logger.getLogger(MessageToDeleteCommand.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
        
        Chat currentChat = (Chat)request.getSession().getAttribute("currentChat");
        
        if(packToDelete != null){
            for(Message mess : packToDelete.getMessages()){
                new ChatHandler().deleteMessage(mess, currentChat);
            }
            request.getSession().setAttribute("messagePack", null);
        }
        
        forward("/RefreshChat.jsp");
    }
}
