package com.assignment.one;

import java.io.IOException;
import java.sql.*;

public class DbWrapper {

    public static void main(String [] a) throws SQLException {
        if(a.length<2){
            System.out.println("Parameters given are not correct");
            return;
        }
        if (a.length == 2) {
            switch (a[0]) {
                case "init":
                    init();
                    break;
                case "query":
                    query(a[1]);
                    break;
                default:
                    System.out.println("incorect operation given....");
            }
        }
    }

    public static void init() {
        try {
            String path="C:\\Users\\Savithru\\IdeaProjects\\Test\\test.csv";
            FileUtilities fileUtilities = new FileUtilities();
            fileUtilities.readFileAndIntertDataToDB(path);
        } catch (IOException e) {
            System.out.println("File could not be closed....");
            e.printStackTrace();
        }
        System.out.println("execution complete");
    }

    public static void query(String query) {
        SQLOperations sqlOperations=new SQLOperations();
        try {
            sqlOperations.getData(query);
        } catch (SQLException e) {
            System.out.println("Given query is not in correct format");
            e.printStackTrace();
        }

    }
}
