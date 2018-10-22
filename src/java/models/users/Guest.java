/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.users;

import java.sql.SQLException;
import core.op.CheckUserLogin;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author tinluu
 */
public class Guest {
    
    final public static String GUEST = "Guest";
    // quicktest
    public static void doDemoTest() {
        
    }
    
    public static void register() {
        
    }
    
    public static boolean login(String userName, String password, HttpServletRequest request) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException  {
        return CheckUserLogin.checkUser(userName, password, request);
    }
}
