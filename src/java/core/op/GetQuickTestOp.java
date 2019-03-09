/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.op;

import core.util.Booleans;
import core.util.ConditionBuilder;
import core.util.Ints;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import models.tests.Answer;
import models.tests.Question;
import models.tests.QuestionType;

/**
 *
 * @author tinluu
 */
public class GetQuickTestOp {

    private static ArrayList<Question> getQuestionList(boolean isAdvanced) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        // Get list of question
        db db = new db();
        ResultSet questionSet = db.where(Question.TYPE)
                .addConditions(ConditionBuilder.get(Question.QTYPE.Name(), isAdvanced ? QuestionType.ADVANCED : QuestionType.BASIC))
                .execute();
        ArrayList<Question> questionList = new ArrayList<>();
        // Add new question into questions list
        while (questionSet.next()) {
            Question currentQuestion = new Question(questionSet.getString(Question.DESCRIPTION.Name()));
            db db2 = new db();
            // Get add answers from question_id
            ResultSet answerSet = db2.where(Answer.TYPE)
                    .addConditions(ConditionBuilder.get(Answer.QUESTION_ID.Name(), questionSet.getInt(Question.ID.Name())))
                    .execute();
            while (answerSet.next()) {
                Answer _answer = new Answer(answerSet.getString(Answer.DESCRIPTION.Name()), Booleans.isTrue(answerSet.getInt(Answer.IS_CORRECT.Name())));
                currentQuestion.addAnswer(_answer);
            }
            questionList.add(currentQuestion);
            db2.close();
        }
        db.close();
        return questionList;
    }

    public static ArrayList<Question> getRandomQuestions(int amount, boolean isAdvanced) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ArrayList<Question> allQuestions = getQuestionList(isAdvanced);
        ArrayList<Question> questions = new ArrayList<>();
        int max = allQuestions.size() - 1;
        // Get random one question
        // if questions does not contain this question => add
        // else ignore.
        while (questions.size() < amount) {
            final Question question = allQuestions.get(Ints.getRandomInt(max));
            if (!questions.contains(question)) {
                questions.add(question);
            }
        }
        return questions;
    }
}
