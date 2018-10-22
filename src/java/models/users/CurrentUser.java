/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.users;

import core.util.Strings;

/**
 *
 * @author tinluu
 */
public class CurrentUser {

    final public static String CURRENTUSER = "CurrentUser";
    private final int ID;
    private final String fullName;
    private final Role role;
    private final int age;
    private final String address;
    private final int phone;
    
    public CurrentUser(int ID, String fullName, Role role, int age, String address, int phone) {
        this.ID = ID;
        this.fullName = fullName;
        this.role = role;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }

    public boolean isTeacher() {
        return is(Role.TEACHER);
    }
    
    public boolean isAdmin() {
        return is(Role.ADMIN);
    }
    
    public boolean isCandidate() {
        return is(Role.CANDIDATE);
    }
    
    public String fullName() {
        return Strings.isNotEmpty(fullName) ? fullName : Guest.GUEST;
    }

    private boolean is(String roleName) {
        return roleName.equals(role.getName());
    }

    public boolean isNotGuest() {
        return role != null;
    }

    public boolean isGuest() {
        return !isNotGuest();
    }
    
    public Role getRole() {
        return role;
    }
}
