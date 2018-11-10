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
public class Listening {
    final public static String TYPE = "listening";
    final public static Field ID = Field.Int("id");
    final public static Field TITLE = Field.String("title");
    final public static Field URL = Field.String("url");

    final public static Field[] listViewColumns = {ID, TITLE, URL};
    final public static Field[] allColumns = {TITLE, URL};

    private String title;
    private String url;
    private List<Question> questions;

    public Listening(String title, String url) {
        this.title = title;
        this.url = url;
        this.questions = new ArrayList<>();
    }

    public Listening() {
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

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
    
}
