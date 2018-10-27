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
        <title>JSP Page</title>
    </head>
    <body>
        <div class="form-container header form-title">
            <h1 class='test-form-title'>Create New Advanced Quick Test</h1>
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
                        </td>
                    </tr>
                </table>
        </form>
        </div>
    </body>
</html>
