package frontController.commands;

import ejbs.CurrentChat;
import models.Chat;

public class RefreshChatCommand extends AbstractCommand{
    
    @Override
    public void process() {
        Chat currentChat = (Chat)request.getSession().getAttribute("currentChat");
        if(currentChat == null){
            currentChat = new Chat("publicroom", new CurrentChat().getMessages("publicroom"));
        }else{
            currentChat = new Chat(currentChat.getName(), new CurrentChat().getMessages(currentChat.getName()));
        }
        request.getSession().setAttribute("currentChat", currentChat);
        forward("/Chat.jsp");
    } 
}
