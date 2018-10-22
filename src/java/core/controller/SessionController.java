/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import models.users.CurrentUser;

/**
 *
 * @author tinluu
 */
public class SessionController {
    
    public static CurrentUser getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        CurrentUser currentUser = null;
        try {
            currentUser = (CurrentUser) session.getAttribute("currentUser");
            request.setAttribute("currentUser", currentUser);
        } catch (Exception e) {}
        return currentUser;
    }

    public static boolean isMember(HttpServletRequest request) {
        return getCurrentUser(request) != null;
    }

    public static boolean isTeacher(HttpServletRequest request) {
        CurrentUser currentUser = getCurrentUser(request);
        return currentUser != null && currentUser.isTeacher();
    }
}