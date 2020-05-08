
package frontController.commands;

import ejbs.stateful.MessagePackRemote;
import models.LogMessage;

public class CancelDeleteCommand extends AbstractCommand{

    @Override
    public void process() {
        logRemote.add(new LogMessage("CancelDeleteCommand::process"));
        
        MessagePackRemote packToDelete = (MessagePackRemote)request.getSession().getAttribute("messagePack");
        if(packToDelete != null){
            packToDelete.cleanAll();
            request.getSession().setAttribute("messagePack", null);
        }
        
        
        
        forward("/Chat.jsp");
    }
    
}
