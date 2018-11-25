/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.op;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tinluu
 */
public class GetListViewOp {
    
    public static ResultSet records(String type) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        return new db().where(type).execute();
    }
    
    public static ResultSet records(String type, String... condition) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        return new db()
                .where(type)
                .addConditions(condition)
                .execute();
    }
}
