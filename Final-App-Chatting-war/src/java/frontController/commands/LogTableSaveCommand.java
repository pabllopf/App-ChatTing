package frontController.commands;

import models.LogMessage;

public class LogTableSaveCommand  extends AbstractCommand{
    @Override
    public void process() {
        String message = request.getParameter("logMessage");
        logTable.saveLog(new LogMessage(message));
        forward("/Support.jsp");
    }
    
}
