package models.users;

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
    public static String ID = "id";
    public static String USERNAME = "user_name";
    public static String PASSWORD = "password";
    public static String FULLNAME = "full_name";
    public static String AGE = "age";
    public static String ADDRESS = "address";
    public static String ROLEID = "role_id";
    public static String PHONE = "phone";
    private int id;
    private String userName;
    private String password;
    private Role role;
    private String fullName;
    private int age;
    private String address;
    private int phone;
    
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.removeAttribute("currentUser");
    };
}
