/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tinluu
 */
public class Strings {

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
    
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    public static String format(String text, HashMap<String, String>... maps) {
        for(HashMap<String, String> map : maps) {
        }
        return "";
    }
    
    /* 
    values's length is at least 2
    values: key, value, key, value
    Use: replace {key} = new value
    */
    public static String format(String text, String... values) {
        HashMap<String, String> map = new HashMap();
        for (int i = 0; i < values.length - 1; i+=2) {
            map.put(values[i], values[i + 1]);
        }
        return format(text, map);
    }
    
    public static String format(String text, HashMap<String, String> map) {
        for(String key : map.keySet()) {
            text = text.replace("{" + key + "}", map.get(key));
        }
        return text;
    }
}
