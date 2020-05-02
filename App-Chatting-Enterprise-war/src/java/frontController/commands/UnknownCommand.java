package frontController.commands;

public class UnknownCommand extends AbstractCommand{
    @Override
    public void process() {
        /*Error errorMessage = new Error().saveError("Unknown Command");
        request.getSession().setAttribute("ErrorPage", errorMessage);*/
        forward("/ErrorPage.jsp");
    }
}
