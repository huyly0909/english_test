/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.op;

import core.util.Strings;
import core.util.field.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author tinluu
 */
public class db {

    final private static String URL = "jdbc:mysql://localhost:3306/english_test";
    final private static String USER = "root";
    final private static String PASSWORD = "";
    private Connection connection = null;
    private Statement statement = null;
    private String clazz;
    private String columns;
    private String conditions;
    private String values;
    private String orderBy;
    private String limit;
    
    public db() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        reset();
        connectDatabase();
    }
    
    private void connectDatabase() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            
        } catch (SQLException errorMessage) {
            System.out.println("Can not connect with database " + errorMessage);
        }
    }
    
    public void close() throws SQLException {
        connection.close();
    }
    
    public void reset() {
        columns = "*";
        conditions = "1";
        limit = "";
        orderBy = "id";
        values = "";
    }
    
    public db find(String clazz) throws SQLException {
        this.clazz = "`" + clazz.toLowerCase() + "`";
//        columns = new ArrayList<String>();
        return this;
    }
    
    public db where(String clazz) throws SQLException {
        this.clazz = "`" + clazz.toLowerCase() + "`";
        return this;
    }
    
    public db addFields(String... columns) {
        this.columns = String.join(", ", columns);
        return this;
    }
    
    public db addColumns(String... columns) {
        this.columns = "`" + String.join("`, `", columns) + "`";
        return this;
    }
    
    public db addColumns(Field... fields) {
        columns = "`";
        for(Field field : fields) {
            columns += field.Name() + "`, `";
        }
        columns = columns.substring(0, columns.length() - 3);
        return this;
    }
    
    public db addValues(String... values) {
        this.values = "'" + String.join("', '", values) + "'";
        return this;
    }
    
    public db addValues(List<String> values) {
        this.values = "'" + String.join("', '", values) + "'";
        return this;
    }
    
//    public db addIntValues(int... values) {
//        String str = String.join(", ", Arrays.toString(values));
//        intValues = str.substring(1, str.length() - 1);
//        return this;
//    }

    public db addConditions(String... conditions) {
        this.conditions = String.join(" && ", conditions);
        return this;
    }

    public db addInCondition(String col, String ids) {
        this.conditions = "`" + col + "` in (" + ids + ")";
        return this;
    }
    
    
    
    public db getLast() {
        OrderBy("id", true);
        Limit(1);
        return this;
    }
    
    public db OrderBy(String column) {
        orderBy = column;
        return this;
    }
    
    public db OrderBy(String column, boolean isDesc) {
        orderBy = "`" + column + "`" + (isDesc ? " desc" : "");
        return this;
    }
    
    public db Limit(int number) {
        limit = "limit " + number;
        return this;
    }

    public ResultSet execute() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        final String query = String.format("select %s from %s where %s order by %s %s", columns, clazz, conditions, orderBy, limit);
        return statement.executeQuery(query);
    }

    public int update() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        final String query = String.format("insert into %s (%s) values (%s)", clazz, columns , values);
        return statement.executeUpdate(query);
    }
    
    // if false: return -1
    // else return id
    public int updateAndGetId() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        update();
        // To get the newest id: select `id` from ... order by desc limit 1
        columns = "id";
        ResultSet resultSet = getLast().execute();
        while(resultSet.next()) {
            return resultSet.getInt("id");
        }
        return -1;
    }
    
    public int delete() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        final String query = String.format("delete from %s where %s", clazz, conditions);
        return statement.executeUpdate(query);
    }
}
