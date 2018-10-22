package core.controller.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import core.CoreSection.HtmlContent;
import core.controller.SessionController;
import core.op.CreateNewQuickTestOp;
import core.op.GetQuickTestOp;
import core.util.HtmlBuilder.ButtonBuilder;
import static core.util.HtmlBuilder.ButtonBuilder.HOME_BTN;
import static core.util.HtmlBuilder.ButtonBuilder.LOGIN_BTN;
import static core.util.HtmlBuilder.ButtonBuilder.LOGOUT_BTN;
import static core.util.HtmlBuilder.ButtonBuilder.REGISTER_BTN;
import static core.util.HtmlBuilder.ButtonBuilder.TOP_BTN_NAME;
import core.util.HtmlBuilder.QuestionHTMLBuilder;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.tests.Question;
import models.users.CurrentUser;

/**
 *
 * @author Administrator
 */
@WebServlet(urlPatterns = {"/quick_test"})
public class QuickTestController extends HttpServlet {
    
    final public static String CREATE_TEST_FORM = "createTestForm";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        String action = (String) request.getParameter("action");
        if(action != null && action.equals("create")) {
            CreateNewQuickTestOp.create(newValues(request));
            request.setAttribute("isSuccessful", true);
            createNewQuickTestForm(request, response);
        } else {
            if (SessionController.isTeacher(request)) {
                createNewQuickTestForm(request, response);
            } else {
                createQuickTestForm(request, response);
            }
        }
    }
    
    private String[] newValues(HttpServletRequest request) {
        String[] result = new String[6];
        String[] allColumns = Question.allColumns;
        // Question.allColumns.length - 1 : 
        for (int i = 0; i < allColumns.length - 1; i++) {
            result[i] = request.getParameter(allColumns[i]);
        }
        return result;
    }
    
    public void createNewQuickTestForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException {
        request.setAttribute("formHeader", "Create new Quick Test");
        request.setAttribute(TOP_BTN_NAME, LOGOUT_BTN + HOME_BTN);
        request.getRequestDispatcher(HtmlContent.CREATE_NEW_TEST_FORM).forward(request, response);
    }
    
    public static String testHeader(String header) {
        return "<h1 class='test-form-title'>" + header + "</h1>";
    }

    public void createQuickTestForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException {
        // Create test header
        request.setAttribute("testHeader", testHeader("Quick Test"));
        // Get Random 10 questions
        ArrayList<Question> questionList = GetQuickTestOp.getRandomQuestions(10);
        String tenQuestionsHTMLForm = "";
        for (Question question : questionList) {
            tenQuestionsHTMLForm += QuestionHTMLBuilder.build(question, questionList.indexOf(question) + 1);
        }
        String fullQuicktest = tenQuestionsHTMLForm 
                + ButtonBuilder.SUBMIT_BTN 
                + ButtonBuilder.TOTAL_CORE
                + ButtonBuilder.continueBtn("quick_test") 
                + ButtonBuilder.DO_OTHER_TEST_BTN; 
       // Add full test form
        request.setAttribute("testForm", fullQuicktest);
//        // Add total core 
//        request.setAttribute("testResult", ButtonBuilder.TOTAL_CORE);
        // Add buttons
        CurrentUser currentUser = SessionController.getCurrentUser(request);
        // If currentUser is guest => create login and register button else create logout button
        String topBtn = currentUser != null ? LOGOUT_BTN : (LOGIN_BTN + REGISTER_BTN);
        request.setAttribute(TOP_BTN_NAME, topBtn);
        request.getRequestDispatcher(HtmlContent.TEST_FORM).forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuickTestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(QuickTestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(QuickTestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(QuickTestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(QuickTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuickTestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(QuickTestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(QuickTestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(QuickTestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(QuickTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
