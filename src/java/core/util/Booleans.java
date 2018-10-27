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
public class Booleans {
    public static boolean isTrue(int value) {
        return value == 1;
    }
    
    public static boolean isFalse(int value) {
        return value == 0;
    }

    public static boolean isNumber(String str) {
        return str.matches("[+-]?\\d+([\\.]\\d+)?");
    }
    
    public static int convertToInt(boolean value) {
        return value ? 1 : 0;
    }
}
