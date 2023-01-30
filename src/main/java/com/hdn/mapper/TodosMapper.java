/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hdn.mapper;

import com.hdn.model.ToDos;
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
public class TodosMapper implements RowMapper<ToDos>{

    @Override
    public List<ToDos> map(ResultSet resultSet) {
        List<ToDos> users = new ArrayList<>();
        try {
            while(resultSet.next())
            {
                String title = resultSet.getString("title");
                boolean completed = resultSet.getByte("completed") == 0 ? false : true;
                users.add(new ToDos(title, completed));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsesMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
}
