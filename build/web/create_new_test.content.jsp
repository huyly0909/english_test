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
                var correctAnswers = ["A", "B", "C", "D"];
                var isCorrect = false;
                for (var i = 0; i < correctAnswers.length; i++) {
                    if ($("#correctAnswer").val() === correctAnswers[i]) {
                       isCorrect = true;
                       break;
                    }
                }
                if (isCorrect) {
                    return true;
                }
                alert("Correct answer is A, B, C or D.");
                return false;
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
                           <input type="text" name="description"  required><br>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <span>Answer A</span>
                        </td>
                        <td>
                           <input type="text" name="answer0"  required><br>
                        </td>
                        <td>
                            <input type="checkbox" name="isCorrect0">
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <span>Answer B</span>
                        </td>
                        <td>
                           <input type="text" name="answer1"  required><br>
                        </td>
                        <td>
                            <input type="checkbox" name="isCorrect1">
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <span>Answer C</span>
                        </td>
                        <td>
                           <input type="text" name="answer2"  required><br>
                        </td>
                        <td>
                            <input type="checkbox" name="isCorrect2">
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <span>Answer D</span>
                        </td>
                        <td>
                           <input type="text" name="answer3"  required><br>
                        </td>
                        <td>
                            <input type="checkbox" name="isCorrect3">
                        </td>
                    </tr>
                    
                    <tr>
                        <td colspan="2" class="buttons">
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
