package frontController.commands;

import ejbs.stateless.ChatHandler;
import models.Error;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.Chat;

public class ConnectWithCommand  extends AbstractCommand{

    @Override
    public void process() {
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
        
        if(new ChatHandler().existsChat(nameChatToConnect) == false){
              request.getSession().setAttribute("Error", new Error().saveError("Chat dont exists."));
              forward("/OpenChat.jsp");
              return;
        }

        Chat currentChat = new ChatHandler().loadChat(nameChatToConnect);
        request.getSession().setAttribute("currentChat", currentChat);
        forward("/RefreshChat.jsp");
    }
}
