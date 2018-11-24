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
public class QuestionType {
    final public static int ADVANCED = 0;
    final public static int BASIC = 1;
    final public static int READING = 2;
    final public static int LISTENING = 3;
    final public static int WRITING = 4;
    
    public static int get(String type) {
        switch (type) {
            case "advanced":
                return ADVANCED;
            case "basic":
                return BASIC;
            case Reading.TYPE:
                return READING;
            case Listening.TYPE:
                return LISTENING;
            case Writing.TYPE:
                return WRITING;
            default:
                return -1;
        }
    }
}
