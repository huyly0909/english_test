package models.users;

import core.util.field.Field;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tinluu
 */
public class User {

    public static String TYPE = "user";
    public static Field ID = Field.Int("id");
    public static Field USERNAME = Field.String("user_name");
    public static Field PASSWORD = Field.String("password");
    public static Field FULLNAME = Field.String("full_name");
    public static Field AGE = Field.Int("age");
    public static Field ADDRESS = Field.String("address");
    public static Field ROLEID = Field.Int("role_id");
    public static Field PHONE = Field.Int("phone");
    public static Field SALARY = Field.Double("salary").Salary();
    private int id;
    private String userName;
    private String password;
    private Role role;
    private String fullName;
    private int age;
    private String address;
    private int phone;
    final public static Field[] allColumns = { ID, USERNAME, FULLNAME, AGE, ADDRESS, ROLEID, PHONE, SALARY };
    
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.removeAttribute("currentUser");
    };
}
