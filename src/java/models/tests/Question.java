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

    final public static String TYPE = "question"; // used for Advanced, Basic, Writing test.
    final public static Field ID = Field.Int("id");
    final public static Field QTYPE = Field.Int("type");
    final public static Field DESCRIPTION = Field.String("description").clickable();
    final public static Field PARENT_ID = Field.Int("parent_id");
    protected int id;
    protected String description;
    protected int type;
    protected List<Answer> answers;
    final public static Field[] allColumns = { DESCRIPTION, QTYPE };
    final public static Field[] allCreateColumns = { DESCRIPTION, QTYPE, PARENT_ID };
    final public static Field[] listViewColumns = { ID, DESCRIPTION };
            
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
    
    public Question(int id, String description) {
        this.id = id;
        this.description = description;
        this.answers = new ArrayList<>();
    }
    
    public Question(String description, int type) {
        this.description = description;
        this.type = type;
        this.answers = new ArrayList<>();
    }
    
    public Question() {}

    public List<Answer> getAnswers() {
        return answers;
    }
    
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
    
    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public String getDescription() {
        return description;
    }
    
    public int getType() {
        return type;
    }

    public int getID () {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
