package frontController.commands;

import ejbs.singleton.LogRemote;
import ejbs.stateful.MessagePackRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import models.Chat;
import models.LogMessage;
import models.Message;
import models.User;

public class MessageToDeleteCommand extends AbstractCommand{
    @Override
    public void process() {
        logRemote.add(new LogMessage("MessageToDeleteCommand::process"));
        
        MessagePackRemote packToDelete = (MessagePackRemote)request.getSession().getAttribute("messagePack");
        
        if(packToDelete == null){
            try {
                packToDelete = (MessagePackRemote) InitialContext.doLookup("java:global/App-Chatting-Enterprise/App-Chatting-Enterprise-ejb/MessagePack");
                request.getSession().setAttribute("messagePack", packToDelete);
            } catch (NamingException ex) {
                Logger.getLogger(MessageToDeleteCommand.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
        
        String mess = request.getParameter("mess");
        Chat currentChat = (Chat)request.getSession().getAttribute("currentChat");
        User user = (User) request.getSession().getAttribute("currentAccount");
        
        if(packToDelete != null){
            Message message = null;
            for(Message mes : currentChat.getMessages()){
                if(mes.getId().equals(mess)){
                    message = mes;                    
                }
            }
            
            if(message != null){
                packToDelete.add(message);
            }
        }
        
        forward("/Chat.jsp");
    }
}
