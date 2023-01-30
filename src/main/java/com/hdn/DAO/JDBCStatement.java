/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hdn.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class JDBCStatement {

    private final String databaseName;

    public JDBCStatement(String databaseName) {
        this.databaseName = databaseName;
    }

    public void createTable(String tableName, String... attributes) {
        Connection connection = MSSQLJDBCConnection.getJDBCConnection(databaseName);
        try {
            Statement statement = connection.createStatement();
            String attributeString = "";
            for (String string : attributes) {
                attributeString += string + ",";
            }
            String sql = String.format("CREATE TABLE %s (%s)", tableName, attributeString);
            int rs = statement.executeUpdate(sql);
            System.out.println("Status: " + (rs == 0 ? "SUCCESS" : "UNSUCCESS"));
        } catch (SQLException ex) {
            Logger.getLogger(JDBCStatement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void dropTable(String tableName) {
        Connection connection = MSSQLJDBCConnection.getJDBCConnection(databaseName);
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("DROP TABLE %s", tableName);
            int rs = statement.executeUpdate(sql);
            System.out.println("Status: " + (rs == 0 ? "SUCCESS" : "UNSUCCESS"));
        } catch (SQLException ex) {
            Logger.getLogger(JDBCStatement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet executeQuery(String sql) {
        Connection connection = MSSQLJDBCConnection.getJDBCConnection(databaseName);
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void executeStatementUpdate(String sql) {
        Connection connection = MSSQLJDBCConnection.getJDBCConnection(databaseName);
        try {
            Statement statement = connection.createStatement();
            int rs = statement.executeUpdate(sql);
            System.out.println("Row affected: " + rs);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertRecords(String tableName, String attributes, String values) {
        Connection connection = MSSQLJDBCConnection.getJDBCConnection(databaseName);
        try {
            Statement statement = connection.createStatement();
            attributes = attributes.equals("") ? attributes : "(" + attributes + ")";
            String sql = String.format("INSERT INTO %s %s VALUES (%s)", tableName, attributes, values);
            int rs = statement.executeUpdate(sql);
            System.out.println("Row affected: " + rs);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateRecords(String tableName, String setters, String conditions) {
        Connection connection = MSSQLJDBCConnection.getJDBCConnection(databaseName);
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("UPDATE %s SET %s WHERE %s", tableName, setters, conditions);
            int rs = statement.executeUpdate(sql);
            System.out.println("Row affected: " + rs);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRecords(String tableName, String conditions) {
        Connection connection = MSSQLJDBCConnection.getJDBCConnection(databaseName);
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("DELETE FROM %s WHERE %s", tableName, conditions);
            int rs = statement.executeUpdate(sql);
            System.out.println("Row affected: " + rs);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void transaction(String... sqls) {
        Connection connection = MSSQLJDBCConnection.getJDBCConnection(databaseName);
        try {
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);
            for (String sql : sqls) {
                statement.executeUpdate(sql);
            }
            connection.commit();
            System.out.println("SUCCESS");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void batch(String... sqls) {
        Connection connection = MSSQLJDBCConnection.getJDBCConnection(databaseName);
        try {
            Statement statement = connection.createStatement();
            for (String sql : sqls) {
                statement.addBatch(sql);
            }
            statement.executeBatch();
            System.out.println("SUCCESS");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet callableStatement(String procedureName, String arguments) {
        Connection connection = MSSQLJDBCConnection.getJDBCConnection(databaseName);
        try {
            String sql = String.format("{call %s(%s)}", procedureName, arguments);
            CallableStatement call = connection.prepareCall(sql);
            ResultSet resultSet = call.executeQuery();
            PreparedStatement pr = connection.prepareStatement("ss");
            return resultSet;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void printResultSet(ResultSet rs) {
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            System.out.print("| ");
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-20s | ", metaData.getColumnName(i));
            }
            while (rs.next()) {
                System.out.println("");
                System.out.print("| ");
                for (int i = 1; i <= columnCount; i++) {
                    System.out.printf("%-20s | ", rs.getString(metaData.getColumnName(i)));
                }
            }
            System.out.println("");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertFile(String tableName, String attributes, String values, String filepath) {
        
        File file = new File(filepath);
        Connection connection = MSSQLJDBCConnection.getJDBCConnection(databaseName);
        attributes = attributes.equals("")? attributes : attributes + ",";
        values = values.equals("")? values : values + ",";
        String sql = String.format("INSERT INTO %s (%s filepath, files) VALUES (%s ?, ?)", tableName, attributes , values);
        
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, filepath);
            preparedStatement.setBinaryStream(2, fileInputStream);
            
            preparedStatement.executeUpdate();
            System.out.println("Status: SUCCESS");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JDBCStatement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getFileFromDB(String tableName, String selector) {
        Connection connection = MSSQLJDBCConnection.getJDBCConnection(databaseName);
        String sql = "SELECT " + selector + " FROM " + tableName;
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                String filepath = rs.getString("filepath");
                Blob file = rs.getBlob("files");
                byte[] b = file.getBytes(1, (int) file.length());
                FileOutputStream fileOutputStream = new FileOutputStream(filepath);
                fileOutputStream.write(b);
                fileOutputStream.close();
            }
            System.out.println("Status: SUCCESS");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCStatement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JDBCStatement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JDBCStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        JDBCStatement test = new JDBCStatement("TestConnection"); 
    }
}
