/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.tests;

import core.util.field.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tinluu
 */
public class Question {

    final public static String TYPE = "question";
    final public static Field ID = Field.Int("id");
    final public static Field QTYPE = Field.Int("type");
    final public static Field DESCRIPTION = Field.String("description");
    private String description;
    private int type;
    private List<Answer> answers;
    // String before int
    final public static Field[] allColumns = { DESCRIPTION, QTYPE };
            
//    public Question()

    public Question(String description, int type, List<Answer> answers) {
        this.description = description;
        this.type = type;
        this.answers = answers;
    }
    
    public Question(String description) {
        this.description = description;
        this.type = 0;
        this.answers = new ArrayList<>();
    }
    
    public Question(String description, int type) {
        this.description = description;
        this.type = type;
        this.answers = new ArrayList<>();
    }

    public List<Answer> getAnswers() {
        return answers;
    }
    
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
    
    public void addAnswers(Answer answers) {
        this.answers.add(answers);
    }

    public String getDescription() {
        return description;
    }
    
    public int getType() {
        return type;
    }

    public static Question[] getQuickTest() {
        
        return null;
    }
    
}
