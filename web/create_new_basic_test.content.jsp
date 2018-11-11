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

            function checkCorrectAnswer() {
                if ($("input[type='checkbox']:checked").length > 1) {
                    alert("The basis question just has one correct answer.");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        ${topBtn}
        <div class="form-container header form-title">
            ${formHeader}
        </div>
        <div class="form-container body">
            <form method="post" action="quick_test" name="createQuestionForm" onsubmit="return validateForm()">
                <table class="form-table">
                    <tr>
                        <td>
                            <span>Description</span>
                        </td>
                        <td>
                           <input type="text" value="New description" name="description"  required><br>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <span>Answer A</span>
                        </td>
                        <td>
                           <input type="text" name="description0" value="a" required><br>
                        </td>
                        <td>
                            <input type="checkbox" onclick="return checkCorrectAnswer()" checked name="is_correct0">
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <span>Answer B</span>
                        </td>
                        <td>
                           <input type="text" name="description1" value="b" required><br>
                        </td>
                        <td>
                            <input type="checkbox" onclick="return checkCorrectAnswer()" name="is_correct1">
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <span>Answer C</span>
                        </td>
                        <td>
                           <input type="text" name="description2" value="c" required><br>
                        </td>
                        <td>
                            <input type="checkbox" onclick="return checkCorrectAnswer()" name="is_correct2">
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <span>Answer D</span>
                        </td>
                        <td>
                           <input type="text" name="description3" value="d" required><br>
                        </td>
                        <td>
                            <input type="checkbox" onclick="return checkCorrectAnswer()" name="is_correct3">
                        </td>
                    </tr>
                    
                    <tr>
                        <td colspan="2" class="buttons">
                            <input type="hidden" value="1" name="type">
                            <input type="hidden" value="4" name="answerSize">
                            <input type="hidden" value="create" name="action">
                            <input type="submit" value="Create">
                        </td>
                    </tr>
                </table>
        </form>
        </div>
    </body>
</html>
