package com.assignment.one;

import java.sql.*;

public class SQLOperations {
    Connection con = null;

    private void openConnection() throws SQLException {
        con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/", "root", "");
    }

    public void createDatabase() throws SQLException {
        openConnection();
        Statement stmt = con.createStatement();
        stmt.executeUpdate(Constents.CREATE_DATABASE + " " + Constents.DATABASE_NAME);

        System.out.println("Database created successfully......");
        closeConnection();
    }

    public void createTable(String[] columns, String fileName) throws SQLException {
        openConnection();
        Statement stmt = con.createStatement();

        //creating the create table query here
        String query = Constents.CREATE_TABLE + " " + Constents.DATABASE_NAME + "." + fileName + " (";
        for (int i = 0; i < columns.length; i++) {
            query = query.concat(" " + columns[i] + " VARCHAR(20),");
        }
        query = query.concat("id int(5) AUTO_INCREMENT PRIMARY KEY);");

        System.out.println("Executing this Query: " + query);
        stmt.executeUpdate(query);
        System.out.println("Created Table Successfully......");

        closeConnection();
    }

    public boolean insertData(String[] values, String tableName, String colums) throws SQLException {
        openConnection();
        Statement stmt = con.createStatement();

        //creating the create table query here
        String query = Constents.INSERT_VALUES + tableName + "(" + colums + ")" + "VALUES (";
        for (int i = 0; i < values.length; i++) {
            query = query.concat("'" + values[i] + "'");
            if (i != values.length - 1) {
                query = query.concat(",");
            }
        }
        query = query.concat(");");
        stmt.executeUpdate(query);

        closeConnection();
        return true;
    }

    public void getData(String query) throws SQLException {
        openConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next())
            System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
        closeConnection();

    }

    private void closeConnection() throws SQLException {
        con.close();
    }

}
