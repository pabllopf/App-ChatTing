package frontController.commands;
import ejbs.stateless.ChatHandler;
import models.Chat;
import models.LogMessage;

public class RefreshChatCommand extends AbstractCommand{
    
    @Override
    public void process() {
        logRemote.add(new LogMessage("RefreshChatCommand::process"));
        
        Chat currentChat = (Chat)request.getSession().getAttribute("currentChat");
        if(currentChat == null){
            currentChat = new ChatHandler().loadChat("publicroom");
        }else{
            currentChat = new ChatHandler().loadChat(currentChat.getName());
        }
        logRemote.add(new LogMessage("RefreshChatCommand::" + currentChat.getName()));
        request.getSession().setAttribute("currentChat", currentChat);
        forward("/Chat.jsp");
    } 
}
