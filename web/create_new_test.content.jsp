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
                           <input type="text" name="a"  required><br>
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <span>Answer B</span>
                        </td>
                        <td>
                           <input type="text" name="b"  required><br>
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <span>Answer C</span>
                        </td>
                        <td>
                           <input type="text" name="c"  required><br>
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <span>Answer D</span>
                        </td>
                        <td>
                           <input type="text" name="d"  required><br>
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <span>Correct answer</span>
                        </td>
                        <td>
                           <input id="correctAnswer" type="text" placeholder="A, B, C or D" name="correct_answer" required ><br>
                        </td>
                    </tr>
                    
                    <tr>
                        <td colspan="2" class="buttons">
                            <input type="hidden" value="create" name="action">
                            <input type="submit" value="Create">
                            <!--<input type='text' id="isSuccessful" class="isSuccessful" readonly value='Create successful!' style="display: none;">-->
                        </td>
                        <!--<td>-->
                           <!--<input type='text' id="isSuccessful" class="isSuccessful" readonly value='Create successful!' style="display: none;"><br>-->
                        <!--</td>-->
                    </tr>
                </table>
            <!--<div class="create-form">-->
                <!--Description: <input type="text" placeholder="Description" name="description" required ><br>-->
<!--                A: <input type="text" name="a"  required><br>
                B: <input type="text" name="b"  required><br>
                C: <input type="text" name="c"  required><br>
                D: <input type="text" name="d"  required><br>
                Correct Answer: <input id="correctAnswer" type="text" placeholder="A, B, C or D" name="correct_answer" required ><br>-->
<!--                <input type='text' id="isSuccessful" class="isSuccessful" readonly value='Create successful!' style="display: none;"><br>
                <input type="hidden" value="create" name="action">
                <input type="submit" value="Create">-->
            <!--</div>-->
        </form>
        </div>
    </body>
</html>
