package frontController.commands;
public class LogTableCleanCommand  extends AbstractCommand{
    @Override
    public void process() {
        logTable.cleanLog();
        forward("/Support.jsp");
    }
}
