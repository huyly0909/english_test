

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
                if ($("#test-description").html().trim() !== "") {
                    $("#test-description").removeAttr("style");
                }
            });

            function checkResult() {
                var total = 0;
                var questions_size = $(".question-container").length;
                var core_per_question = 100 / questions_size;
                // Compute total core
                for (var i = 1; i <= questions_size; i++) {
                    var questionID = "#question" + i;
                    var questionInput = questionID + " > input";
                    // Get selected answer
                    var selectedAnswer = $(questionInput + ":checked");
                    var selectedAnswerVal = selectedAnswer.val();
                    if (typeof selectedAnswerVal === 'undefined') {
                        alert("Please finish question " + i);
                        return false;
                    }
                    //  Get correct answer
                    var correctAnswerVal = $(questionID + " > div[class='correct-answer'] > span").text();
                    // compare selected answer with correct answer
                    var isCorrect = true;
                    // If have single answer
                    // else have multiple answers
                    if (selectedAnswer.length === 1) {
                        isCorrect = correctAnswerVal === selectedAnswerVal;
                    } else {
                        for (var j = 0; j < selectedAnswer.length; j++) {
                            if(!correctAnswerVal.includes(selectedAnswer.get(j).value)) {
                                isCorrect = false;
                                break;
                            }
                        }
                    }
                    if (isCorrect) {
                        total += core_per_question;
                    }
                    // disable question after submit
                    $(questionInput).each(function(index) {
                        $(questionInput + ":eq(" + index + ")").attr("disabled", true);
                    });
                }
                // display all correct-answer
                $(".correct-answer").each(function(index) {
                    $(".correct-answer:eq(" + index + ")").attr("style", "display: block");
                });
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
        <div class="test-form-container">
            ${testHeader}
        </div>
        
        <div id="test-description" class="test-form-container" style="display: none;">
            ${testDescription}
        </div>
        
        <div class="test-form-container">    
            ${testForm}
        </div>
        <!--<div class="test-form-container footer">-->
            <!--${testResult}-->
        <!--</div>-->
    </body>
</html>
