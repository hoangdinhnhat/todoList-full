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

/**
 *
 * @author Lenovo
 */
@WebServlet(urlPatterns = {"/confirm-email"})
public class confirmEmailController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String confirmNum = "" + (Integer) req.getSession().getAttribute("confirmNumber");
        String cfNum = req.getParameter("confirmNumber");
        if (confirmNum.equals(cfNum)) {
            User newUser = (User) req.getSession().getAttribute("newUser");
            UserServices services = new UserServices();
            services.insertUser(newUser);
            resp.getWriter().println("<script>alert('SUCCESSFULLY')</script>");
            req.getSession().setAttribute("confirmNumber", null);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/");
            dispatcher.include(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("confirmEmail/confirmEmail.html");
            dispatcher.forward(req, resp);
        }
    }

}
