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
                    <h1>Staful Container</h1>
                    <%
                        StafulContainerRemote container = (StafulContainerRemote) InitialContext.doLookup("java:global/App-Chatting-Enterprise/App-Chatting-Enterprise-ejb/StafulContainer");
                        
                        Iterator it = container.getAll().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry e = (Map.Entry)it.next();
                            
                            out.println("<br>");
                            
                            out.println("<h2>Component: "+ e.getKey() +"</h2>");
                            out.println("<h3>JNDI: "+ e.getValue() +"</h3>");
                            
                            if(e.getKey().equals("messagePack")){
                                MessagePackRemote messagePack = (MessagePackRemote) request.getSession().getAttribute("messagePack");
                                out.println("<h4>Content: </h4>");
                                
                                for(Message message : messagePack.getMessages()){
                                    out.println("<h5>Message Selected To Delete: '"+ message.getContent() + "' by " + message.getUser() + " sended at " + message.getCreated_at() + "</h5>");
                                }
                            }
                            
                            out.println("<br>");
                            
                            if(e.getKey().equals("chatPackRemote")){
                                ChatPackRemote chatPack = (ChatPackRemote) request.getSession().getAttribute("chatPackRemote");
                                
                                for(Chat chat : chatPack.getAll()){
                                    out.println("<h5>Chat Visited by me: "+ chat.getName() + " </h5>");
                                }
                            }
                        }
                    %>
                </div>
            </main>
        </div>
    </body>
</html>