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
import java.util.ArrayList;
import models.tests.Answer;
import models.tests.Question;
import models.tests.QuestionType;

/**
 *
 * @author tinluu
 */
public class GetListViewOp {
    
    private static ResultSet records(String type) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        return new db().where(type).execute();
    }
}
