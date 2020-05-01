<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/LoginStyle.css">
        <link rel="icon" href="resources/icon.png">
        <title>Login</title>
    </head>
    <body>
        <div class="container">
           <form method="post" action="FrontController">
                <label>User</label>
                <input type="text" name="userText">

                <label>Password</label>
                <input type="password" name="passwordText">

                <jsp:include page="debug/CheckError.jsp" />

                <input type="hidden" name="command" value="LoginCommand">
                <input type="submit" value="Login">
           </form>
               
            <form method="post" action="SignUp.jsp">
                <input type="submit" value="Sign Up">
            </form>
        </div>
    </body>
</html>