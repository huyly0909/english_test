/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.tests;

import core.util.field.Field;
import java.util.List;

/**
 *
 * @author tinluu
 */
public class Answer {
    
    final public static String TYPE = "answer";
    final public static Field QUESTION_ID = Field.Int("question_id");
    final public static Field DESCRIPTION = Field.String("description");
    final public static Field IS_CORRECT = Field.Boolean("is_correct");
    final public static Field[] allCreateColumns = { QUESTION_ID, DESCRIPTION, IS_CORRECT };
    final public static Field[] allColumns = { QUESTION_ID, DESCRIPTION, IS_CORRECT };
    private String description;
    private boolean isCorrect;
    private int questionId;

    public Answer(int questionId, String description, boolean isCorrect) {
        this.questionId = questionId;
        this.description = description;
        this.isCorrect = isCorrect;
    }
    
    public Answer(String description) {
        this.description = description;
    }
    
    public Answer(String description, boolean isCorrect) {
        this.description = description;
        this.isCorrect = isCorrect;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
    
    
}
