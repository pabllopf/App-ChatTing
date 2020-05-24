<%@page import="ejbs.stateful.MessagePackRemote"%>
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
                    <jsp:include page="page/NameChat.jsp"/>
                    
                     <%
                        MessagePackRemote messagePack = (MessagePackRemote) request.getSession().getAttribute("messagePack");
                        if(messagePack != null){     
                            out.println("<form method=\"post\" action=\"DeleteMessages.jsp\">");  
                            out.println("<input style=\"background-color: red;\" type=\"submit\" value=\"See Messages To Delete\" name=\"see\">");
                            out.println("</form>");
                        }  
                    %> 

                    <form method="post" action="FrontController">
                        <input type="hidden" name="command" value="SendMessageCommand">
                        <input type="submit" value="Send Message" name="send">
                        
                        <jsp:include page="debug/CheckError.jsp"/>

                        <input type="text" value="" name="sendMessage">
                    </form>
                  

                    <jsp:include page="page/PrintMessages.jsp"/>
                </div>
            </main>
        </div>
    </body>
</html>