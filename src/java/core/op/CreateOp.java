/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.op;

import core.util.Strings;
import core.util.field.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import models.users.User;

/**
 *
 * @author tinluu
 */
public class CreateOp {
    public static void create(String type, HttpServletRequest request) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Field[] allColumns = User.fullColumns;
//        if (type.equals(User.TYPE)) {
//            allColumns = User.allColumns;
//        }
        List<String> values = new ArrayList<>();
        for(Field col : allColumns) {
            String value = request.getParameter(col.Name());
            if (Strings.isEmpty(value) && col.isNumber()) {
                value = "0";
            }
            values.add(value);
        }
        new db().where(type)
                .addColumns(allColumns)
                .addValues(values)
                .update();
    }
}
