<%-- 
    Document   : create_new_advanced_test
    Created on : Oct 27, 2018, 4:13:15 PM
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
            var VALUE = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"];
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
                $("input[name='answerSize']").val($("tr").length - 2);
            }
            
            function addNewAnswer() {
                var answerRowLength = $("tr").length - 2;
                // count from 0
                var nextIndex = answerRowLength;
                var answerHtml = '<tr><td><span>Answer ' + VALUE[nextIndex] + 
                        '</span></td><td><input type="text" name="description' + nextIndex +
                        '" value="a" required><br></td><td><input type="checkbox" name="is_correct' + nextIndex +
                        '"></td></tr>';
                // last answer row = hear is first row + answer row length
                $(answerHtml).insertAfter($("tr:eq(" + answerRowLength + ")"));
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
                            <input type="checkbox" checked name="is_correct0">
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
                            <input type="checkbox" name="is_correct1">
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
                            <input type="checkbox" name="is_correct2">
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
                            <input type="checkbox" name="is_correct3">
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="buttons">
                            <input onclick="addNewAnswer()" type="button" value="➕ Answer">
                        </td>
                        <td class="buttons">
                            <input type="hidden" value="0" name="type">
                            <input type="hidden" value="4" name="answerSize">
                            <input type="hidden" value="create" name="action">
                            <input type="hidden" value="1" name="type">
                            <input type="submit" value="Create">
                        </td>
                    </tr>
                </table>
        </form>
        </div>
    </body>
</html>