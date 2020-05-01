<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(request.getSession().getAttribute("currentAccount") != null){
        response.sendRedirect("RefreshChat.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/IndexStyle.css">
        <link rel="icon" href="resources/icon.png">
        <title>ChatTing</title>
    </head>
    <body>
        <div class="container">
            <form  method="post" action="Login.jsp">
                <input class="btn" type="submit" value="Welcome To ChatTing">
            </form>
        </div> 
    </body>
</html>
