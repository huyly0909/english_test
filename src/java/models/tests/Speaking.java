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
public class Speaking {
    final public static String TYPE = "speaking";
    final public static Field ID = Field.Int("id");
    final public static Field TITLE = Field.String("title").clickable();
    final public static Field DESCRIPTION = Field.String("description");
    
    final public static Field[] listViewColumns = {ID, TITLE};
    final public static Field[] allColumns = {TITLE, DESCRIPTION};
    
    private String title;
    private String description;

    public Speaking(String title, String description) {
        this.title = title;
        this.description = description;
    }
    
    public Speaking(String title) {
        this.title = title;
    }

    public Speaking() {}
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
