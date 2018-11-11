/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.op;

import core.util.Booleans;
import java.sql.SQLException;
import java.util.List;
import models.tests.Answer;
import models.tests.Listening;
import models.tests.Question;
import models.tests.Reading;

/**
 *
 * @author tinluu
 */
public class CreateNewTestOp {
    public static void create(Reading test) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        db db = new db();
        int testId = new db().where(Question.TYPE)
                  .addColumns(Reading.allColumns)
                  .addValues(test.getTitle(),test.getDescription())
                  .updateAndGetId();
        createQuestions(db, test.getQuestions(), testId);
    }
    
        public static void create(Listening test) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        db db = new db();
        int testId = new db().where(Question.TYPE)
                  .addColumns(Listening.allColumns)
                  .addValues(test.getTitle(), test.getUrl())
                  .updateAndGetId();
        createQuestions(db, test.getQuestions(), testId);
    }
        
    private static void createQuestions(db db, List<Question> questions, int testId) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        for (Question question : questions) {
            int questionId = db.where(Question.TYPE)
                      .addColumns(Question.allCreateColumns)
                      .addValues(question.getDescription(), String.valueOf(question.getType()), String.valueOf(testId))
                      .updateAndGetId();
            db.reset();
            for(Answer answer : question.getAnswers()) {
                db.where(Answer.TYPE)
                        .addColumns(Answer.allCreateColumns)
                        .addValues(String.valueOf(questionId), answer.getDescription(), String.valueOf(Booleans.convertToInt(answer.isCorrect())))
                        .update();
            }
            db.reset();
        }
        db.close();
    }
}
