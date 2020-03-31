<%@page import="es.ulpgc.as.pablopf.chatting.models.Error"%>
<% 
    Error error = (Error) request.getSession().getAttribute("Error"); 
    if(error != null){
        out.println("<label class=\"label danger\">"+ error.getMessage() +"</label>");
        request.getSession().setAttribute("Error", null);
    }
    
    Error errorPage = (Error) request.getSession().getAttribute("ErrorPage"); 
    if(errorPage != null){
        out.println("<label class=\"label danger\">"+ errorPage.getMessage() +"</label>");
        request.getSession().setAttribute("ErrorPage", null);
    }
%>
