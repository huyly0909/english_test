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
public class Writing {

    final public static String TYPE = "writing";
    final public static Field ID = Field.Int("id");
    final public static Field TITLE = Field.String("title").clickable();
    
    final public static Field[] listViewColumns = {ID, TITLE};
    final public static Field[] allColumns = {TITLE};
    
    private String title;
    private List<Question> questions;

    public Writing(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
    }
    
    public Writing(String title, List<Question> questions) {
        this.title = title;
        this.questions = questions;
    }

    public Writing() {
        this.questions = new ArrayList<>();
    }
    
    public void addQuestion(Question question) {
        this.questions.add(question);
    }
    
    public List<Question> getQuestions() {
        return questions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
