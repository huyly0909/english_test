package core.util.HtmlBuilder;


import static core.CoreSection.HtmlContent.LIST_VIEW_CONTENT_JSP;
import static core.controller.AccountController.HEADER;
import core.util.Booleans;
import static core.util.HtmlBuilder.ButtonBuilder.HOME_BTN;
import static core.util.HtmlBuilder.ButtonBuilder.LOGOUT_BTN;
import static core.util.HtmlBuilder.ButtonBuilder.TOP_BTN_NAME;
import core.util.field.Field;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.users.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tinluu
 */
public class ListViewBuilder {
    
    final public static String EDIT_BTN = "<div type='button' onclick='editAction()' class='div-btn list-view-btn list-view-edit-btn' style=\"display: none\">Edit</div>";
    final public static String DELETE_BTN = "<div type='button' onclick='deleteAction()' class='div-btn list-view-btn list-view-delete-btn'>Delete</div>";
    final public static String ADD_BTN = "<div type='button' onclick='addAction()' class='div-btn list-view-btn list-view-add-btn'>Add</div>";
    final private static String STR_TD_STYLE = "str-col-style";
    final private static String NUMBER_TD_STYLE = "number-col-style";
    final private static String TABLE = "<table class='list-view-table' border=1>%s</table>";
    final private static String ROW = "<tr class='list-view-tr'>%s</tr>";
    final private static String COL_HEADER = "<th class='list-view-td-header %s'><span>%s</span></th>";
    final private static String COL = "<td class='list-view-td %s'><span>%s</span></td>";
    final private static String CLICKABLE_COL = "<td class='list-view-td clickable-text %s' onClick='edit(%d)'><span>%s</span></td>";
    final private static String HEADER_CHECKER = "<th class='list-view-td-header'><input onclick='checkerHeader()' type='checkbox' id='headerChecker'></input></th>";
    final private static String ROW_CHECKER = "<th class='list-view-td'><input type='checkbox' onclick='checkerRow()' value='%s' id='rowChecker'></input></th>";
    final public static DecimalFormat formatter = new DecimalFormat("#,###");
//    final public static DecimalFormat formatter = new DecimalFormat("#,###.00");
  /**
  - row have many cols (contain details of object)
  - table have many rows
  <table>
    <tr>
      <th>...</th>
      <th>...</th>
      ...
    </tr>
    <tr>
      <td>...</td>
      <td>...</td>
      ...
    </tr>
    ...
  </table>
   **/
  public static String build(ResultSet resultSet, boolean headerChecker, Field... columns) throws SQLException {
    String rows = "";
    // Add header row
    if (headerChecker) {
        rows += HEADER_CHECKER;
    }
//    boolean hasIdCol = false;
    for (Field field : columns) {
//        if (!hasIdCol && "id".equals(field.Name())) { // check do we have id column? id column always is the first col
//            hasIdCol = true;
//        }
        String style = field.isString() ? STR_TD_STYLE : NUMBER_TD_STYLE;
        rows += String.format(COL_HEADER, style, field.Label());
    }
    // Add details rows
    int id = 1; // id
    while (resultSet.next()) {
      String cols = "";
      if (headerChecker) {
        cols = String.format(ROW_CHECKER, resultSet.getInt(User.ID.Name()));
      }
      for (Field field : columns) {
        cols += field.isId() ? generateColHtml(id++, NUMBER_TD_STYLE) : buildCol(resultSet, field);
      }
      rows += String.format(ROW, cols);
    }
    // Add rows into table
    return String.format(TABLE, rows);
  }

  public static String buildCol(ResultSet resultSet, Field field) throws SQLException {
    String val = "";
    String colName = field.Name();
    if (field.isInt()) {
        return generateColHtml(resultSet.getInt(colName), NUMBER_TD_STYLE);
    } else if (field.isDouble()) {
        return generateColHtml((field.isMoney() ? "$" : "") + resultSet.getDouble(colName), NUMBER_TD_STYLE);
    } else if (field.isClickable()) {
        return generateClickableColHtml(resultSet.getString(colName), resultSet.getInt("id"), STR_TD_STYLE);
    }
    return generateColHtml(resultSet.getString(colName), STR_TD_STYLE);
  }
  
  public static String buildIdCol(int id) throws SQLException {
    return generateColHtml(id, NUMBER_TD_STYLE);
  }
  
  private static String generateColHtml(String val, String style) {
      return String.format(COL, style, val);
  }
  
  private static String generateClickableColHtml(String val, int id, String style) {
      return String.format(CLICKABLE_COL, style, id, val);
  }
  
  private static String generateColHtml(int val, String style) {
      return String.format(COL, style, formatter.format(val));
  }
  
  private static String generateColHtml(double val, String style) {
      return String.format(COL, style, formatter.format(val));
  }
  
  public static void display(HttpServletRequest request, HttpServletResponse response, String type, String header, ResultSet resultSet, Field... columns) throws SQLException, ServletException, IOException {
      display(request, response, type, -1, header, true, true, true, true, resultSet, columns);
  }

  public static void display(HttpServletRequest request, HttpServletResponse response, String type, String header, boolean addBtn, boolean deleteBtn, boolean editBtn, boolean headerChecker, ResultSet resultSet, Field... columns) throws SQLException, ServletException, IOException {
      display(request, response, type, -1, header, addBtn, deleteBtn, editBtn, headerChecker, resultSet, columns);
  }
  
  public static void display(HttpServletRequest request, HttpServletResponse response, String type, int questionType, String header, ResultSet resultSet, Field... columns) throws SQLException, ServletException, IOException {
      display(request, response, type, questionType, header, true, true, true, true, resultSet, columns);
  }

  public static void display(HttpServletRequest request, HttpServletResponse response, String type, int questionType, String header, boolean addBtn, boolean deleteBtn, boolean editBtn, boolean headerChecker, ResultSet resultSet, Field... columns) throws SQLException, ServletException, IOException {
    String body = ListViewBuilder.build(resultSet, headerChecker, columns);
    request.setAttribute("type", type);
    request.setAttribute("questionType", questionType);
    // Add buttons
    String buttons  = "";
    if (addBtn) {
        buttons += ADD_BTN;
    }
    if (deleteBtn) {
        buttons += DELETE_BTN;
    }
    if (editBtn) {
        buttons += EDIT_BTN;
    }
    if (!buttons.equals("")) {
        request.setAttribute("actionBtn", buttons);
    }
    request.setAttribute("listHeader", WebsiteBuilder.Header(header));
    request.setAttribute("listBody", body);
    request.setAttribute(TOP_BTN_NAME, LOGOUT_BTN + HOME_BTN);
    request.getRequestDispatcher(LIST_VIEW_CONTENT_JSP).forward(request, response);
  }
}
