/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.op;

import core.util.ConditionBuilder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import models.users.CurrentUser;
import models.users.Role;
import models.users.User;

/**
 *
 * @author tinluu
 */
public class CheckUserLogin {

    final private static String SALT = "123456";
    final private static char[] HEXARRAY = "0123456789ABCDEF".toCharArray();

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEXARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEXARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    private String hashPassword(String in) {
        try {
            MessageDigest md = MessageDigest
                    .getInstance("SHA-256");
            md.update(SALT.getBytes());        // <-- Prepend SALT.
            md.update(in.getBytes());

            byte[] out = md.digest();
            return bytesToHex(out);            // <-- Return the Hex Hash.
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean checkUser(String userName, String password, HttpServletRequest request) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ResultSet resultSet = new db().find(User.TYPE)
                .addConditions(ConditionBuilder.get(User.USERNAME.Name(), userName),
                               ConditionBuilder.get(User.PASSWORD.Name(), password))
                .execute();
        if (resultSet.first()) { // Check has at least one user who has correct user_name and correct password
            setCurrentUser(resultSet, request);
            return true;
        }
        return false;
    }

    private static void setCurrentUser(ResultSet resultSet, HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession(false);
        CurrentUser currentUser = new CurrentUser(
                resultSet.getInt(User.ID.Name()),
                resultSet.getString(User.FULLNAME.Name()),
                Role.getRole(resultSet.getInt(User.ROLEID.Name())),
                resultSet.getInt(User.AGE.Name()),
                resultSet.getString(User.ADDRESS.Name()),
                resultSet.getInt(User.PHONE.Name()));
        session.setAttribute("currentUser", currentUser);
    }
}
