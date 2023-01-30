/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hdn.controllers;

import com.hdn.model.User;
import com.hdn.sendingemail.GMailer;
import com.hdn.service.UserServices;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lenovo
 */
@WebServlet(urlPatterns = {"/signup"})
public class signUpController extends HttpServlet {

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confPass = req.getParameter("conf-password");

        UserServices services = new UserServices();
        boolean isValid = (services.getUserByEmail(email) == null ? true : false) && (services.getUser(username) == null ? true : false);
        if(!isValid || !password.equals(confPass))
        {
            resp.sendRedirect("/ToDoList-Maven");
            return;
        }
        User newUser = new User(username, email, password);
        req.getSession().setAttribute("newUser", newUser);
        Random rd = new Random();
        int confirmNumber = getRandomNumber(100000, 999999);
        try {
            GMailer gmail = new GMailer();
            gmail.sendMail("CONFIRM YOUR ACCOUNT!", "Welcome to our application!!!\n"
                    + "Please confirm your account!\n"
                    + "Your confirm number is: " + confirmNumber, email);
        } catch (Exception ex) {
            Logger.getLogger(signUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.getSession().setAttribute("confirmNumber", confirmNumber);
        RequestDispatcher dispatcher = req.getRequestDispatcher("confirmEmail/confirmEmail.html");
        dispatcher.forward(req, resp);
    }

}
