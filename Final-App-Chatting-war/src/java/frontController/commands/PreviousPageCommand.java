package frontController.commands;

import java.util.List;
import models.LogMessage;
import models.Page;
import tables.TableUsers;

public class PreviousPageCommand extends AbstractCommand{
    @Override
    public void process() {
        logRemote.add(new LogMessage("PreviousPageCommand::process"));
        
        Page pagegood = (Page)request.getSession().getAttribute("numPage");
        if(pagegood != null){
            int num = pagegood.getNumpage();
            
            if(num - 1 >= 1 ){
                pagegood.setNumpage(num - 1);
            }
        }
        
        request.getSession().setAttribute("numPage", new Page(pagegood.getNumpage()));
        forward("/Users.jsp");
    }
}