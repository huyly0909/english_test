/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.util.field;

import core.util.Strings;

/**
 *
 * @author tinluu
 */
public class Field {
    protected String name;
    protected String label;
    protected String type;
    protected boolean isMoney;

    public Field(String name, String label, String type) {
        this.name = name;
        this.label = label;
        this.type = type;
    }

    public static Field Int(String name, String label) {
        return new Field(name, label, "int");
    }
    
    public static Field Int(String name) {
        return new Field(name, Strings.getLabel(name), "int");
    }
    
    public static Field Boolean(String name) {
        return new Field(name, Strings.getLabel(name), "boolean");
    }
    
    public static Field Double(String name, String label) {
        return new Field(name, label, "double");
    }
    
    public static Field Double(String name) {
        return new Field(name, Strings.getLabel(name), "double");
    }
    
    public static Field String(String name, String label) {
        return new Field(name, label, "String");
    }
    
    public static Field String(String name) {
        return new Field(name, Strings.getLabel(name), "String");
    }
    
    public boolean isMoney() {
        return isMoney;
    }
    
    public Field Salary() {
        this.isMoney = true;
        return this;
    }

    public String Name() {
        return name;
    }
    
    public String Label() {
        return label;
    }
    
    public boolean isInt() {
        return "int".equals(type);
    }
    
    public boolean isString() {
        return "String".equals(type);
    }
    
    public boolean isDouble() {
        return "double".equals(type);
    }
}
