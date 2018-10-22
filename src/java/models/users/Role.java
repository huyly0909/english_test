/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.users;

/**
 *
 * @author tinluu
 */
public class Role {
    
    final static String ADMIN = "admin";
    final static String TEACHER = "teacher";
    final static String CANDIDATE = "candidate";
    private static Role admin = null;
    private static Role teacher = null;
    private static Role candidate = null;
//    private int ID;
    private String name;
    private String decription;

    public Role(String name, String decription) {
        this.name = name;
        this.decription = decription;
    }
    
    public static Role getRole(int role_id) {
        switch (role_id) {
            case 1:
                return admin();
            case 2:
                return teacher();
            default:
                return candidate();
        }
    }

    // Create a static teacher
    public static Role teacher() {
        if (teacher == null) {
            teacher = new Role(TEACHER, "Manage tests");
        }
        return teacher;
    }
    
    public static Role admin() {
        if (admin == null) {
            admin = new Role(ADMIN, "Manage accounts");
        }
        return admin;
    }
    
    public static Role candidate() {
        if (candidate == null) {
            candidate = new Role(CANDIDATE, "");
        }
        return candidate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}
