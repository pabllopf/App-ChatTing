<%@page import="java.util.ArrayList"%>
<%@page import="ejbs.stateful.MessagePackRemote"%>
<%@page import="models.Error"%>
<%@page import="models.User"%>
<%@page import="models.Message"%>
<%@page import="models.Chat"%>
<% 
    MessagePackRemote messagePack = (MessagePackRemote) request.getSession().getAttribute("messagePack");
    ArrayList<Message> messagesTemp = new ArrayList();
    if(messagePack != null){
        for(Message message : messagePack.getMessages()){
            out.println("<form class =\"borderCool mymessage\">");
            out.println("<label class =\"userCool\">" + message.getUser()+ "</label>");
            out.println("<textarea class = \"textAreaCool\" readonly>"+ message.getContent()+"</textarea>");
            out.println("<p class = \"textDateCool\">"+message.getCreated_at()+ "</p>");
            out.println("</form><br>");
        }
    }
%>
