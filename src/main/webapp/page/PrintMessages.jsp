<%@page import="es.ulpgc.as.pablopf.chatting.models.Error"%>
<%@page import="es.ulpgc.as.pablopf.chatting.models.User"%>
<%@page import="es.ulpgc.as.pablopf.chatting.models.Message"%>
<%@page import="es.ulpgc.as.pablopf.chatting.models.Chat"%>
<% 
    if(request.getSession().getAttribute("currentAccount") == null){
        request.getSession().setAttribute("ErrorPage", new Error().saveError("You do not have permissions to access. Please login or Sign Up."));
        response.sendRedirect("../ErrorPage.jsp");
    }else{
        Chat currentChat = (Chat) request.getSession().getAttribute("currentChat");
        User currentAccount = (User) request.getSession().getAttribute("currentAccount");
        for(Message message : currentChat.getMessages()){
            if(message.getName().equals(currentAccount.getUser())){
                out.println("<form class =\"borderCool mymessage\">");
            }else{
                out.println("<form class =\"borderCool themessages\">");
            }

            out.println("<label class =\"userCool\">" + message.getName() + "</label>");
            out.println("<textarea class = \"textAreaCool\" readonly>"+ message.getMessage() +"</textarea>");
            out.println("<p class = \"textDateCool\">"+message.getCreated_at()+ "</p>");
            out.println("</form><br>");
        }
    }
%>