/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.op;

import core.util.Booleans;
import core.util.ConditionBuilder;
import core.util.field.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.tests.Answer;
import models.tests.Question;
import models.tests.QuestionType;
import models.tests.Listening;

/**
 *
 * @author tinluu
 */
public class GetListeningTestOp {
    public static Listening execute(int id) throws ClassNotFoundException, InstantiationException, SQLException, SQLException, SQLException, IllegalAccessException {
        db db = new db();
        ResultSet resultSet = db.where(Listening.TYPE)
                .addConditions(ConditionBuilder.get(Listening.ID.Name(), id))
                .execute();
        // Get Listening test.
        Listening test = new Listening();
        while (resultSet.next()) {
            test.setUrl(resultSet.getString(Listening.URL.Name()));
            test.setTitle(resultSet.getString(Listening.TITLE.Name()));
            break;
        }
        // Get question from Listening test.
        resultSet = db.where(Question.TYPE)
                .addConditions(ConditionBuilder.get(Question.PARENT_ID.Name(), id), ConditionBuilder.get(Question.QTYPE.Name(), QuestionType.LISTENING))
                .execute();
        // Add question into Listening test: ID is used to get Answer
        while(resultSet.next()) {
            test.addQuestion(new Question(
                    resultSet.getInt(Question.ID.Name()),
                    resultSet.getString(Question.DESCRIPTION.Name())
            ));
        }
        for (Question question : test.getQuestions()) {
            resultSet = db.where(Answer.TYPE)
                    .addConditions(ConditionBuilder.get(Answer.QUESTION_ID.Name(), question.getID()))
                    .execute();
            while(resultSet.next()) {
                question.addAnswer(new Answer(
                        resultSet.getString(Answer.DESCRIPTION.Name()),
                        Booleans.isTrue(resultSet.getInt(Answer.IS_CORRECT.Name()))
                ));
            }
        }
        db.close();
        return test;
    }
}
