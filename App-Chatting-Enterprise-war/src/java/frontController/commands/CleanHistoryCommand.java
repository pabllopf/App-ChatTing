
package frontController.commands;

import ejbs.stateful.ChatPackRemote;
import models.LogMessage;

public class CleanHistoryCommand  extends AbstractCommand{
    @Override
    public void process() {
       logRemote.add(new LogMessage("CleanHistoryCommand::process"));
        
        ChatPackRemote packChats = (ChatPackRemote)request.getSession().getAttribute("chatPackRemote");
        if(packChats != null){
            packChats.cleanAll();
            request.getSession().setAttribute("chatPackRemote", null);
        }
        
        
        
        forward("/Chat.jsp");
    }
    
}
