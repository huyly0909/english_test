package core.controller.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import core.CoreSection.CoreSection;
import core.CoreSection.HtmlContent;
import core.controller.GeneralController;
import core.controller.SessionController;
import static core.controller.tests.QuickTestController.testHeader;
import core.op.CreateNewTestOp;
import core.op.GetListViewOp;
import core.op.GetListeningTestOp;
import core.op.GetReadingTestOp;
import core.op.GetWritingTestOp;
import core.util.Booleans;
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
import models.tests.Listening;
import models.tests.Question;
import models.tests.QuestionType;
import models.tests.Reading;
import models.tests.Speaking;
import models.tests.Writing;
import models.users.CurrentUser;
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
    final private static String SPEAKING_TESTS_HEADER = "Speaking Tests";
    final private static String READING = "reading";
    final private static String DO_READING = "doreading";
    final private static String CREATE_READING = "createreading";
    final private static String LISTENING = "listening";
    final private static String DO_LISTENING = "dolistening";
    final private static String WRITING = "writing";
    final private static String DO_WRITING = "dowriting";
    final private static String CREATE_LISTENING = "createlistening";
    final private static String CREATE_WRITING = "createwriting";
    final private static String SPEAKING = "speaking";
    final private static String CREATE_SPEAKING = "createspeaking";
    final private static String DO_SPEAKING = "dospeaking";
    
    /**
     * Used to control Writing, Listening, Speaking test.
     * User need to login to do those tests.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        CurrentUser currentUser = SessionController.getCurrentUser(request);
        
        if (currentUser.isGuest()) {
            response.sendRedirect(CoreSection.HOME);
        } else {
            String action = (String) request.getParameter("action");
            ResultSet resultSet;
            int id;
            if (null != action) 
                if (currentUser.isCandidate()) switch (action) {
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
                        ListViewBuilder.display(request, response, Listening.TYPE, QuestionType.LISTENING, LISTENING_TESTS_HEADER, false, false, false, false, resultSet, Listening.listViewColumns);
                        break;
                    case DO_LISTENING:
                        id = Integer.parseInt(request.getParameter("ids"));
                        getListeningTest(request, response, id);
                        break;
                    case WRITING: // Pop-up writing test.
                        resultSet = GetListViewOp.records(Writing.TYPE);
                        ListViewBuilder.display(request, response, Writing.TYPE, QuestionType.WRITING, WRITING_TESTS_HEADER, false, false, false, false, resultSet, Writing.listViewColumns);
                        break;
                    case DO_WRITING:
                        id = Integer.parseInt(request.getParameter("ids"));
                        getWritingTest(request, response, id);
                        break;    
                    default:
                        break;
            } else if (currentUser.isTeacher()) switch (action) {
                case READING: // Pop-up reading test.
                    resultSet = GetListViewOp.records(Reading.TYPE);
                    ListViewBuilder.display(request, response, Reading.TYPE, QuestionType.READING, READING_TESTS_HEADER, resultSet, Reading.ID, Reading.TITLE);
                    break;
                case LISTENING: // Pop-up listening test.
                    resultSet = GetListViewOp.records(Listening.TYPE);
                    ListViewBuilder.display(request, response, Listening.TYPE, QuestionType.LISTENING, LISTENING_TESTS_HEADER, resultSet, Listening.listViewColumns);
                    break;
                case WRITING: // Pop-up Writing test.
                    resultSet = GetListViewOp.records(Writing.TYPE);
                    ListViewBuilder.display(request, response, Writing.TYPE, QuestionType.WRITING, WRITING_TESTS_HEADER, resultSet, Writing.listViewColumns);
                    break;
                case SPEAKING: // Pop-up Speaking test.
                    resultSet = GetListViewOp.records(Speaking.TYPE);
                    ListViewBuilder.display(request, response, Speaking.TYPE, QuestionType.SPEAKING, SPEAKING_TESTS_HEADER, resultSet, Speaking.listViewColumns);
                    break;
                case CREATE_READING:
                    CreateNewTestOp.create(newReadingTest(request));
                    request.setAttribute("isSuccessful", true);
                    GeneralController.createNewTestForm(request, response, Reading.TYPE);
                    break;
                case CREATE_LISTENING:
                    CreateNewTestOp.create(newListeningTest(request));
                    request.setAttribute("isSuccessful", true);
                    GeneralController.createNewTestForm(request, response, Listening.TYPE);
                    break;
                case CREATE_WRITING:
                    CreateNewTestOp.create(newWritingTest(request));
                    request.setAttribute("isSuccessful", true);
                    GeneralController.createNewWritingTestForm(request, response);
                    break;
                default:
                    break;
            }
        }
    }
    
    private Reading newReadingTest(HttpServletRequest request) {
                return new Reading(
                    request.getParameter(Reading.TITLE.Name()),
                    request.getParameter(Reading.DESCRIPTION.Name()),
                    newQuestions(request)
                );
    }

    private Listening newListeningTest(HttpServletRequest request) {
        return new Listening(
                    request.getParameter(Listening.TITLE.Name()),
                    request.getParameter("description"),
                    newQuestions(request)
                );
    }
    
    private Writing newWritingTest(HttpServletRequest request) {
        return new Writing(
                    request.getParameter(Listening.TITLE.Name()),
                    newWritingQuestions(request)
                );
    }
    
    private List<Question> newWritingQuestions(HttpServletRequest request) {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            questions.add(
                    new Question(
                        request.getParameter(Question.DESCRIPTION.Name() + "_q" + i), // description_q0, description_q1,..., description_q9
                        QuestionType.WRITING,
                        newWritingAnswers(request, i)
                    )
            );
        }
        return questions;
    }
    
    private List<Answer> newWritingAnswers(HttpServletRequest request, int questionIndex) {
        List<Answer> answers = new ArrayList<>();
        String description = request.getParameter("answer_q" + questionIndex); // answer_q1, answer_q2,..
        answers.add(new Answer(description));
        return answers;
    }

    private List<Question> newQuestions(HttpServletRequest request) {
        List<Question> questions = new ArrayList<>();
        int questionType = QuestionType.get((String) request.getParameter(Question.QTYPE.Name()));
        for (int i = 0; i <= 9; i++) {
            questions.add(
                    new Question(
                        request.getParameter(Question.DESCRIPTION.Name() + "_q" + i), // description_q0, description_q1,..., description_q9
                        questionType,
                        newAnswers(request, i)
                    )
            );
        }
        return questions;
    }
    
    private List<Answer> newAnswers(HttpServletRequest request, int questionIndex) {
        List<Answer> answers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String description = request.getParameter(Answer.DESCRIPTION.Name() + i + "_q" + questionIndex);
            String isCorrect = request.getParameter(Answer.IS_CORRECT.Name() + i + "_q" + questionIndex);
            answers.add(new Answer(
                    description,
                    Booleans.checkBox(isCorrect)
            ));
        }
        return answers;
    }
    
    private void getReadingTest(HttpServletRequest request, HttpServletResponse response, int id) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException, ServletException, IOException {
        Reading test = GetReadingTestOp.execute(id);
        // Create test header
        request.setAttribute("testHeader", testHeader(test.getTitle()));
        request.setAttribute("testDescription", test.getDescription());
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
    
    private void getWritingTest(HttpServletRequest request, HttpServletResponse response, int id) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException, ServletException, IOException {
        Writing test = GetWritingTestOp.execute(id);
        // Create test header
        request.setAttribute("testHeader", WRITING_TESTS_HEADER);
        String tenQuestionsHTMLForm = "";
        List<Question> questionList = test.getQuestions();
        for (Question question : questionList) {
            tenQuestionsHTMLForm += QuestionHTMLBuilder.buildWriting(question, questionList.indexOf(question) + 1);
        }
        String fullTest = tenQuestionsHTMLForm 
                + ButtonBuilder.SUBMIT_BTN 
                + ButtonBuilder.TOTAL_CORE
                + ButtonBuilder.DO_OTHER_TEST_BTN; 
       // Add full test form
        request.setAttribute("testForm", fullTest);
        request.setAttribute("testType", Writing.TYPE);
        request.setAttribute("testDescription", "Re-write and correct sentences");
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

    private void CreateNewTestOp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
