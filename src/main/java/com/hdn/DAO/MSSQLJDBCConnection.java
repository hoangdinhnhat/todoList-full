/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hdn.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class MSSQLJDBCConnection {
    public static Connection getJDBCConnection(String databaseName)
    {
        final String user = "sa";
        final String password = "12345";
        final String url = String.format("jdbc:sqlserver://localhost:1433;databaseName=%s;user=%s;password=%s", databaseName, user, password);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MSSQLJDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MSSQLJDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
