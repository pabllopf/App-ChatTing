package frontController.commands;

import ejbs.singleton.LogRemote;
import ejbs.stateful.ChatPackRemote;
import ejbs.stateful.MessagePackRemote;
import ejbs.stateless.ChatHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Error;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import models.Chat;
import models.LogMessage;

public class ConnectWithCommand  extends AbstractCommand{
    @Override
    public void process() {
        logRemote.add(new LogMessage("ConnectWithCommand::process"));
        
        String nameChatToConnect = request.getParameter("nameChatToConnect");
        if(nameChatToConnect == null){
            nameChatToConnect = request.getParameter("nameChat");
        }
        
        
        Pattern pattern = Pattern.compile("[A-Z\\s\\d\\W]+.*");
        Matcher matcher= pattern.matcher(nameChatToConnect);
        
        if(nameChatToConnect.length() <= 0 || nameChatToConnect.length() > 25){
            request.getSession().setAttribute("Error", new Error().saveError("Incorrect name: enter a name between 1 and 25 characters."));
            forward("/OpenChat.jsp");
            return;
        }
        
        if(matcher.find()){
            request.getSession().setAttribute("Error", new Error().saveError("Incorrect name: enter only lowercase letters without spaces."));
            forward("/OpenChat.jsp"); 
            return;
        }
        
        if(new ChatHandler().existsChat(nameChatToConnect) == false){
              request.getSession().setAttribute("Error", new Error().saveError("Chat dont exists."));
              forward("/OpenChat.jsp");
              return;
        }

        Chat currentChat = new ChatHandler().loadChat(nameChatToConnect);
        request.getSession().setAttribute("currentChat", currentChat);
        
        
        ChatPackRemote chatPack = (ChatPackRemote)request.getSession().getAttribute("chatPackRemote");
        
        if(chatPack == null){
            try {
                chatPack = (ChatPackRemote) InitialContext.doLookup("java:global/Final-App-Chatting/Final-App-Chatting-ejb/ChatPack!ejbs.stateful.ChatPackRemote");
                request.getSession().setAttribute("chatPackRemote", chatPack);
                stafulContainer.add("chatPackRemote", "java:global/Final-App-Chatting/Final-App-Chatting-ejb/ChatPack!ejbs.stateful.ChatPackRemote");
            } catch (NamingException ex) {
                Logger.getLogger(MessageToDeleteCommand.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
        
        if(chatPack != null){
            chatPack.add(currentChat);
        }
        
        logRemote.add(new LogMessage("ConnectWith::Chat::" + currentChat.getName()));
        forward("/RefreshChat.jsp");
    }
    
}
