
package frontController.commands;

import ejbs.stateful.ChatPackRemote;

public class CleanHistoryCommand  extends AbstractCommand{

    @Override
    public void process() {
        ChatPackRemote packChats = (ChatPackRemote)request.getSession().getAttribute("chatPackRemote");
        if(packChats != null){
            packChats.cleanAll();
            request.getSession().setAttribute("chatPackRemote", null);
        }
        
        forward("/Chat.jsp");
    }
    
}
