package es.ulpgc.as.pablopf.chatting.controllers.frontController.commands;

import es.ulpgc.as.pablopf.chatting.controllers.frontController.AbstractCommand;
import es.ulpgc.as.pablopf.chatting.models.Chat;
import es.ulpgc.as.pablopf.chatting.models.Error;
import es.ulpgc.as.pablopf.chatting.controllers.databaseHandler.ChatHandler;
import es.ulpgc.as.pablopf.chatting.controllers.databaseHandler.MessageHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectWithCommand  extends AbstractCommand{

    @Override
    public void process() {
        ChatHandler chatModel = new ChatHandler();
        MessageHandler messageModel = new MessageHandler();
        
        String nameChatToConnect = request.getParameter("nameChatToConnect");
        
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
        
        if(!chatModel.exists(nameChatToConnect)){
              request.getSession().setAttribute("Error", new Error().saveError("Incorrect name: a chat with this name dont exists."));
              forward("/OpenChat.jsp");
              return;
        }
        
        Chat currentChat = new Chat();
        currentChat.setName(nameChatToConnect);
        currentChat.setMessages(messageModel.getAllMessages(nameChatToConnect));
        
        request.getSession().setAttribute("currentChat", currentChat);
        forward("/RefreshChat.jsp");
    }
}
