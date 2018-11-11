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
public class Reading {
    final public static String TYPE = "reading";
    final public static Field ID = Field.Int("id");
    final public static Field TITLE = Field.String("title");
    final public static Field DESCRIPTION = Field.String("description");
    
    final public static Field[] listViewColumns = {ID, TITLE, DESCRIPTION};
    final public static Field[] allColumns = {TITLE, DESCRIPTION};
    
    private String title;
    private String descrition;
    private List<Question> questions;

    public Reading(String title, String descrition) {
        this.title = title;
        this.descrition = descrition;
        this.questions = new ArrayList<>();
    }
    
    public Reading(String title, String descrition, List<Question> questions) {
        this.title = title;
        this.descrition = descrition;
        this.questions = questions;
    }

    public Reading() {
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

    public void setDescription(String descrition) {
        this.descrition = descrition;
    }

    public String getDescription() {
        return descrition;
    }
    
}
