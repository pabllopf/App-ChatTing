
package frontController.commands;

import java.util.List;
import models.LogMessage;
import models.Page;
import tables.TableUsers;

public class NextPageCommand  extends AbstractCommand{
    @Override
    public void process() {
        logRemote.add(new LogMessage("NextPageCommand::process"));
        
        Page pagegood = (Page)request.getSession().getAttribute("numPage");
        if(pagegood != null){
            int num = pagegood.getNumpage();
            
            if(num + 1 <= userHandler.getLastPageUsers()){
                pagegood.setNumpage(num + 1);
            }
        }
        
        request.getSession().setAttribute("numPage", new Page(pagegood.getNumpage()));
        forward("/Users.jsp");
    }
}