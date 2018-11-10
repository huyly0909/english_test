package core.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import core.CoreSection.CoreSection;
import static core.CoreSection.HtmlContent.LIST_VIEW_CONTENT_JSP;
import core.op.GetListViewOp;
import core.op.db;
import static core.util.HtmlBuilder.ButtonBuilder.LOGOUT_BTN;
import static core.util.HtmlBuilder.ButtonBuilder.HOME_BTN;
import static core.util.HtmlBuilder.ButtonBuilder.TOP_BTN_NAME;
import core.util.HtmlBuilder.ListViewBuilder;
import core.util.HtmlBuilder.WebsiteBuilder;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.users.CurrentUser;
import models.users.User;

/**
 *
 * @author Administrator
 */
@WebServlet(urlPatterns = {"/account"})
public class AccountController extends HttpServlet {
    
    final public static String HEADER = "User Accounts";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        CurrentUser currentUser = SessionController.getCurrentUser(request);
        request.setAttribute("currentUser", currentUser);
        if (currentUser != null) {
            ResultSet resultSet = GetListViewOp.records(User.TYPE);
            ListViewBuilder.display(request, response, User.TYPE, HEADER, resultSet, User.allColumns);
        } else {
            response.sendRedirect(CoreSection.HOME);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
