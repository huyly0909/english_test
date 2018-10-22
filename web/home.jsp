
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test Online</title>
        <link rel="stylesheet" href="css/style.css">
<!--        <script src="jquery-3.2.1.min.js"-->
        <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
        <script TYPE="text/javascript">
            $(document).ready(function(){
                
            });
            function checkPermission(testName) {
                var isMember = '${currentUser.isNotGuest()}';
                if (isMember) {
                    return true;
                } else {
                    alert("Please login to do " + testName + " test.");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        
        <h1 style="text-align: center;" class="test-form-title">
            Welcome 
            ${currentUser.fullName() == null || currentUser.fullName() == "" ? "Guest" : currentUser.fullName()}
            to Test Online</h1>
        <!--login - register - logout-->
        ${topBtn}
        
        <div class="container">
            <a href='/test/quick_test'><div class="div-btn quick-test-btn test-section-btn"><span class="test-section-text">Q U I C K &nbsp; T E S T</span></div></a>
            <a onclick="return checkPermission('reading')" href='/test/home'><div class="div-btn reading-btn test-section-btn"><span class="test-section-text">R E A D I N G</span></div></a>
            <a onclick="return checkPermission('writing')" href='/test/home'><div class="div-btn test-section-btn writing-btn"><span class="test-section-text">W R I T I N G</span></div></a>
            <a onclick="return checkPermission('listening')" href='/test/home'><div class="div-btn test-section-btn listening-btn"><span class="test-section-text">L I S T E N I N G</span></div></a>
            <a onclick="return checkPermission('speaking')" href='/test/home'><div class="div-btn test-section-btn speading-btn"><span class="test-section-text">S P E A K I N G</span></div></a>
        </div>
        
        <br/><br/><br/>
    </body>
</html>
