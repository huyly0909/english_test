<%-- 
    Document   : create_test.content
    Created on : Oct 22, 2018, 12:29:52 AM
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
                var displayStyle = "display: ";
                displayStyle += isSuccessful ? "block" : "none";
                $("#isSuccessful").attr('style', displayStyle);
            });

            function validateForm() {
                if ($("input[type='checkbox']:checked").length === 0) {
                    alert("Select correct answer");
                    return false;
                }
            }

            function checkCorrectAnswer(index) {
                var count = 0;
                for (var i = 0; i <= 3; i++) {
                    var input = "input[name='is_correct" + i + "_q" + index + "']";
                    if($(input).is(":checked")) {
                        count++;
                        if(count === 2) {
                            alert("The question just has one correct answer.");
                            return false;
                        }
                    }
                }
                return true;
            }
        </script>
    </head>
    <body>
        ${topBtn}
        <div class="form-container header form-title">
            ${formHeader}
        </div>
        <div class="form-container body">
            ${formBody}
        </div>
    </body>
</html>
