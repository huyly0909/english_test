/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.util.HtmlBuilder;

import core.util.Strings;
import java.util.List;
import models.tests.Answer;
import models.tests.Question;

/**
 *
 * @author tinluu
 */
public class QuestionHTMLBuilder {
    final private static String[] VALUE = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"};
    final private static String RADIO_TYPE = "radio";
    final private static String CHECKBOX_TYPE = "checkbox";
    /*
    value: A, B, C, D, E, F, G...
    desription:
    */
    private static String buildAnswer(String value, String description, String inputType) {
        return String.format(
                "<input class='question-radio-input option-input %s' type='%s' name='question{index}' value='%s'> %s: %s<br/>", inputType, inputType, value, value, description)
                .replaceAll("(.)(- [A-Z])", "$1<br/>$2");
    }

    public static String build(Question question, int i) {
        return build(question, i, false);
    }

    public static String build(Question question, int i, boolean isMutipleChoice) {
        // Build answers
        String answersHTML = "";
        String correctAnswer = "";
        String inputType = isMutipleChoice ? CHECKBOX_TYPE : RADIO_TYPE;
        List<Answer> answers = question.getAnswers();
        for(Answer answer : answers) {
            String value = VALUE[answers.indexOf(answer)];
            answersHTML += buildAnswer(value, answer.getDescription(), inputType);
            if (answer.isCorrect()) {
                correctAnswer += String.format("%s, ", value);
            }
        }
        String template =
            "<div id='question{index}' class='question-container'>" + 
                "<span type='text' class='input-text'><span class='bold'>Q{index}:</span> {description}</span><br/>" +
                "{answers}" +
                "<div class='correct-answer' style='display: none'>Correct Answer: <span class='bold'>{correct_answer}</span></div>" +
            "</div>" +
            "<br/>";
        try {
            correctAnswer = correctAnswer.substring(0, correctAnswer.length() - 2);
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Question " + i + " has not correc answer.");
        }
        return Strings.format(template, "index", String.valueOf(i),
                                        "answers", answersHTML,
                                        "description", question.getDescription(),
                                        "correct_answer", correctAnswer); // remove ", " at the end
    }
}
