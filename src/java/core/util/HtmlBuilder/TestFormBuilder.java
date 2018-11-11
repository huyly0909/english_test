/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.util.HtmlBuilder;

import core.util.Strings;
import models.tests.Reading;

/**
 *
 * @author tinluu
 */
public class TestFormBuilder {
    final static String TestForm = 
            "<form method='post' action='test' name='createQuestionForm' onsubmit='return validateForm()'>" +
            "    <table class='form-table'>" +
            "       {body}" +
            "    </table>" +
            "</form>";
        
    final static String TitleAndDescription = 
            "<tr>" +
            "    <td>" +
            "        <span>Title</span>" +
            "    </td>" +
            "    <td>" +
            "       <input type='text' value='Title' name='title'  required><br>" +
            "    </td>" +
            "</tr>";
    final static String ReadingTestDescription = 
            "<tr>" +
            "    <td>" +
            "        <span>Essay</span>" +
            "    </td>" +
            "    <td>" +
            "       <input type='text' value='Essay' name='description'  required><br>" +
            "    </td>" +
            "</tr>";

    final static String ListeningTestDescription = 
            "<tr>" +
            "    <td>" +
            "        <span>Url</span>" +
            "    </td>" +
            "    <td>" +
            "       <input type='text' value='Url' name='description'  required><br>" +
            "    </td>" +
            "</tr>";

    final static String QuestionHtml = 
            "<tr>" +
            "    <td colspan='2'  style='text-align: center'>" +
            "        <span>Question {questionIndex}</span>" +
            "    </td>" +
            "</tr>" +
            "" +
            "<tr>" +
            "    <td>" +
            "        <span>Description</span>" +
            "    </td>" +
            "    <td>" +
            "       <input type='text' value='New description' name='description_q{index}'  required><br>" +
            "    </td>" +
            "</tr>" +
            "<tr>" +
            "    <td>" +
            "        <span>Answer A</span>" +
            "    </td>" +
            "    <td>" +
            "       <input type='text' name='description0_q{index}' value='a' required><br>" +
            "    </td>" +
            "    <td>" +
            "        <input type='checkbox' onclick='return checkCorrectAnswer({index})' checked name='is_correct0_q{index}'>" +
            "    </td>" +
            "</tr>" +
            "" +
            "<tr>" +
            "    <td>" +
            "        <span>Answer B</span>" +
            "    </td>" +
            "    <td>" +
            "       <input type='text' name='description1_q{index}' value='b' required><br>" +
            "    </td>" +
            "    <td>" +
            "        <input type='checkbox' onclick='return checkCorrectAnswer({index})' name='is_correct1_q{index}'>" +
            "    </td>" +
            "</tr>" +
            "<tr>" +
            "    <td>" +
            "        <span>Answer C</span>" +
            "    </td>" +
            "    <td>" +
            "       <input type='text' name='description2_q{index}' value='c' required><br>" +
            "    </td>" +
            "    <td>" +
            "        <input type='checkbox' onclick='return checkCorrectAnswer({index})' name='is_correct2_q{index}'>" +
            "    </td>" +
            "</tr>" +
            "<tr>" +
            "    <td>" +
            "        <span>Answer D</span>" +
            "    </td>" +
            "    <td>" +
            "       <input type='text' name='description3_q{index}' value='d' required><br>" +
            "    </td>" +
            "    <td>" +
            "        <input type='checkbox' onclick='return checkCorrectAnswer({index})' name='is_correct3_q{index}'>" +
            "    </td>" +
            "</tr>";
    final static String SubmitBtn = 
            "<tr>" +
            "    <td colspan='2' class='buttons'>" +
            "        <input type='hidden' value='create{testType}' name='action'>" +
            "        <input type='hidden' value='{testType}' name='type'>" +
            "        <input type='submit' value='Create'>" +
            "    </td>" +
            "</tr>";

    public static String build(String type) {
        String fullForm = TitleAndDescription + (type.equals(Reading.TYPE) ? ReadingTestDescription : ListeningTestDescription);
        for (int i = 0; i <= 9; i++) {
            fullForm += Strings.format(QuestionHtml, "questionIndex", String.valueOf(i + 1),
                                                     "index", String.valueOf(i));
        }
        fullForm += Strings.format(SubmitBtn, "testType", type);
        return Strings.format(TestForm, "body", fullForm);
    }
}