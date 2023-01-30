/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hdn.controllers;

import com.hdn.model.User;
import com.hdn.service.UserServices;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
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
@WebServlet(urlPatterns = {"/login"})
public class loginController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserServices services = new UserServices();
        User user =  services.getUser(username, password);
        if(user != null)
        {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("/ToDoList-Maven");
        }else
        {
            resp.getWriter().println("<script>alert('WRONG')</script>");
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.include(req, resp);
        }
    }
    
}
