<%@page import="es.ulpgc.as.pablopf.chatting.models.Error"%>
<%@page import="es.ulpgc.as.pablopf.chatting.models.Chat"%>
<%@page import="es.ulpgc.as.pablopf.chatting.models.User"%>
<% 
    if(request.getSession().getAttribute("currentAccount") == null){
        request.getSession().setAttribute("ErrorPage", new Error().saveError("You do not have permissions to access. Please login or Sign Up."));
        response.sendRedirect("../ErrorPage.jsp");
    }else{
        Chat currentChat = (Chat) request.getSession().getAttribute("currentChat");
        if(currentChat == null){
            response.sendRedirect("index.jsp");
        }else{
            out.println("<h2>Chat: " + currentChat.getName() + "</h2>");
        }
    }
%>