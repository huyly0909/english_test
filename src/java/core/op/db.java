/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.op;

import core.util.Strings;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

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
    private String strValues;
    private String intValues;
    
    public db() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        columns = "*";
        conditions = "1";
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
    
    public void closeDatabaseConnection() throws SQLException {
        connection.close();
    }
    
    public db find(String clazz) throws SQLException {
        this.clazz = clazz.toLowerCase();
//        columns = new ArrayList<String>();
        return this;
    }
    
    public db where(String clazz) throws SQLException {
        this.clazz = clazz.toLowerCase();
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
    
    public db addStrValues(String... values) {
        strValues = "'" + String.join("', '", values) + "'";
        return this;
    }
    
    public db addIntValues(int... values) {
        String str = String.join(", ", Arrays.toString(values));
        intValues = str.substring(1, str.length() - 1);
        return this;
    }

    public db addConditions(String... conditions) {
        this.conditions = String.join(" && ", conditions);
        return this;
    }

    public ResultSet execute() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        final String query = String.format("select %s from %s where %s", columns, clazz, conditions);
        return executeQuery(query);
    }
    
    /*
    Rule: Need to add all string colums before int columns
    */
    public void update() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String values = Strings.isNotEmpty(strValues) ? strValues : "";
        if (Strings.isNotEmpty(intValues)) {
            values += ", " + intValues;
        }
        final String query = String.format("insert into %s (%s) values (%s)", clazz, columns , values);
        statement.executeUpdate(query);
    }
    
    private ResultSet executeQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }
}
