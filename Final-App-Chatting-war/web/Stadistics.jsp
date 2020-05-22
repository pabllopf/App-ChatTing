<%@page import="ejbs.singleton.StatisticsRemote"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="models.Error"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if(request.getSession().getAttribute("currentAccount") == null){
        request.getSession().setAttribute("ErrorPage", new Error().saveError("Error: please Login or Sign Up first."));
        response.sendRedirect("ErrorPage.jsp");
    }
    
    response.setIntHeader("Refresh", 15);
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/ChatStyle.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="icon" href="resources/icon.png">
        <title>ChatTing</title>
    </head>
    <body>  
        <div class="page">   
            <jsp:include page="clock/Clock.jsp"/>
            <jsp:include page="page/NavMenu.jsp"/>
            <jsp:include page="page/AdminButton.jsp"/>
            
            <main>
                <div>
                    <form  method="post" action="RefreshChat.jsp">
                        <input type="submit" value="Come Back">
                    </form>
                    <h1>Statistics</h1>
                    <%
                        StatisticsRemote statistics = (StatisticsRemote) InitialContext.doLookup("java:global/App-Chatting-Enterprise/App-Chatting-Enterprise-ejb/Statistics");
                        out.println("<h3>Number of users logged: " + statistics.getCounterOfLoggings() + "</h3>");
                        out.println("<h3>Number of registered users: " + statistics.getCounterOfSingUp()+ "</h3>");
                    %>
                </div>
            </main>
        </div>
    </body>
</html>