

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="css/login_style.css">
        <script src="jquery-3.2.1.min.js"/>
        <script TYPE="text/javascript">
            $(document).ready(function(){
                var isInvalidAccount = '${isInvalid}';
                var displayStyle = "display: ";
                displayStyle += isInvalidAccount ? "block" : "none";
                $("#invalidValidMessage").attr('style', displayStyle);
            });
        </script>
    </head>
    <body>
        <div class="body" ></div>
        <div class="grad"></div>
        <div class="header">
            <div>English <span>Test</span></div>
        </div>
        <br>
        <form method="post" action="login" name="loginForm" onsubmit="return checkInput()">
            <div class="login">
                <input type="text" placeholder="Username" name="userName" value="student" ><br>
                <input type="password" placeholder="Password" name="password" value="123456"><br>
                <input type='text' id="invalidValidMessage" class="invalidAccountMessage" readonly value='Username or password is incorrect!' style="display: none;"><br>
                <input type="hidden" value="login" name="action">
                <input type="submit" value="Login">
                <a href="Register.jsp"><div class="xxx">Register</div></a>
            </div>
        </form>
    </body>
</html>
