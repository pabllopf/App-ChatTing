package es.ulpgc.as.pablopf.chatting.controllers.frontController.commands;

import es.ulpgc.as.pablopf.chatting.controllers.frontController.AbstractCommand;
import es.ulpgc.as.pablopf.chatting.models.Chat;
import es.ulpgc.as.pablopf.chatting.models.Error;
import es.ulpgc.as.pablopf.chatting.models.Message;
import es.ulpgc.as.pablopf.chatting.controllers.databaseHandler.ChatHandler;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewChatCommand extends AbstractCommand{
    @Override
    public void process() {
        ChatHandler chatModel = new ChatHandler();
        
        String nameOfChat = request.getParameter("nameChat");
        
        Chat currentChat = new Chat(nameOfChat, new ArrayList<Message>());
        
        Pattern pattern = Pattern.compile("[A-Z\\s\\d\\W]+.*");
        Matcher matcher= pattern.matcher(nameOfChat);
        
        if(nameOfChat.length() <= 0 || nameOfChat.length() > 25){
            request.getSession().setAttribute("Error", new Error().saveError("Incorrect name: enter a name between 1 and 25 characters."));
            forward("/CreateChat.jsp"); 
            return;
        }
        
        if(matcher.find()){
            request.getSession().setAttribute("Error", new Error().saveError("Incorrect name: enter only lowercase letters without spaces."));
            forward("/CreateChat.jsp"); 
            return;
        }
        
        if(chatModel.exists(nameOfChat)){
              request.getSession().setAttribute("Error", new Error().saveError("Incorrect name: a chat with this name already exists."));
              forward("/CreateChat.jsp");
              return;
        }
        
        request.getSession().setAttribute("currentChat", currentChat);
        chatModel.createNewChat(nameOfChat);
        forward("/RefreshChat.jsp");
    }    
}
