package core.util.HtmlBuilder;


import core.util.Booleans;
import core.util.field.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
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
    
    final public static String EDIT_BTN = "<div type='button' class='div-btn list-view-btn list-view-edit-btn' style=\"display: none\">Edit</div>";
    final public static String DELETE_BTN = "<div type='button' class='div-btn list-view-btn list-view-delete-btn'>Delete</div>";
    final public static String ADD_BTN = "<div type='button' class='div-btn list-view-btn list-view-add-btn'>Add</div>";
    final private static String STR_TD_STYLE = "str-col-style";
    final private static String NUMBER_TD_STYLE = "number-col-style";
    final private static String TABLE = "<table class='list-view-table' border=1>%s</table>";
    final private static String ROW = "<tr class='list-view-tr'>%s</tr>";
    final private static String COL_HEADER = "<th class='list-view-td-header %s'><span>%s</span></th>";
    final private static String COL = "<td class='list-view-td %s'><span>%s</span></td>";
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
  public static String build(ResultSet resultSet, Field... columns) throws SQLException {
    String rows = "";
    // Add header row
    rows += HEADER_CHECKER;
    for (Field field : columns) {
        String style = field.isString() ? STR_TD_STYLE : NUMBER_TD_STYLE;
        rows += String.format(COL_HEADER, style, field.Label());
    }
    // Add details rows
    while (resultSet.next()) {
      String cols = String.format(ROW_CHECKER, resultSet.getInt(User.ID.Name()));
      for (Field field : columns) {
        cols += buildCol(resultSet, field);
      }
      rows += String.format(ROW, cols);
    }
    // Add rows into table
    return String.format(TABLE, rows);
  }

  public static String buildCol(ResultSet resultSet, Field field) throws SQLException {
    String val = "";
    String colName = field.Name();
    if(field.isInt()) {
        return generateColHtml(resultSet.getInt(colName), NUMBER_TD_STYLE);
    } else if(field.isDouble()) {
        return generateColHtml((field.isMoney() ? "$" : "") + resultSet.getDouble(colName), NUMBER_TD_STYLE);
    }
    return generateColHtml(resultSet.getString(colName), STR_TD_STYLE);
  }
  
  private static String generateColHtml(String val, String style) {
      return String.format(COL, style, val);
  }
  
  private static String generateColHtml(int val, String style) {
      return String.format(COL, style, formatter.format(val));
  }
  
  private static String generateColHtml(double val, String style) {
      return String.format(COL, style, formatter.format(val));
  }
}
