/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hdn.service;

import com.hdn.DAO.JDBCStatement;
import com.hdn.mapper.RowMapper;
import com.hdn.mapper.TodosMapper;
import com.hdn.mapper.UsesMapper;
import com.hdn.model.ToDos;
import com.hdn.model.User;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class UserServices implements IUserServices {

    @Override
    public User getUser(String username, String password) {
        JDBCStatement DAO = new JDBCStatement("ToDoList");
        String sql = String.format("SELECT * FROM Users WHERE username='%s' AND password='%s' ", username, password);
        ResultSet rs = DAO.executeQuery(sql);
        RowMapper<User> mapper = new UsesMapper();
        List<User> users = mapper.map(rs);
        return users.size() > 0 ? users.get(0) : null;
    }

    @Override
    public User getUser(String username) {
        JDBCStatement DAO = new JDBCStatement("ToDoList");
        String sql = String.format("SELECT * FROM Users WHERE username='%s'", username);
        ResultSet rs = DAO.executeQuery(sql);
        RowMapper<User> mapper = new UsesMapper();
        List<User> users = mapper.map(rs);
        return users.size() > 0 ? users.get(0) : null;
    }

    @Override
    public void insertUser(User user) {
        JDBCStatement DAO = new JDBCStatement("ToDoList");
        DAO.insertRecords("Users", "", String.format("'%s', '%s', '%s'", user.getUsername(), user.getEmail(), user.getPassword()));
    }
    
    public User getUserByEmail(String email)
    {
        JDBCStatement DAO = new JDBCStatement("ToDoList");
        String sql = String.format("SELECT * FROM Users WHERE email='%s'", email);
        ResultSet rs = DAO.executeQuery(sql);
        RowMapper<User> mapper = new UsesMapper();
        List<User> users = mapper.map(rs);
        return users.size() > 0 ? users.get(0) : null;
    }
    
    public void setNewPassword(String username, String password)
    {
        System.out.println(username + "," + password);
        JDBCStatement DAO = new JDBCStatement("ToDoList");
        String sql = String.format("UPDATE Users SET password='%s' WHERE username='%s'", password, username);
        DAO.executeStatementUpdate(sql);
    }

    public List<ToDos> getTodos(String username) {
        JDBCStatement DAO = new JDBCStatement("ToDoList");
        String sql = String.format("SELECT * FROM ToDos WHERE username='%s'", username);
        ResultSet rs = DAO.executeQuery(sql);
        RowMapper<ToDos> mapper = new TodosMapper();
        List<ToDos> todos = mapper.map(rs);
        return todos;
    }

    public void setToDos(List<ToDos> todos, User user) {
        JDBCStatement DAO = new JDBCStatement("ToDoList");
        String sql = String.format("DELETE FROM ToDos WHERE username='%s'", user.getUsername());
        DAO.executeStatementUpdate(sql);
        for (ToDos todo : todos) {
            DAO.insertRecords("ToDos", "", String.format("'%s', '%s', '%s'", user.getUsername(), todo.getTitle(), todo.isCompleted() == true ? 1 : 0));
        }
    }
}
