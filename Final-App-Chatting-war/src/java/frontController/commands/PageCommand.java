
package frontController.commands;

import java.util.List;
import models.LogMessage;
import models.Page;
import tables.TableUsers;

public class PageCommand  extends AbstractCommand{
    @Override
    public void process() {
        logRemote.add(new LogMessage("PageCommand::process"));
        
        String num = request.getParameter("page");
        
        request.getSession().setAttribute("numPage", new Page(Integer.parseInt(num)));
        forward("/Users.jsp");
    }
}