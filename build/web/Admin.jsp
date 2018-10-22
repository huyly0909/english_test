<%-- 
    Document   : Admin
    Created on : Dec 9, 2016, 11:54:14 PM
    Author     : Administrator
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <link rel="stylesheet" href="style.css">
        <script TYPE="text/javascript">
            function checkInput() {
                var name = document.forms["addCarForm"]["name"].value;
                var price = document.forms["addCarForm"]["price"].value;
                var discount = document.forms["addCarForm"]["discount"].value;
                var seat = document.forms["addCarForm"]["seat"].value;
                var color = document.forms["addCarForm"]["color"].value;
                if (checkName(name) == 0 || checkPrice(price) == 0 || checkDiscount(discount) == 0 || checkSeat(seat) == 0 || checkColor(color) == 0) {
                    return false;
                } else {
                    return true;
                }
            }
            function checkName(name) {
                if (name == "") {
                    alert("Car name not null");
                    return 0;
                } else if (name.length > 20) {
                    alert("Car name must less than 20 characters");
                    return 0;
                }
            }
            function checkPrice(price) {
                if (price == "") {
                    alert("Price not null");
                    return 0;
                } else if (price < 50) {
                    alert("Price at least $50");
                    return 0;
                }
            }
            function checkDiscount(discount) {
                if (discount >= 100 || discount < 0) {
                    alert("Invalid! 0 < Discount < 100");
                    return 0;
                }
            }
            function checkSeat(seat) {
                if (seat == "") {
                    alert("A number of seats is not null");
                    return 0;
                } else if (seat >= 50 || seat < 1) {
                    alert("Not correct a number of seats!");
                    return 0;
                }
            }
            function checkColor(color) {
                if (color == "") {
                    alert("color not null");
                    return 0;
                }
            }
            function checkEdit() {
                var pass = document.forms["EditForm"]["AdPass"].value;
                var repass = document.forms["EditForm"]["Re-AdPass"].value;
                var phone = document.forms["EditForm"]["AdPhone"].value;
                var address = document.forms["EditForm"]["AdAddress"].value;
                var name = document.forms["EditForm"]["AdFName"].value;
                if (checkFName(name) == 0 || checkPassword(pass,repass) == 0 || checkPhone(phone) == 0 || checkAddress(address) == 0) {
                    return false;
                } else {
                    return true;
                }
            }
            function checkPassword(pass,repass) {
                if (pass != repass) {
                    alert("Password not equals to Re-Password");
                    return false;
                }else if(pass.length>20 || pass.length<6){
                    alert("Password must less than 20 characters and greater than 6 characters");
                    return 0;
                }
            }
            function checkPhone(phone) {
                if (phone != "") {
                    if (phone.length > 11 || phone.length < 10) {
                        alert("Phone number have 10 or 11 numbers");
                        return 0;
                    }
                }
            }
            function checkAddress(address) {
                if (address != "") {
                    if (address.length > 50) {
                        alert("Address have a lot of characters");
                        return 0;
                    }
                }
            }
            function checkFName(name) {
                if (name != "") {
                    if (name.length > 20) {
                        alert("Full Name must less than 20 characters");
                        return 0;
                    }
                }
            }

        </script>
    </head>
    <body>
        <%  //declare
            String connectionURL = "jdbc:mysql://localhost:3306/carrental?user=root;password=";
            Connection connection = null;
            Statement statement = null;
            Statement statement2 = null;
            ResultSet rs = null;
            ResultSet rs2 = null;
        %>
        <%
            String option = null;
            option = request.getParameter("option");
            if (option == null) {
                option = (String) session.getAttribute("option");
            }
        %>
        <jsp:useBean id="account" scope="session" class="storeAccount.Account" />
        <!-- -->
        <div class="topHome">

            <div class='topHomeL'><p>Welcome MR.<jsp:getProperty name="account" property="name"/></p></div>

            <div class='topHomeR'>
                <a href='homePage.jsp'><div class="buttonLogin buttonColor">Home</div></a>
                <a href='Login.jsp'><div class="buttonLogin buttonColor">Logout</div></a>

            </div>

        </div>
        <div class="adBody removeSpace">

            <div class="adOptions">
                <div class="adOption">
                    <form method="post" action="Admin.jsp">
                        <input type="hidden" name="option" value="Rent">
                        <input type="submit" class="button buttonPink" value="Rent" >
                    </form>
                </div>
                <div class="adOption">
                    <form method="post" action="Admin.jsp">
                        <input type="hidden" name="option" value="Car">
                        <input type="submit" class="button buttonPink" value="Car" >
                    </form>
                </div>
                <div class="adOption">
                    <form method="post" action="Admin.jsp">
                        <input type="hidden" name="option" value="Customer">
                        <input type="submit" class="button buttonPink" value="Customer" >
                    </form>
                </div>
                <div class="adOption">
                    <form method="post" action="Admin.jsp">
                        <input type="hidden" name="option" value="addCar">
                        <input type="submit" class="button buttonPink" value="Add Car" >
                    </form>
                </div>
                <div class="adOption">
                    <form method="post" action="Admin.jsp">
                        <input type="hidden" name="option" value="History">
                        <input type="submit" class="button buttonPink" value="History" >
                    </form>
                </div>
                <div class="adOption">
                    <form method="post" action="Admin.jsp">
                        <input type="hidden" name="option" value="Edit">
                        <input type="submit" class="button buttonPink" value="Edit" >
                    </form>
                </div>
            </div>
            <div class="showOption">
                <%
                    if (option.equals("History")) {
                %>
                <div style="font-size: 40px; font-family: font1; text-align: center; color: white; width: 797px; margin: auto;background-color: rgba(0,0,0,0.8);;">Histories</div>
                <div style="width:797px; margin: auto;     background-color: rgba(0,0,0,0.8);   ">


                    <%
                        out.print("<div class='historys removeSpace'>");
                        out.print("<div class='history historyName headerHistory'>" + "Full Name" + "</div>" + "<div class='history historyName headerHistory'>" + "Car" + "</div>" + "<div class='history historyTime headerHistory'>" + "Time rent" + "</div>" + "<div class='history historyTime headerHistory'>" + "Time return" + "</div>" + "<div class='history historyPayment headerHistory'>" + "Payment" + "</div>");
                        out.print("</div>");
                        //change
                        try {
                            Class.forName("com.mysql.jdbc.Driver").newInstance();
                            connection = DriverManager.getConnection(connectionURL, "root", "");
                            statement = connection.createStatement();
                        } catch (SQLException ex) {
                        }
                        rs = statement.executeQuery("SELECT * FROM history");
                        while (rs.next()) {
                            String CFName = rs.getString("CFName");
                            String CarName = rs.getString("CarName");
                            String TimeRent = rs.getString("TimeRent");
                            String TimeReturn = rs.getString("TimeReturn");
                            String Payment = rs.getString("Payment");
                            out.print("<div class='historys removeSpace'>");
                            out.print("<div class='history historyName'>" + CFName + "</div>" + "<div class='history historyName'>" + CarName + "</div>" + "<div class='history historyTime'>" + TimeRent + "</div>" + "<div class='history historyTime'>" + TimeReturn + "</div>" + "<div class='history historyPayment'>" + Payment + "</div>");
                            out.print("</div>");
                        }
                        connection.close();
                    %>
                </div>
                <%} else if (option.equals("Car")) {%>
                <div style="font-size: 40px; font-family: font1; text-align: center; color: white; width: 825px;; margin: auto;background-color: rgba(0,0,0,0.8);;">All Car in system</div>
                <div style="width: 825px;; margin: auto;     background-color: rgba(0,0,0,0.8);   ">
                    <%
                        String a = option;
                        out.print("<div class='historys removeSpace'>");
                        out.print("</div>");
                        out.print("<div class='historys removeSpace'>");
                        out.print("<div class='history divID headerHistory'>" + "Name" + "</div>" + "<div class='history historyName headerHistory'>" + "Name" + "</div>" + "<div class='history showCarAdmin headerHistory'>" + "Price($)" + "</div>" + "<div class='history showCarAdmin headerHistory'>" + "Discount(%)" + "</div>" + "<div class='history showCarAdmin headerHistory'>" + "Rate" + "</div>" + "<div class='history showCarAdmin headerHistory'>" + "Status" + "</div>");
                        out.print("</div>");
                        //change
                        try {
                            Class.forName("com.mysql.jdbc.Driver").newInstance();
                            connection = DriverManager.getConnection(connectionURL, "root", "");
                            statement = connection.createStatement();
                        } catch (SQLException ex) {
                        }
                        rs = statement.executeQuery("SELECT * FROM car c WHERE CarID not IN (SELECT r.CarID FROM rent r)");
                        while (rs.next()) {
                            String CarID = rs.getString("CarID");
                            String CarName = rs.getString("CarName");
                            String Discount = rs.getString("Discount");
                            String Price = rs.getString("Price");
                            String Like = rs.getString("Like");
                            String Dislike = rs.getString("Dislike");
                            String Status = rs.getString("Status");
                            int like = Integer.parseInt(Like);
                            int dislike = Integer.parseInt(Dislike);
                            float x;
                            int star = 0;
                            x = (float) like / (float) dislike;
                            if (x < 0.5) {
                                star = 0;
                            } else if (0.5 <= x && x < 1.5) {
                                star = 1;
                            } else if (1.5 <= x && x < 2.5) {
                                star = 2;
                            } else if (2.5 <= x && x < 3.5) {
                                star = 3;
                            } else if (3.5 <= x && x < 4.5) {
                                star = 4;
                            } else if (4.5 <= x) {
                                star = 5;
                            }

                            out.print("<div class='historys removeSpace'>");
                            out.print("<div class='history divID'>" + CarID + "</div>" + "<div class='history historyName'>" + CarName + "</div>" + "<div class='history showCarAdmin'>" + Price + "</div>" + "<div class='history showCarAdmin'>" + Discount + "</div>" + "<div class='history showCarAdmin'>" + star + "</div>" + "<div class='history showCarAdmin'>" + Status + "</div>"
                                    + "<div class='history tableCarList1'>"
                                    + "<form method='post' action='check'>"
                                    + "<input type='hidden' name='CarID' value='" + CarID + "'>"
                                        + "<input type='hidden' value='Admin_Car_Edit' name='action'>"
                                    + "<input type='Submit' class='button buttonBlack' value='Edit'>"
                                    + "</form></div>");
                            if (Status.equals("A")) {
                                out.print("<div class='history tableCarList2'>"
                                        + "<form method='post' action='check'>"
                                        + "<input type='hidden' name='CarID' value='" + CarID + "'>"
                                        + "<input type='hidden' value='Admin_Car_Update' name='action'>"
                                        + "<input type='Submit' class='button buttonBlack' value='Update'>"
                                        + "</form></div>");
                            } else if (Status.equals("U")) {
                                out.print("<div class='history tableCarList2'>"
                                        + "<form method='post' action='check'>"
                                        + "<input type='hidden' name='CarID' value='" + CarID + "'>"
                                        + "<input type='hidden' value='Admin_Car_Already' name='action'>"
                                        + "<input type='Submit' class='button buttonBlack' value='Already'>"
                                        + "</form></div>");
                            }
                            out.print("<div class='history tableCarList2'>"
                                    + "<form method='post' action='check'>"
                                    + "<input type='hidden' name='CarID' value='" + CarID + "'>"
                                    + "<input type='hidden' value='Admin_Car_Delete' name='action'>"
                                    + "<input type='Submit' class='button buttonBlack' value='Delete' onclick=\"alert('Delete " + CarName + " success!')\">"
                                    + "</form></div>");
                            out.print("</div>");
                        }
                        connection.close();
                    %>
                </div>
                <%}%><%else if (option.equals("carEdit")) {%> 
                <%
                    String ID = (String) session.getAttribute("CarID");

                %>
                <div class="formEdit">
                    <div class="editHeader">Edit Car Form</div>
                    <form method="post" action="check">
                        <div class="rowEdit removeSpace">
                            <div class="col1">
                                <div class="headerEdit">Name</div>
                                <div class="headerEdit">Price($)</div>
                                <div class="headerEdit">Discount(%)</div>
                                <div class="buttonEdit1"><input type="reset" class="button buttonBlack" value="Cancel" style="font-size: 20px;"></div>
                            </div>
                            <div class="col2">
                                <div class="valueEdit"><input type="text" class="editCarBlank" name="name"></div>
                                <div class="valueEdit"><input type="number" class="editCarBlank" name="price"></div>
                                <div class="valueEdit"><input type="number" class="editCarBlank" name="discount"></div>
                                <div class="buttonEdit2"><input type="submit" class="button buttonBlack" value="Change" style="font-size: 20px;"></div>
                            </div>
                        </div>    

                        <input type="hidden" value="Admin_Car_Edit_Form" name="action">
                        <input type="hidden" value="<%=ID%>" name="carID">


                    </form>
                </div>
                <%}%><%else if (option.equals("addCar")) {%>
                <div class="formEdit">
                    <div class="editHeader">Add New Car</div>
                    <form method="post" action="check" name="addCarForm" onsubmit="return checkInput()">
                        <div class="rowEdit removeSpace">
                            <div class="col1">
                                <div class="headerEdit">Car Name</div>
                                <div class="headerEdit">Price($)</div>
                                <div class="headerEdit">Discount(%)</div>
                                <div class="headerEdit">Type</div>
                                <div class="headerEdit">Seats</div>
                                <div class="headerEdit">Color</div>
                                <div class="headerEdit">Introduce</div>
                                <div class="buttonEdit1"><input type="reset" class="button buttonBlack" value="Cancel" style="font-size: 20px;"></div>
                            </div>
                            <div class="col2">
                                <div class="valueEdit"><input type="text" class="editCarBlank" name="name"></div>
                                <div class="valueEdit"><input type="number" class="editCarBlank" min="50" name="price"></div>
                                <div class="valueEdit"><input type="number" class="editCarBlank" min="0" name="discount"></div>
                                <div class="valueEdit"><select style="border: 1px solid" name="carType">
                                        <option value="Travel">Travel car</option>
                                        <option value="Sport">Sport car</option>
                                        <option value="Family">Family car</option>
                                        <option value="Pony">Pony car</option>
                                    </select></div>
                                <div class="valueEdit"><input type="number" class="editCarBlank" min="2" name="seat"></div>
                                <div class="valueEdit"><input type="text" class="editCarBlank" name="color"></div>
                                <div class="valueEdit"><input type="text" class="editCarBlank" name="introduce"></div>
                                <input type="hidden" value="Admin_Car_addCar" name="action">
                                <div class="buttonEdit2"><input type="submit" class="button buttonBlack" value="Add" style="font-size: 20px;"></div>
                            </div>
                        </div>    
                    </form>
                </div>
                <%}%><%else if (option.equals("Customer")) {%>
                <div style="font-size: 40px; font-family: font1; text-align: center; color: white; width: 657px; margin: auto;background-color: rgba(0,0,0,0.8);">Control Customers</div>
                <div style="width: 657px; margin: auto;     background-color: rgba(0,0,0,0.8);   ">

                    <%
                            out.print("<div class='historys removeSpace'>");

                            out.print("</div>");
                            out.print("<div class='historys removeSpace'>");
                            out.print("<div class='history historyName headerHistory'>" + "Full Name" + "</div>" + "<div class='history customerAdmin headerHistory'>" + "Phone Number" + "</div>" + "<div class='history customerAdmin headerHistory'>" + "Address" + "</div>" + "<div class='history customerAdmin headerHistory'>" + "Identity Number" + "</div>");
                            out.print("</div>");
                            //change
                            try {
                                Class.forName("com.mysql.jdbc.Driver").newInstance();
                                connection = DriverManager.getConnection(connectionURL, "root", "");
                                statement = connection.createStatement();
                                statement2 = connection.createStatement();
                            } catch (SQLException ex) {
                            }
                            rs = statement.executeQuery("SELECT * FROM customer");
                            while (rs.next()) {
                                String CID = rs.getString("CID");
                                String CFName = rs.getString("CFName");
                                String CPhone = rs.getString("CPhone");
                                String CAddress = rs.getString("CAddress");
                                String CINumber = rs.getString("CINumber");
                                out.print("<div class='historys removeSpace'>");
                                out.print("<div class='history historyName'>" + CFName + "</div>" + "<div class='history customerAdmin'>" + CPhone + "</div>" + "<div class='history customerAdmin'>" + CAddress + "</div>" + "<div class='history customerAdmin'>" + CINumber + "</div>");
                                rs2 = statement2.executeQuery("SELECT * FROM ban WHERE CID = '" + CID + "'");
                                if (rs2.next()) {
                                    out.print("<div class='history tableCarList2'>"
                                            + "<form method='post' action='check'>"
                                            + "<input type='hidden' name='CID' value='" + CID + "'>"
                                            + "<input type='hidden' value='Admin_Car_Unban' name='action'>"
                                            + "<input type='Submit' class='button buttonBlack' value='unBan'>"
                                            + "</form></div>");
                                } else {
                                    out.print("<div class='history tableCarList2'>"
                                            + "<form method='post' action='check'>"
                                            + "<input type='hidden' name='CID' value='" + CID + "'>"
                                            + "<input type='hidden' value='Admin_Car_Ban' name='action'>"
                                            + "<input type='Submit' class='button buttonBlack' value='Ban'>"
                                            + "</form></div>");
                                }

                                out.print("</div>");
                            }
                            out.print("</div>");
                            connection.close();

                        }%><%else if (option.equals("Edit")) {%>
                    <div class="formEdit">
                        <div class="editHeader">Edit Admin Info</div>
                        <form method="post" action="check" name="EditForm" onsubmit="return checkEdit()">
                            <div class="rowEdit removeSpace">
                                <div class="col1">
                                    <div class="headerEdit">Full Name</div>
                                    <div class="headerEdit">Pass</div>
                                    <div class="headerEdit">Re-Pass</div>
                                    <div class="headerEdit">Phone</div>
                                    <div class="headerEdit">Address</div>
                                    <div class="buttonEdit1"><input type="reset" class="button buttonBlack" value="Cancel" style="font-size: 20px;"></div>
                                </div>
                                <div class="col2">
                                    <div class="valueEdit"><input type="text" class="editCarBlank" name="AdFName"></div>
                                    <div class="valueEdit"><input type="password" class="editCarBlank" name="AdPass"></div>
                                    <div class="valueEdit"><input type="password" class="editCarBlank" name="Re-AdPass"></div>
                                    <div class="valueEdit"><input type="number" class="editCarBlank" name="AdPhone"></div>
                                    <div class="valueEdit"><input type="text" class="editCarBlank" name="AdAddress"></div>
                                    <div class="buttonEdit2"><input type="submit" class="button buttonBlack" value="Change" style="font-size: 20px;"></div>
                                </div>
                            </div>    

                            <input type="hidden" value="Admin_Car_EditAdmin_Form" name="action">
                        </form>
                    </div>
                    <%}%><%else if (option.equals("Rent")) {%>

                    <div style="font-size: 40px; font-family: font1; text-align: center; color: white; width: 788px; margin: auto;background-color: rgba(0,0,0,0.8);">Control Customers</div>
                    <div style="width: 788px; margin: auto;     background-color: rgba(0,0,0,0.8);   ">
                        <%
                            try {
                                Class.forName("com.mysql.jdbc.Driver").newInstance();
                                connection = DriverManager.getConnection(connectionURL, "root", "");
                                statement = connection.createStatement();
                                statement2 = connection.createStatement();
                            } catch (SQLException ex) {
                            }
                            out.print("<div class='historys removeSpace'>");
                            out.print("<div class='history divID headerHistory'>" + "ID" + "</div>" + "<div class='history historyName headerHistory'>" + "Full Name" + "</div>" + "<div class='history divID headerHistory'>" + "CarID" + "</div>" + "<div class='history historyName headerHistory'>" + "Car Name" + "</div>" + "<div class='history historyTime headerHistory'>" + "Date & Time" + "</div>" + "<div class='history divID headerHistory'>" + "Type" + "</div>");
                            out.print("</div>");
                            rs = statement.executeQuery("SELECT * FROM rent");
                            while (rs.next()) {
                                int CarID = Integer.parseInt(rs.getString("CarID"));
                                int CID = Integer.parseInt(rs.getString("CID"));
                                String TimeRent = rs.getString("TimeRent");
                                String RentType = rs.getString("RentType");
                                String CFName = "";
                                String CarName = "";
                                rs2 = statement2.executeQuery("SELECT CFName FROM customer WHERE CID=" + CID);
                                if (rs2.next()) {
                                    CFName = rs2.getString("CFName");
                                }
                                rs2 = statement2.executeQuery("SELECT CarName FROM car WHERE CarID=" + CarID);
                                if (rs2.next()) {
                                    CarName = rs2.getString("CarName");
                                }

                                out.print("<div class='historys removeSpace'>");
                                out.print("<div class='history divID'>" + CID + "</div>" + "<div class='history historyName'>" + CFName + "</div>" + "<div class='history divID'>" + CarID + "</div>" + "<div class='history historyName'>" + CarName + "</div>" + "<div class='history historyTime'>" + TimeRent + "</div>" + "<div class='history divID'>" + RentType + "</div>");
                                if (RentType.equals("W")) {
                                    out.print("<div class='history tableCarList1'>"
                                            + "<form method='post' action='check'>"
                                            + "<input type='hidden' name='CarID' value='" + CarID + "'>"
                                            + "<input type='hidden' value='getCar' name='action'>"
                                            + "<input type='Submit' class='button buttonBlack1' value='Get'>"
                                            + "</form></div>");
                                    out.print("<div class='history tableCarList1'>"
                                            + "<form method='post' action='check'>"
                                            + "<input type='hidden' name='CarID' value='" + CarID + "'>"
                                            + "<input type='hidden' value='CancelOrder' name='action'>"
                                            + "<input type='Submit' class='button buttonBlack1' value='Cancel'>"
                                            + "</form></div>");
                                } else if (RentType.equals("R")) {
                                    out.print("<div class='history tableCarList1'>"
                                            + "<form method='post' action='check'>"
                                            + "<input type='hidden' name='CarID' value='" + CarID + "'>"
                                            + "<input type='hidden' value='Return' name='action'>"
                                            + "<input type='Submit' class='button buttonBlack1' value='Return'>"
                                            + "</form></div>");
                                }
                                out.print("</div>");

                            }
                            out.print("</div>");
                            connection.close();
                        %>






                        <%}%>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
