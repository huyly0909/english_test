<%-- 
    Document   : Register
    Created on : Dec 13, 2016, 11:10:25 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" href="style.css">
        <script TYPE="text/javascript">
            function checkInput() {
                var ID = document.forms["myForm"]["CName"].value;
                var name = document.forms["myForm"]["CFName"].value;
                var pass = document.forms["myForm"]["CPass"].value;
                var repass = document.forms["myForm"]["Re-CPass"].value;
                var address = document.forms["myForm"]["CAddress"].value;
                var phone = document.forms["myForm"]["CPhone"].value;
                var cinumber = document.forms["myForm"]["CINumber"].value;
                if (checkID(ID) == 0 || checkPassword(pass, repass) == 0 || checkName(name) == 0 || checkPhone(phone) == 0 || checkAddress(address) == 0 || checkIN(cinumber) == 0) {
                    return false;
                } else {
                    return true;
                }
            }
            function checkID(ID) {
                if (ID == "") {
                    alert("ID not null");
                    return 0;
                } else if (ID.length > 20) {
                    alert("ID must less than 20 characters");
                    return 0;
                }
            }
            function checkName(name) {
                if (name == "") {
                    alert("Full Name not null");
                    return 0;
                } else if (name.length > 20) {
                    alert("Full Name must less than 20 characters");
                    return 0;
                }
            }
            function checkPassword(pass, repass) {
                if (pass == "" || repass == "") {
                    alert("Password and re-Password not null");
                    return 0;
                } else if (pass != repass) {
                    alert("Password not equals to Re-Password");
                    return 0;
                } else if (pass.length > 20 || pass.length < 6) {
                    alert("Password must less than 20 characters and greater than 6 characters");
                    return 0;
                }
            }
            function checkPhone(phone) {
                if (phone == "") {
                    alert("Phone Number not null");
                    return 0;
                } else if (phone.length > 11 || phone.length < 10) {
                    alert("Phone number have 10 or 11 numbers");
                    return 0;
                }
            }
            function checkAddress(address) {
                if (address == "") {
                    alert("Address Number not null");
                    return 0;
                } else if (address.length > 50) {
                    alert("Address have a lot of characters");
                    return 0;
                }
            }
            function checkIN(IN) {
                if (IN == "") {
                    alert("Identity Number not null");
                    return 0;
                } else if (IN.length != 9) {
                    alert("Identity number have 9 numbers");
                    return 0;
                }
            }

        </script>
    </head>
    <body>
        <%
            String check = (String) session.getAttribute("checkAccount");
        %>

        <div class="formRegister">
            <div class="editHeader">Register Form</div>
            <form method="post" action="check" name="myForm" onsubmit="return checkInput();">
                <div class="rowEdit removeSpace">
                    <div class="col1Register">
                        <div class="headerEdit">ID</div>
                        <div class="headerEdit">Password</div>
                        <div class="headerEdit">re-Password</div>
                        <div class="headerEdit">Full Name</div>
                        <div class="headerEdit">Phone</div>
                        <div class="headerEdit">Address</div>
                        <div class="headerEdit">Identity Number</div>

                        <%if (check == null) {
                            } else if (check.equals("exist")) {
                                out.print("<div class='sameAccount'>Account or IN already exist</div>");
                            }
                            session.setAttribute("checkAccount", "");
                        %>
                    </div>
                    <div class="col2Register">
                        <div class="valueEdit"><input type="text" class="editCarBlank" name="CName"></div>
                        <div class="valueEdit"><input type="password" class="editCarBlank" name="CPass"></div>
                        <div class="valueEdit"><input type="password" class="editCarBlank" name="Re-CPass"></div>
                        <div class="valueEdit"><input type="text" class="editCarBlank" name="CFName"></div>
                        <div class="valueEdit"><input type="number" class="editCarBlank" name="CPhone"></div>
                        <div class="valueEdit"><input type="text" class="editCarBlank" name="CAddress"></div>
                        <div class="valueEdit"><input type="number" class="editCarBlank" name="CINumber"></div>
                        <input type="hidden" value="Register" name="action">
                        <div class="buttonEdit2"><input type="submit" class="button buttonBlack" value="Register" style="font-size: 20px;"></div>
                        <a href="homePage.jsp"><div class="buttonEdit2"><input type="button" class="button buttonBlack" value="Cancel" style="font-size: 20px;"></div></a>
                    </div>
                </div>     
            </form>
        </div>

    </body>
</html>
