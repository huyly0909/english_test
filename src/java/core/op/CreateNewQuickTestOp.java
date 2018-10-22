/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.op;

import java.sql.SQLException;
import models.tests.Question;

/**
 *
 * @author tinluu
 */
public class CreateNewQuickTestOp {
    public static void create(String[] strValues) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        new db().where(Question.TYPE)
                .addColumns(Question.allColumns)
                .addStrValues(strValues)
                .addIntValues(1)
                .update();
    }
}
