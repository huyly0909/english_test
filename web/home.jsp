
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
            function checkPermission(testName) {
                var isMember = '${currentUser.isNotGuest()}';
                if (isMember) {
                    $("input[name='action']").attr("value", testName);
                    $("form[name='test']").submit();
                    return true;
                }
                alert('Please login to do ' + testName + ' test.');
                return false;
            }
            
            function displayQuickTestOptions() {
                $("#main-container").attr("style", "display: none;");
                $("#quick-test-options-container").attr("style", "");
            }
            
            function cancelQuickTestOptions() {
                $("#main-container").attr("style", "");
                $("#quick-test-options-container").attr("style", "display: none");
            }
            
            function selectOption(option) {
                if (option === "advanced" && !checkPermission("advanced quick test")) {
                    return false;
                }
                $("input[name='action']").attr("value", option);
                $("form[name='quickTestOptions']").submit();
                return true;
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
        
        <form method="post" name='quickTestOptions' action="quick_test">
            <input type="hidden" value="" name="action">
        </form>
        <form method="post" name='test' action="test">
            <input type="hidden" value="" name="action">
        </form>
        
        <div id="main-container" class="container" style="">
            <div type="button" onclick="displayQuickTestOptions()" class="div-btn quick-test-btn test-section-btn"><span class="test-section-text">Q U I C K &nbsp; T E S T</span></div>
            <div type="button" onclick="return checkPermission('reading')" class="div-btn reading-btn test-section-btn"><span class="test-section-text">R E A D I N G</span></div>
            <div type="button" onclick="return checkPermission('writing')" class="div-btn test-section-btn writing-btn"><span class="test-section-text">W R I T I N G</span></div>
            <div type="button" onclick="return checkPermission('listening')" class="div-btn test-section-btn listening-btn"><span class="test-section-text">L I S T E N I N G</span></div>
            <div type="button" onclick="return checkPermission('speaking')" class="div-btn test-section-btn speading-btn"><span class="test-section-text">S P E A K I N G</span></div>
        </div>
        <div id="quick-test-options-container" class='container quick-test-options-container' style="display: none;">
            <div type="button" onclick='return selectOption("advanced")' class="div-btn test-section-btn quick-test-advented-btn"><span class="test-section-text">A D V E N C E D</span></div>
            <div type="button" onclick='selectOption("basic")' class="div-btn test-section-btn quick-test-basic-btn"><span class="test-section-text">B A S I C</span></div>
        </div>
        <br/><br/><br/>
    </body>
</html>
