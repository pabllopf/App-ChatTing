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
        <link rel="stylesheet" href="css/OpenChat.css">
        <link rel="icon" href="resources/icon.png">
        <title>Open Chat</title>
    </head>
    <body>
        <div class="container">
            <form method="post" action="FrontController">
                <label>Name Of Chat</label>
                <input type="text" name="nameChatToConnect" placeholder="name...">
                     <jsp:include page="debug/CheckError.jsp" />
                <input type="hidden" name="command" value="ConnectWithCommand">
                <input type="submit" value="Connect">
            </form>

            <form method="post" action="RefreshChat.jsp">
                <input type="submit" value="Come Back">
            </form>
        </div>
    </body>
</html>