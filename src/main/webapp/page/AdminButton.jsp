<%@page import="es.ulpgc.as.pablopf.chatting.models.User"%>
<%@page import="es.ulpgc.as.pablopf.chatting.models.Error"%>
<%
    if(request.getSession().getAttribute("currentAccount") == null){
        request.getSession().setAttribute("ErrorPage", new Error().saveError("You do not have permissions to access. Please login or Sign Up."));
        response.sendRedirect("../ErrorPage.jsp");
    }else{
        User currentAccount = (User) request.getSession().getAttribute("currentAccount");
        out.println("<a href=\"AdminUser.jsp\"><div class=\"button-rigth\" tabindex=\"0\"><p><i class=\"fa fa-fw fa-user\"></i>" + currentAccount.getUser() + "</p></div></a>");
    }
%>