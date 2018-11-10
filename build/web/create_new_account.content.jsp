<%-- 
    Document   : create_new_account.content
    Created on : Oct 28, 2018, 12:45:03 AM
    Author     : tinluu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quick Test</title>
        <link rel="stylesheet" href="css/style.css">
        <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
        <script TYPE="text/javascript">
            $(document).ready(function(){
                var isSuccessful = '${isSuccessful}';
                if (isSuccessful) {
                    alert("New question was created!");
                }
            });

        </script>
    </head>
    <body>
        ${topBtn}
        <div class="form-container header form-title">
            ${formHeader}
        </div>
        <div class="form-container body">
            <form method="post" action="general_controller" name="createForm">
                <table class="form-table">
                    <tr>
                        <td>
                            <span>Username</span>
                        </td>
                        <td>
                           <input type="text" value="username" name="user_name"  required><br>
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <span>Full Name</span>
                        </td>
                        <td>
                           <input type="text" value="new name" name="full_name"  required><br>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <span>Password</span>
                        </td>
                        <td>
                           <input type="password" name="password" value="123456" required><br>
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <span>Role</span>
                        </td>
                        <td>
                            <input type="radio" name="role_id" checked value="1"> Admin 
                           <input type="radio" name="role_id" value="2"> Teacher 
                           <input type="radio" name="role_id" value="3"> Candidate 
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <span>Age</span>
                        </td>
                        <td>
                           <input type="number" value="2" name="age"><br>
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <span>Address</span>
                        </td>
                        <td>
                           <input type="text" value="TP.HCM" name="address"><br>
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <span>Phone</span>
                        </td>
                        <td>
                           <input type="number" value="1234567890" name="phone"><br>
                        </td>
                    </tr>
                    <tr id="salary">
                        <td>
                            <span>Salary</span>
                        </td>
                        <td>
                           <input type="number" value="123456789" name="salary"><br>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="buttons">
                            <input type="hidden" value="create" name="action">
                            <input type="hidden" value="${type}" name="type">
                            <input type="submit" value="Create">
                        </td>
                    </tr>
                </table>
        </form>
        </div>
    </body>
</html>
