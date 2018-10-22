/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.util.HtmlBuilder;

import core.util.Strings;
import models.tests.Question;

/**
 *
 * @author tinluu
 */
public class QuestionHTMLBuilder {

    /*
    value: A, B, C, D
    desription:
    */
    private static String answer(String value, String description) {
        return String.format(
                "<input class='question-radio-input option-input radio' type='radio' name='question{index}' value='%s'> %s: %s<br/>", value, value, description)
                .replaceAll("(.)(- [A-Z])", "$1<br/>$2");
    }

    public static String build(Question question, int i) {
        String template =
            "<div id='question{index}' class='question-container'>" + 
                "<span type='text' class='input-text'><span class='bold'>Q{index}:</span> {description}</span><br/>" +
                    answer("A", question.getA()) +
                    answer("B", question.getB()) +
                    answer("C", question.getC()) +
                    answer("D", question.getD()) +
                "<div class='correct-answer' style='display: none'>Correct Answer: <span class='bold'>{correct_answer}</span></div>" +
            "</div>" +
            "<br/>";
        return Strings.format(template, "index", String.valueOf(i),
                                        "description", question.getDescription(),
                                        "correct_answer", question.getCorrectAnswer());
    }
}
