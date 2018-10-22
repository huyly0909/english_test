

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quick Test</title>
        <link rel="stylesheet" href="css/style.css">
        <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
        <script TYPE="text/javascript">
            function checkResult() {
                var total = 0;
                // display all correct-answer
                $(".correct-answer").each(function(index) {
                    $(".correct-answer:eq(" + index + ")").attr("style", "display: block");
                });
                // Compute total core
                for (var i = 1; i <= 10; i++) {
                    var questionID = "#question" + i;
                    var questionInput = questionID + " > input";
                    // Get selected answer
                    var selectedAnswer = $(questionInput + ":checked");
                    var selectedAnswerVal = selectedAnswer.val();
                    //  Get correct answer
                    var correctAnswerVal = $(questionID + " > div[class='correct-answer'] > span").text();
                    // compare selected answer with correct answer
                    if (selectedAnswerVal === correctAnswerVal) {
                        total += 10;
                    }
                    // disable question after submit
                    $(questionInput).each(function(index) {
                        $(questionInput + ":eq(" + index + ")").attr("disabled", true);
                    });
                }
                total = " Total core: " + total + " " + (total !== 0 ? "points" : "point");
                // display total core
                $("#totalCore").text(total);
                // Scroll to result
                $('html,body').animate({
                    scrollTop: $("#totalCore").offset().top
                }, 'fast');
                // Invisible submit btn
                $("input:submit").attr("style", "display: none");
                $("#continueBtn").attr("style", "");
                $("#doOtherTestBtn").attr("style", "");
            }
        </script>
    </head>
    <body>
        ${topBtn}
        <div class="test-form-container header">
            ${testHeader}
        </div>
        <div class="test-form-container body">
            ${testForm}
        </div>
        <!--<div class="test-form-container footer">-->
            <!--${testResult}-->
        <!--</div>-->
    </body>
</html>
