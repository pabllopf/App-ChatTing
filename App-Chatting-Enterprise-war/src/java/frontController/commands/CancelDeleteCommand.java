
package frontController.commands;

import ejbs.stateful.MessagePackRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CancelDeleteCommand extends AbstractCommand{

    @Override
    public void process() {
        MessagePackRemote packToDelete = (MessagePackRemote)request.getSession().getAttribute("messagePack");
        if(packToDelete != null){
            packToDelete.cleanAll();
            request.getSession().setAttribute("messagePack", null);
        }
        
        forward("/Chat.jsp");
    }
    
}
