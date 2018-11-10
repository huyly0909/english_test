/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.op;

import core.util.field.Field;
import java.sql.SQLException;

/**
 *
 * @author tinluu
 */
public class DeleteOp {
    public static void execute(String type, String ids) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        new db().where(type)
                .addInCondition("id", ids)
                .delete();
    }

    public static void execute(String type, Field col, String ids) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        new db().where(type)
                .addInCondition(col.Name(), ids)
                .delete();
    }
}
