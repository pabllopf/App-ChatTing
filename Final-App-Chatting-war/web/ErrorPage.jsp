<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/ErrorStyle.css">
        <link rel="icon" href="resources/icon.png">
        <title>Error</title>
    </head>
    <body>
        <div class="container">
           <form method="post" action="FrontController">
               <jsp:include page="debug/CheckError.jsp" />
               
                <input type="hidden" name="command" value="ErrorLogCommand">
                <input type="submit" value="Okey">
           </form>
        </div>
    </body>
</html>
