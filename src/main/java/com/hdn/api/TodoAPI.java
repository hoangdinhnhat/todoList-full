/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hdn.api;

import com.google.gson.Gson;
import com.hdn.model.ListTodos;
import com.hdn.model.ToDos;
import com.hdn.model.User;
import com.hdn.service.UserServices;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lenovo
 */
@WebServlet(urlPatterns = {"/todos"})
public class TodoAPI extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/ToDoList-Maven");
            return;
        }
        resp.setContentType("application/json");
        Gson gson = new Gson();
        UserServices services = new UserServices();
        resp.getWriter().println(gson.toJson(services.getTodos(user.getUsername())));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        BufferedReader reader = req.getReader();
        String output = "";
        int i;
        while ((i = reader.read()) != -1) {
            output += (char) i;
        }
        Gson gson = new Gson();
        ArrayList<ToDos> todos = gson.fromJson(output, ListTodos.class);
        UserServices services = new UserServices();
        services.setToDos(todos, (User)req.getSession().getAttribute("user"));
    }
}
