<%@page import="models.Page"%>
<%@page import="ejbs.stateless.controllers.TableUsersFacade"%>
<%@page import="tables.TableUsers"%>
<%@page import="java.util.List"%>
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
                    <h1>Users List (10 for page)</h1>
                    <%
                        TableUsersFacade userHandler = (TableUsersFacade)InitialContext.doLookup("java:global/Final-App-Chatting/Final-App-Chatting-ejb/TableUsersFacade!ejbs.stateless.controllers.TableUsersFacade"); 
                        Page pagegood = (Page)request.getSession().getAttribute("numPage");
                        
                        out.println("<h2>Page " + pagegood.getNumpage() + " of " + userHandler.getLastPageUsers() + "</h2>");
                    %>
                    
                    
                    <%
                        for(int i = 0; i < userHandler.getLastPageUsers() ;i++){
                            int nump = i + 1;
                            out.println("<form method=\"post\" action=\"FrontController\">");
                            out.println("<input type=\"hidden\" name=\"command\" value=\"PageCommand\">");
                            out.println("<input type=\"hidden\" name=\"page\" value=\""+ nump+  "\">");
                            out.println("<input type=\"submit\" value=\""+ nump +"\" name=\""+ nump +"\">");
                            out.println("</form>");
                        }
                    %>
                    
                   
                    <form method="post" action="FrontController">
                        <input type="hidden" name="command" value="NextPageCommand">
                        <input type="submit" value="Next Page" name="Next">
                    </form>
                    
                    <form method="post" action="FrontController">
                        <input type="hidden" name="command" value="PreviousPageCommand">
                        <input type="submit" value="Previous Page" name="Previous">
                    </form>
                    
                    
                    
                    <%
                        List<TableUsers> users = userHandler.loadUsers(pagegood.getNumpage());
                        for(TableUsers user : users){
                            out.println("<h3>User("+  user.getId()+ ") with name '" + user.getName() + "'</h3>");
                        }
                    %>
                </div>
            </main>
        </div>
    </body>
</html>