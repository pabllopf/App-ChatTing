package frontController.commands;

import ejbs.stateless.ChatHandler;
import models.Error;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.Chat;

public class NewChatCommand extends AbstractCommand{
    @Override
    public void process() {
        String nameOfChat = request.getParameter("nameChat");
        
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
        
        if(new ChatHandler().existsChat(nameOfChat)){
              request.getSession().setAttribute("Error", new Error().saveError("Incorrect name: a chat with this name already exists."));
              forward("/CreateChat.jsp");
              return;
        }
        
        if(new ChatHandler().createChat(nameOfChat)){
            Chat currentChat = new ChatHandler().loadChat(nameOfChat);
            request.getSession().setAttribute("currentChat", currentChat);
            forward("/RefreshChat.jsp");
        }else{
            request.getSession().setAttribute("Error", new Error().saveError("Error To create the new chat."));
            forward("/CreateChat.jsp");
        }
    }    
}
