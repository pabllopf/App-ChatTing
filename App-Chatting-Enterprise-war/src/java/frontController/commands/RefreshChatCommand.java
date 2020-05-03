package frontController.commands;
import ejbs.stateless.ChatHandler;
import models.Chat;

public class RefreshChatCommand extends AbstractCommand{
    
    @Override
    public void process() {
        Chat currentChat = (Chat)request.getSession().getAttribute("currentChat");
        if(currentChat == null){
            currentChat = new ChatHandler().loadChat("publicroom");
        }else{
            currentChat = new ChatHandler().loadChat(currentChat.getName());
        }
        request.getSession().setAttribute("currentChat", currentChat);
        forward("/Chat.jsp");
    } 
}
