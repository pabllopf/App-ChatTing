package frontController.commands;

import ejbs.stateless.ChatHandler;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import models.Chat;
import models.User;
import models.Error;
import models.LogMessage;
import models.Message;

public class SendMessageCommand extends AbstractCommand{

    @Override
    public void process() { 
        logRemote.add(new LogMessage("SendMessageCommand::process"));
        
        Chat currentChat = (Chat) request.getSession().getAttribute("currentChat");
        User currentAccount = (User) request.getSession().getAttribute("currentAccount");
        
        String messageContent = request.getParameter("sendMessage");
        
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = dateTimeFormatter.format(localDateTime);
        
        if(messageContent.length() <= 0 || messageContent.length() > 1000){
            request.getSession().setAttribute("Error", new Error().saveError("Incorrect message: enter a message between 1 and 1000 characters."));
            forward("/Chat.jsp");
        }else{
            Message message = new Message(currentAccount.getUser(), messageContent, date);
            new ChatHandler().sendMessageTo(currentChat.getName(), message);
            logRemote.add(new LogMessage("SendMessageCommand::SendMessage::" + message.getContent() + ":in chat:" + currentChat.getName() + ":By:" + currentAccount.getUser()));
            forward("/RefreshChat.jsp");
        }
    } 
}