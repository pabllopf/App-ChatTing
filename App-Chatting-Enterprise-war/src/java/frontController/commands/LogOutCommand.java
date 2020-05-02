package frontController.commands;

public class LogOutCommand extends AbstractCommand{
    
    @Override
    public void process() {
        request.getSession().setAttribute("currentAccount", null);
        forward("/index.jsp");
    }
}
