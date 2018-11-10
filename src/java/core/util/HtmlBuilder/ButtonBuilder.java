/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.util.HtmlBuilder;

/**
 *
 * @author tinluu
 */
public class ButtonBuilder {
    final public static String TOP_BTN_NAME = "topBtn";
    final public static String LOGIN_BTN = "<a href='/build/login'><div class='div-btn top-btn first-btn'>Login</div></a> ";
    final public static String REGISTER_BTN = "<a href='/build/register'><div class='div-btn top-btn second-btn'>Register</div></a> ";
    final public static String LOGOUT_BTN = "<a href='/build/logout'><div class='div-btn top-btn first-btn'>Logout</div></a> ";
    final public static String HOME_BTN = "<a href='/build/home'><div class='div-btn top-btn second-btn'>Home</div></a> ";
    final public static String SUBMIT_BTN = "<input class='normal-btn' type='submit' value='Submit' onclick='checkResult()'> ";
    final public static String TOTAL_CORE = "<div type='text' id='totalCore' class='total-core' style=''></div> ";
    final public static String DO_OTHER_TEST_BTN = "<a href='/build/home' class='middle-inline-block'><div id='doOtherTestBtn' style='display: none' class='normal-btn'>Do other tests</div></a> ";
    
    public static String continueBtn(String testName) {
        return "<a href='/build/" + testName + "' class='middle-inline-block'><div id='continueBtn' style='display: none' class='normal-btn'>Continue</div></a> ";
    }
}
