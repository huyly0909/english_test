/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.op;

import core.util.Booleans;
import core.util.ConditionBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import models.tests.Answer;
import models.tests.Question;

/**
 *
 * @author tinluu
 */
public class CreateNewQuickTestOp {
    public static void create(Question question) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        int questionId = new db().where(Question.TYPE)
                  .addColumns(Question.allColumns)
                  .addValues(question.getDescription(), String.valueOf(question.getType()))
                  .updateAndGetId();
        db answerDB = new db();
        for(Answer answer : question.getAnswers()) {
            answerDB.where(Answer.TYPE)
                    .addColumns(Answer.allColumns)
                    .addValues(String.valueOf(questionId), answer.getDescription(), String.valueOf(Booleans.convertToInt(answer.isCorrect())))
                    .update();
        }
        answerDB.close();
    }
}
