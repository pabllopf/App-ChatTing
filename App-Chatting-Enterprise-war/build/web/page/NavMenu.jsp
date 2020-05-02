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
        </ul>
    </div>
</div>

