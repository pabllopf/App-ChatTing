package es.ulpgc.as.pablopf.chatting.controllers.frontController;


import es.ulpgc.as.pablopf.chatting.controllers.frontController.commands.UnknownCommand;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import es.ulpgc.as.pablopf.chatting.models.ConsoleColors;

public abstract class AbstractCommand {
    protected ServletContext context; 
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    
    public void init(ServletContext context, HttpServletRequest request, HttpServletResponse response)
    {
        this.context = context;
        this.request = request;
        this.response = response;
    }
   
    abstract public void process();
    
     public void forward(String target){
         try{
            RequestDispatcher dp= context.getRequestDispatcher(target);
           
            dp.forward(request, response);
        }
        catch (ServletException | IOException ex) {
            Logger.getLogger(UnknownCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
