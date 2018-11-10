package core.controller.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import core.CoreSection.CoreSection;
import core.CoreSection.HtmlContent;
import static core.controller.AccountController.HEADER;
import core.controller.SessionController;
import static core.controller.tests.QuickTestController.testHeader;
import core.op.GetListViewOp;
import core.op.GetListeningTestOp;
import core.op.GetReadingTestOp;
import core.util.HtmlBuilder.ButtonBuilder;
import static core.util.HtmlBuilder.ButtonBuilder.HOME_BTN;
import static core.util.HtmlBuilder.ButtonBuilder.LOGOUT_BTN;
import static core.util.HtmlBuilder.ButtonBuilder.TOP_BTN_NAME;
import core.util.HtmlBuilder.ListViewBuilder;
import core.util.HtmlBuilder.QuestionHTMLBuilder;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.tests.Listening;
import models.tests.Question;
import models.tests.Reading;
import models.users.User;

/**
 *
 * @author Administrator
 */
@WebServlet(urlPatterns = {"/test"})
public class TestController extends HttpServlet {
    
    final private static String READING_TESTS_HEADER = "Reading Tests";
    final private static String WRITING_TESTS_HEADER = "Writing Tests";
    final private static String LISTENING_TESTS_HEADER = "Listening Tests";
    final private static String READING = "reading";
    final private static String DO_READING = "doreading";
    final private static String LISTENING = "listening";
    final private static String DO_LISTENING = "dolistening";
    
    /**
     * Used to control Writing, Listening, Speaking test.
     * User need to login to do those tests.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        if (!SessionController.isMember(request)) {
            response.sendRedirect(CoreSection.HOME);
        } else {
            String action = (String) request.getParameter("action");
            ResultSet resultSet;
            int id;
            if (null != action) switch (action) {
                case READING: // Pop-up reading test.
                    resultSet = GetListViewOp.records(Reading.TYPE);
                    ListViewBuilder.display(request, response, Reading.TYPE, READING_TESTS_HEADER, false, false, false, false, resultSet, Reading.ID, Reading.TITLE);
                    break;
                case DO_READING:
                    id = Integer.parseInt(request.getParameter("ids"));
                    getReadingTest(request, response, id);
                    break;
                case LISTENING: // Pop-up listening test.
                    resultSet = GetListViewOp.records(Listening.TYPE);
                    ListViewBuilder.display(request, response, Listening.TYPE, READING_TESTS_HEADER, false, false, false, false, resultSet, Listening.listViewColumns);
                    break;
                case DO_LISTENING:
                    id = Integer.parseInt(request.getParameter("ids"));
                    getListeningTest(request, response, id);
                    break;
                default:
                    break;
            }
        }
    }
    
    private void getReadingTest(HttpServletRequest request, HttpServletResponse response, int id) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException, ServletException, IOException {
        // Get Random 10 questions
        Reading test = GetReadingTestOp.execute(id);
        // Create test header
        request.setAttribute("testHeader", testHeader(test.getTitle()));
        request.setAttribute("testDescription", test.getDescrition());
        String tenQuestionsHTMLForm = "";
        List<Question> questionList = test.getQuestions();
        for (Question question : questionList) {
            tenQuestionsHTMLForm += QuestionHTMLBuilder.build(question, questionList.indexOf(question) + 1);
        }
        String fullTest = tenQuestionsHTMLForm 
                + ButtonBuilder.SUBMIT_BTN 
                + ButtonBuilder.TOTAL_CORE
                + ButtonBuilder.DO_OTHER_TEST_BTN; 
       // Add full test form
        request.setAttribute("testForm", fullTest);
        request.setAttribute(TOP_BTN_NAME, LOGOUT_BTN + HOME_BTN);
        request.getRequestDispatcher(HtmlContent.TEST_FORM).forward(request, response);
    }
    
    private void getListeningTest(HttpServletRequest request, HttpServletResponse response, int id) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException, ServletException, IOException {
        // Get Random 10 questions
        Listening test = GetListeningTestOp.execute(id);
        // Create test header
        request.setAttribute("testHeader", testHeader(test.getTitle()));
        request.setAttribute("testDescription", test.getUrl());
        String tenQuestionsHTMLForm = "";
        List<Question> questionList = test.getQuestions();
        for (Question question : questionList) {
            tenQuestionsHTMLForm += QuestionHTMLBuilder.build(question, questionList.indexOf(question) + 1);
        }
        String fullTest = tenQuestionsHTMLForm 
                + ButtonBuilder.SUBMIT_BTN 
                + ButtonBuilder.TOTAL_CORE
                + ButtonBuilder.DO_OTHER_TEST_BTN; 
       // Add full test form
        request.setAttribute("testForm", fullTest);
        request.setAttribute(TOP_BTN_NAME, LOGOUT_BTN + HOME_BTN);
        request.getRequestDispatcher(HtmlContent.TEST_FORM).forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
