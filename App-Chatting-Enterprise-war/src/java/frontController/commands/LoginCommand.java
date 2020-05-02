package frontController.commands;
import ejbs.CurrentUser;
import models.Error;
import models.User;

public class LoginCommand extends AbstractCommand{
    
    @Override
    public void process() {
        User account = new User(request.getParameter("userText"), request.getParameter("passwordText"));
        if(new CurrentUser().login(account)){
            request.getSession().setAttribute("currentAccount", account);
            forward("/RefreshChat.jsp");
        }else{
            request.getSession().setAttribute("Error", new Error().saveError("Incorrect username or password."));
            forward("/Login.jsp");
        }
    }
}
