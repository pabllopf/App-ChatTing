<%@page import="models.Error"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if(request.getSession().getAttribute("currentAccount") == null){
        request.getSession().setAttribute("ErrorPage", new Error().saveError("Error: please Login or Sign Up first."));
        response.sendRedirect("ErrorPage.jsp");
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/AdminUserStyle.css">
        <link rel="icon" href="resources/icon.png">
        <title>User</title>
    </head>
    <body>
        <div>
            <h2>Admin User</h2>
        </div>
        
        <form  method="post" action="RefreshChat.jsp">
            <input type="submit" value="Come Back">
        </form>
        
        <form method="post" action="FrontController">
           <input type="hidden" name="command" value="LogOutCommand">
           <input type="submit" value="Log Out">
        </form>
        
        <form method="post" action="Stadistics.jsp">
           <input type="submit" value="Statitics">
        </form>
        
        <form method="post" action="LogChatApp.jsp">
           <input type="submit" value="Log Of Process">
        </form>
        
        <form method="post" action="StafulContainer.jsp">
           <input type="submit" value="Staful Container Singlenton">
        </form>
        
        <form method="post" action="LogTime.jsp">
           <input type="submit" value="Config Log Time">
        </form>

        <form method="post" action="FrontController">
           <input type="hidden" name="command" value="fbjjrCommand">
           <input type="submit" value="Unknown Command">
        </form>
    </body>
</html>