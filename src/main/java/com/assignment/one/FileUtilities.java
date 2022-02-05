package com.assignment.one;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class FileUtilities {

    SQLOperations sqlOperations = new SQLOperations();
    String fileActualName =null;
    String[] tableColums= null;
    String columval=null;

    public void readFileAndIntertDataToDB(String path) throws IOException {
        FileReader fr = null;
        try {
             fr = new FileReader(path);
            // read from FileReader till the end of file
            try (BufferedReader br = new BufferedReader(fr)) {
                String line;
                int lineNumber=0;
                while ((line = br.readLine()) != null) {
                    // process the line.
                    if (lineNumber == 0) {
                        columval = line;
                        tableColums = line.split(",");
                        // create object of Path
                        Path filePath = Paths.get(path);
                        // call getFileName() and get FileName path object
                        Path fileName = filePath.getFileName();
                        // get FileName
                        fileActualName = fileName.toString().split("\\.")[0];
                        System.out.println("Colum length = "+tableColums.length + " File name: " + fileActualName);
                        sqlOperations.createDatabase();
                        sqlOperations.createTable(tableColums, fileActualName);
                    }
                    else {
                        String[] values = line.split(",");
                        sqlOperations.insertData(values,fileActualName,columval);
                        System.out.println(line);
                        System.out.println("Inserted Values into Table Successfully......");
                    }
                    lineNumber++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File path is incorrect, cannot find the file");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fr != null)
            fr.close();
        }
    }
}
