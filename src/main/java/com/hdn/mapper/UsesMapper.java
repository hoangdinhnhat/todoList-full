/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hdn.mapper;

import com.hdn.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class UsesMapper implements RowMapper<User>{

    @Override
    public List<User> map(ResultSet resultSet) {
        List<User> users = new ArrayList<>();
        try {
            while(resultSet.next())
            {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                users.add(new User(username, email, password));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsesMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
}
