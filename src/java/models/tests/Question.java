/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.tests;

/**
 *
 * @author tinluu
 */
public class Question {

    final public static String TYPE = "question";
    final public static String IS_QUICK_TEST = "is_quick_test";
    final public static String DESCRIPTION = "description";
    final public static String A = "a";
    final public static String B = "b";
    final public static String C = "c";
    final public static String D = "d";
    final public static String CORRECT_ANSWER = "correct_answer";
    final private String description;
    final private String a;
    final private String b;
    final private String c;
    final private String d;
    final private String correctAnswer;
    // String before int
    final public static String[] allColumns = { DESCRIPTION, A, B, C, D, CORRECT_ANSWER, IS_QUICK_TEST };
            
//    public Question()

    public Question(String description, String a, String b, String c, String d, String correctAnswer) {
        this.description = description;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.correctAnswer = correctAnswer;
    }

    public String getDescription() {
        return description;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
    
    public static Question[] getQuickTest() {
        
        return null;
    }
    
}
