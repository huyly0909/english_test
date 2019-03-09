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
                    alert("New writing test was created!");
                }
            });

            function validateForm() {
                for (var i = 0; i < 10; i++) {
                    var answer = $("input[name='answer_q" + i + "']").val();
                    var description_length = $("input[name='description_q" + i + "']").val().length;
                    if (answer.length < description_length) {
                        alert("The answer of question " + (i + 1) + " can not shorter than description.");
                        return false;
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
