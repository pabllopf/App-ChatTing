<%@page import="tables.TableLog"%>
<%@page import="ejbs.stateless.controllers.TableLogFacade"%>
<%@page import="models.LogMessage"%>
<%@page import="ejbs.singleton.LogRemote"%>
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
                    
                    <h1>Support Page</h1>
                    
                    <form method="post" action="FrontController">
                        <label>Write a message</label>
                        <input type="text" name="logMessage" placeholder="name...">
                            <jsp:include page="debug/CheckError.jsp" />
                        <input type="hidden" name="command" value="LogTableSaveCommand">
                        <input type="submit" value="Send">
                   </form>
                        
                    <form method="post" action="FrontController">
                        <input type="hidden" name="command" value="LogTableCleanCommand">
                        <input type="submit" value="Clean All Support Database">
                   </form>
                    
                    <%
                        TableLogFacade logTable = (TableLogFacade)InitialContext.doLookup("java:global/Final-App-Chatting/Final-App-Chatting-ejb/TableLogFacade!ejbs.stateless.controllers.TableLogFacade");
                        if(logTable != null){
                            for(TableLog log : logTable.findAllOrderById()){
                                out.println("<h3>" + log.getLogmessage() + "</h3>");
                            }
                        }else{
                            out.println("<h3> Dont init TableLogFacade ejb</h3>");
                        }
                    %>
                </div>
            </main>
        </div>
    </body>
</html>