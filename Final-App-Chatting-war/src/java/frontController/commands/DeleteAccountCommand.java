package frontController.commands;

import models.User;

public class DeleteAccountCommand  extends AbstractCommand{
    @Override
    public void process() {
        User currentAccount = (User) request.getSession().getAttribute("currentAccount");
        request.getSession().setAttribute("currentAccount", null);
        userHandler.removeThisUser(currentAccount);
        forward("/Login.jsp");
    }
}
