<%@page import="models.Chat"%>
<%@page import="ejbs.stateful.ChatPackRemote"%>
<%@page import="models.Error"%>
<%
    if(request.getSession().getAttribute("currentAccount") == null){
        request.getSession().setAttribute("ErrorPage", new Error().saveError("You do not have permissions to access. Please login or Sign Up."));
        response.sendRedirect("../ErrorPage.jsp");
    }
%>

<div id="nav-container">
    <div class="bg"></div>
    
    <div class="button" tabindex="0">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </div>
    
    <div id="nav-content" tabindex="0">
        <ul>
            <li>
                <form method="post" action="CreateChat.jsp">
                    <input type="submit" value="Create a Chat">
                </form>
            </li>
            <li>
                <form method="post" action="OpenChat.jsp">
                    <input type="submit" value="Connect to Chat">
                </form>
            </li>
            
            <li>
                <form method="post" action="FrontController">
                    <input type="hidden" name="command" value="CleanHistoryCommand">
                    <input type="submit" value="Clean History">
                </form>
            </li>
            
            <li>
               Chats History:
            </li>
            
            <%
                ChatPackRemote chatPack = (ChatPackRemote) request.getSession().getAttribute("chatPackRemote");
                if(chatPack != null){
                    for(Chat c : chatPack.getAll()){
                        out.println("<li>");
                        out.println("<form method=\"post\" action=\"FrontController\">");
                        out.println("<input type=\"hidden\" name=\"nameChat\" value='"+ c.getName() +"'>");
                        out.println("<input type=\"hidden\" name=\"command\" value=\"ConnectWithCommand\">");
                        out.println("<input type=\"submit\" value='Chat: "+ c.getName() +"'>");
                        out.println("</form>");
                        out.println("</li>");
                    }
                   
                }
            %>
            
        </ul>
    </div>
</div>

