<%@page import="models.Chat"%>
<%@page import="models.Message"%>
<%@page import="ejbs.stateful.ChatPackRemote"%>
<%@page import="ejbs.stateful.MessagePackRemote"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="ejbs.singleton.StafulContainerRemote"%>
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
                    <h1>Configure time of log.</h1>
                    
                    <form method="post" action="FrontController">
                         <label>Time</label>
                        <input type="text" name="timeIntroduced">
                        
                            <jsp:include page="debug/CheckError.jsp" />
                         
                         <input type="hidden" name="command" value="ConfigLogCommand">
                         <input type="submit" value="Send">
                    </form>
                    
                </div>
            </main>
        </div>
    </body>
</html>