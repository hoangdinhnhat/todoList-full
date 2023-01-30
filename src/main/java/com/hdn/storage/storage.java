/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hdn.storage;

import com.hdn.model.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Lenovo
 */
@WebServlet(urlPatterns = {"/storage"})
@MultipartConfig
public class storage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");  
        String id = req.getParameter("id");
        if (id != null) {
            String filepath = "D:\\Different(D)\\Code\\Java\\JavaWeb\\ToDoList\\storage\\";
            File f = new File(filepath);
            boolean check = false;
            for (String string : f.list()) {
                if (string.equals(id + ".avatar")) {
                    check = true;
                }
            }
            if (!check) {
                InputStream input = new FileInputStream(filepath + "forAll.jpg");
                OutputStream output = resp.getOutputStream();
                int i;
                while ((i = input.read()) != -1) {
                    output.write(i);
                }
            } else {
                InputStream input = new FileInputStream(filepath + id + ".avatar");
                OutputStream output = resp.getOutputStream();
                int i;
                while ((i = input.read()) != -1) {
                    output.write(i);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Part filepart = req.getPart("imgFile");
        String exten = filepart.getContentType();
        String[] extens = exten.split("/");
        if (!extens[1].equals("jpeg") && !extens[1].equals("png")) {
            return;
        }
        InputStream input = filepart.getInputStream();
        OutputStream output = new FileOutputStream("D:\\Different(D)\\Code\\Java\\JavaWeb\\ToDoList\\storage\\" + user.getUsername() + ".avatar");
        OutputStream output2 = new FileOutputStream("D:\\Different(D)\\Code\\Java\\JavaWeb\\ToDoList\\storage\\pending.avatar");
        int i;
        while ((i = input.read()) != -1) {
            output.write(i);
            output2.write(i);
        }
        resp.setContentType("text/html");
        resp.getWriter().println("<script>window.close()</script>");
    }

}
