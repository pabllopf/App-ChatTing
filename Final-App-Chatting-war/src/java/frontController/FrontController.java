package frontController;

import frontController.commands.AbstractCommand;
import frontController.commands.UnknownCommand;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InstantiationException, IllegalAccessException {
        AbstractCommand command= getCommand(request);
        command.init(getServletContext(), request, response);
        command.process();
    }
    
    private AbstractCommand getCommand(HttpServletRequest request) throws InstantiationException, IllegalAccessException
    {
        return (AbstractCommand) getCommandClass(request).newInstance();
    }
    
    private Class getCommandClass(HttpServletRequest request)
    {
         Class result;
        final String command = "frontController.commands." + (String)request.getParameter("command");
        try{
            result= Class.forName(command);
        }catch(ClassNotFoundException e) {
            result= UnknownCommand.class;
        }
        return result;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (InstantiationException | IllegalAccessException ex) {}
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (InstantiationException | IllegalAccessException ex) {}
    }
}
