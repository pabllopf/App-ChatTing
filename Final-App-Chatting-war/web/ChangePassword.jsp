<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/SignUpStyle.css">
        <link rel="icon" href="resources/icon.png">
        <title>Change Password</title>
    </head>
    <body>
        <div class="container">
           <form method="post" action="FrontController">
               <label>Password</label>
               <input type="password" name="passwordText">
               
               <label>Repeat Password</label>
               <input type="password" name="passwordrepeatText">
               
               <jsp:include page="debug/CheckError.jsp" />
                             
               <input type="hidden" name="command" value="ChangePasswordCommand">
               <input type="submit" value="Change">
           </form>
               
            <form method="post" action="Chat.jsp">
                <input type="submit" value="Come Back">
            </form>
        </div>
    </body>
</html>