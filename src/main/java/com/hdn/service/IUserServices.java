/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hdn.service;

import com.hdn.model.User;

/**
 *
 * @author Lenovo
 */
public interface IUserServices {
    User getUser(String username, String password);
    User getUser(String username);
    void insertUser(User user);
}
