

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
                clearInterval(clock);
                var type = '${testType}';
                var total = 0;
                var questions_size = $(".question-container").length;
                var points = 100 / questions_size;
                // Compute total core
                for (var i = 1; i <= questions_size; i++) {
                    var questionID = "#question" + i;
                    var questionInput = questionID + " > input";
                    if (type === "writing") {
                        questionInput = questionID + " > textarea";
                        var questionInputVal = $(questionInput).val();
//                        if (questionInputVal === '') {
//                            alert("Please finish question " + i);
//                            return false;
//                        }
                        var correct_answer = $(questionID + " > div[class='correct-answer']").text().split(': ')[1];
                        if (correct_answer === questionInputVal) {
                            total += points;
                        }
                    } else {
                        // Get selected answer
                        var selectedAnswer = $(questionInput + ":checked");
                        var selectedAnswerVal = selectedAnswer.val();
//                        if (typeof selectedAnswerVal === 'undefined') {
//                            alert("Please finish question " + i);
//                            return false;
//                        }
                        //  Get correct answer
                        var correctAnswerVal = $(questionID + " > div[class='correct-answer'] > span").text();
                        // compare selected answer with correct answer
                        var isCorrect = true;
                        // If have single answer
                        // else have multiple answers
                        if (selectedAnswer.length in [0, 1]) {
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
                            total += points;
                        }
                        // disable question after submit
                        $(questionInput).each(function(index) {
                            $(questionInput + ":eq(" + index + ")").attr("disabled", true);
                        });
                    }
                }
                // display all correct-answer
                $(".correct-answer").each(function(index) {
                    $(".correct-answer:eq(" + index + ")").attr("style", "display: block");
                });
                total = " Total score: " + total + " " + (total !== 0 ? "points" : "point");
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
            
            var clock = null;

            function startTimer(duration, display) {
                var timer = duration, minutes, seconds;
                clock = setInterval(function () {
                    minutes = parseInt(timer / 60, 10);
                    seconds = parseInt(timer % 60, 10);

                    minutes = minutes < 10 ? "0" + minutes : minutes;
                    seconds = seconds < 10 ? "0" + seconds : seconds;

                    display.textContent = minutes + " | " + seconds;

                    if (--timer < 0) {
                        clearInterval(clock);
                        $("input[type='submit']").click();
                    }
                }, 1000);
            }

            window.onload = function () {
                var fiveMinutes = 60 * 15;
                display = document.querySelector('#time');
                startTimer(fiveMinutes, display);
            };
        </script>
    </head>
    <body>
        <div class="countdown-clock" style="border: solid 3px; position: fixed; font-size: 30px; margin-left: 5px;" id="time">15 | 00</div>
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
