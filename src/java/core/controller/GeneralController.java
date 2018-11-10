package core.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import core.CoreSection.CoreAction;
import core.CoreSection.CoreSection;
import core.CoreSection.HtmlContent;
import static core.controller.tests.QuickTestController.displayQuickTestListView;
import core.op.CreateOp;
import core.op.DeleteOp;
import static core.util.HtmlBuilder.ButtonBuilder.HOME_BTN;
import static core.util.HtmlBuilder.ButtonBuilder.LOGIN_BTN;
import static core.util.HtmlBuilder.ButtonBuilder.LOGOUT_BTN;
import static core.util.HtmlBuilder.ButtonBuilder.REGISTER_BTN;
import static core.util.HtmlBuilder.ButtonBuilder.TOP_BTN_NAME;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.tests.Answer;
import models.tests.Question;
import models.tests.QuestionType;
import models.users.CurrentUser;
import models.users.Guest;
import models.users.Role;
import models.users.User;

/**
 *
 * @author Administrator
 */
@WebServlet(urlPatterns = {"/general_controller"})
public class GeneralController extends HttpServlet {
    final public static String CREATE_USER_HEADER = "Create New User";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String type = request.getParameter("type");
        if (CoreAction.ADD.equals(action)) {
            if (Question.TYPE.equals(type)) { // Add new question
                int Qtype = Integer.parseInt(request.getParameter("question_type"));
                // For quick_test
                if(Qtype == (QuestionType.ADVANCED) || Qtype == (QuestionType.BASIC)) {
                    createNewQuickTestForm(Qtype, request, response);
                }
            } else { // Add new Others
                String buttons = "";
                String form = "";
                String header = "";
                // Pop-up the form when request is add new account
                if (User.TYPE.equals(type)) {
                    buttons = LOGOUT_BTN + HOME_BTN;
                    form = HtmlContent.CREATE_NEW_ACCOUNT_FORM;
                    header = CREATE_USER_HEADER;
                }
                request.setAttribute("type", type);
                request.setAttribute("formHeader", header);
                request.setAttribute(TOP_BTN_NAME, buttons);
                request.getRequestDispatcher(form).forward(request, response);
            }
        } else{
            String coreSection = "";
            if (User.TYPE.equals(type)) {
                    coreSection = CoreSection.ACCOUNT;
            }
            if (CoreAction.CREATE.equals(action)) { // Create after submit creation form
                CreateOp.create(type, request);
            } else if (CoreAction.DELETE.equals(action)) {
                DeleteOp.execute(type, request.getParameter("ids"));
                if (Question.TYPE.equals(type)) {
                    int Qtype = Integer.parseInt(request.getParameter("question_type"));
                    boolean isAdvanced = Qtype == QuestionType.ADVANCED;
                    DeleteOp.execute(Answer.TYPE, Answer.QUESTION_ID, request.getParameter("ids"));
                    displayQuickTestListView(request, response, isAdvanced);
                }
            }
            if (coreSection != "") {
                response.sendRedirect(coreSection);
            }
        }
    }
    
    public void createNewQuickTestForm(int type, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException {
        boolean isAdvanced = type == QuestionType.ADVANCED;
        String header = String.format("Create New %s Quick Test", isAdvanced ? "Advanced" : "Basic");
        request.setAttribute("formHeader", header);
        // generate form
        request.setAttribute(TOP_BTN_NAME, LOGOUT_BTN + HOME_BTN);
        String form = isAdvanced ? HtmlContent.CREATE_NEW_ADVANCED_TEST_FORM : HtmlContent.CREATE_NEW_BASIC_TEST_FORM;
        request.getRequestDispatcher(form).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GeneralController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GeneralController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GeneralController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GeneralController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(GeneralController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GeneralController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GeneralController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GeneralController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GeneralController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(GeneralController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
