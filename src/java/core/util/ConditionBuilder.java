/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.util;

/**
 *
 * @author tinluu
 */
public class ConditionBuilder {

    public static String get(String field, String value) {
        return String.format("`%s` = '%s'", field, value);
    }
    
    public static String get(String field, boolean value) {
        return String.format("`%s` = %s", field, value ? 1 : 0);
    }
    
    public static String get(String field, int value) {
        return String.format("`%s` = %s", field, value);
    }
}
