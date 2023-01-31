/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hdn.controllers;

import com.hdn.model.User;
import com.hdn.service.UserServices;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lenovo
 */
@WebServlet(urlPatterns = {"/edited"})
public class editController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String body = "";
        int i;
        while((i = reader.read()) != -1)
        {
            body += (char)i;
        }
        String tmps [] = body.split(",");
        String username = tmps[0];
        String newPassword = tmps[1];
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        user.setPassword(newPassword);
        UserServices services = new UserServices();
        services.setNewPassword(username, newPassword);
    }
    
}
