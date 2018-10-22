/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.op;

import core.util.ConditionBuilder;
import core.util.Ints;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import models.tests.Question;

/**
 *
 * @author tinluu
 */
public class GetQuickTestOp {

    private static ArrayList<Question> getQuestionList() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ResultSet resultSet = new db().where(Question.TYPE)
                .addConditions(ConditionBuilder.get(Question.IS_QUICK_TEST, true))
                .execute();
        ArrayList<Question> questionList = new ArrayList<>();
        // Add new question into questions list
        while (resultSet.next()) {
            questionList.add(
                    new Question(resultSet.getString(Question.DESCRIPTION),
                                 resultSet.getString(Question.A),
                                 resultSet.getString(Question.B),
                                 resultSet.getString(Question.C),
                                 resultSet.getString(Question.D),
                                 resultSet.getString(Question.CORRECT_ANSWER))
            );
        }
        return questionList;
    }
    public static ArrayList<Question> getRandomQuestions(int amount) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ArrayList<Question> allQuestions = getQuestionList();
        ArrayList<Question> questions = new ArrayList<>();
        int max = allQuestions.size() - 1;
        // Get random one question
        // if questions does not contain this question => add
        // else ignore.
        while (questions.size() <= amount - 1) {
            final Question question = allQuestions.get(Ints.getRandomInt(max));
            if (!questions.contains(question)) {
                questions.add(question);
            }
        }
        return questions;
    }
}
