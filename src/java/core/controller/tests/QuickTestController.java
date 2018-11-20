package core.controller.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import core.CoreSection.CoreAction;
import core.CoreSection.HtmlContent;
import core.controller.SessionController;
import core.op.CreateNewQuickTestOp;
import core.op.GetListViewOp;
import core.op.GetQuickTestOp;
import core.util.Booleans;
import core.util.ConditionBuilder;
import core.util.HtmlBuilder.ButtonBuilder;
import static core.util.HtmlBuilder.ButtonBuilder.HOME_BTN;
import static core.util.HtmlBuilder.ButtonBuilder.LOGIN_BTN;
import static core.util.HtmlBuilder.ButtonBuilder.LOGOUT_BTN;
import static core.util.HtmlBuilder.ButtonBuilder.REGISTER_BTN;
import static core.util.HtmlBuilder.ButtonBuilder.TOP_BTN_NAME;
import core.util.HtmlBuilder.ListViewBuilder;
import core.util.HtmlBuilder.QuestionHTMLBuilder;
import core.util.field.Field;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.tests.Answer;
import models.tests.Question;
import models.tests.QuestionType;
import models.users.CurrentUser;

/**
 *
 * @author Administrator
 */
@WebServlet(urlPatterns = {"/quick_test"})
public class QuickTestController extends HttpServlet {
    
    final public static String CREATE_TEST_FORM = "createTestForm";
    final private static String ADVANCED_HEADER = "Advanced Quick Tests";
    final private static String BASIC_HEADER = "Basic Quick Tests";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        String action = (String) request.getParameter("action");
        // After create new quick test
        if(action != null && action.equals(CoreAction.CREATE)) {
            CreateNewQuickTestOp.create(newQuestion(request));
            request.setAttribute("isSuccessful", true);
            int type = Integer.parseInt(request.getParameter("type"));
            createNewQuickTestForm(type, request, response);
        } else {
            boolean isAdvanced = CoreAction.ADVANCED.equals(action);
            // If current user is teacher => display quick test
            // Else do test
            if (SessionController.isTeacher(request)) {
                displayQuickTestListView(request, response, isAdvanced);
            } else {
                getQuickTests(isAdvanced, request, response);
            }
        }
    }
    
    public static void displayQuickTestListView (HttpServletRequest request, HttpServletResponse response, boolean isAdvanced) throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException {
                String header = isAdvanced ? ADVANCED_HEADER : BASIC_HEADER;
                int type = isAdvanced ? QuestionType.ADVANCED : QuestionType.BASIC;
                // Get list of question
                ResultSet resultSet = GetListViewOp.records(Question.TYPE, ConditionBuilder.get(Question.QTYPE.Name(), type));
                ListViewBuilder.display(request, response, Question.TYPE, type, header, resultSet, Question.listViewColumns);
    }
    
    private Question newQuestion(HttpServletRequest request) {
        return new Question(
                request.getParameter(Question.DESCRIPTION.Name()),
                Integer.parseInt(request.getParameter(Question.QTYPE.Name())),
                newAnswers(request)
        );
    }
    
    private List<Answer> newAnswers(HttpServletRequest request) {
        int answerSize = Integer.parseInt(request.getParameter("answerSize"));
        List<Answer> answers = new ArrayList<>();
                
        for (int i = 0; i <     answerSize; i++) {
            String description = request.getParameter(Answer.DESCRIPTION.Name() + i);
            String isCorrect = request.getParameter(Answer.IS_CORRECT.Name() + i);
            answers.add(new Answer(
                    description,
                    Booleans.checkBox(isCorrect)
            ));
        }
        return answers;
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
    
    public static String testHeader(String header) {
        return "<h1 class='test-form-title'>" + header + "</h1>";
    }

    public void getQuickTests(boolean isAdvanced, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException {
        // Create test header
        request.setAttribute("testHeader", testHeader("Quick Test"));
        // Get Random 10 questions
        ArrayList<Question> questionList = GetQuickTestOp.getRandomQuestions(10, isAdvanced);
        String questionsHTMLForm = "";
        for (Question question : questionList) {
            questionsHTMLForm += QuestionHTMLBuilder.build(question, questionList.indexOf(question) + 1, isAdvanced);
        }
        String fullQuicktest = questionsHTMLForm 
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
