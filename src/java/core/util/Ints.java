/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.util;

import java.util.concurrent.ThreadLocalRandom;
/**
 *
 * @author tinluu
 */
public class Ints {
    
    /*
    * min = 0
    * max = 2
    * result is in [0, 2]
    */
    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
    
    public static int getRandomInt(int max) {
        return getRandomInt(0, max);
    }
}
