/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hdn.controllers;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.hdn.model.User;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(urlPatterns = {"/loginsWithGoogle"})
public class logInWithGoogle extends HttpServlet{
    
    public String generatePassword()
    {
        String model = "!@#$%^&qwertyuiopasdfghjklzxcvbnm123456789";
        String output = "";
        for (int i = 0; i < 8; i++) {
            int index = getRandomNumber(0, model.length());
            output += model.charAt(index);
        }
        return output;
    }
    
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String credential = req.getParameter("credential");
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList("96615940146-a6npdnvt227aiaou542u02q3q38v788t.apps.googleusercontent.com"))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

// (Receive idTokenString by HTTPS POST)
        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(credential);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(logInWithGoogle.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String tmps[] = email.split("[@]");
            String username = "google@" + tmps[0] + "_" + getRandomNumber(100000, 999999);
            String password = generatePassword();
            
            User user = new User(username, email, password);
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("/ToDoList-Maven");
            System.out.println(String.format("%s %s %s", user.getUsername(), user.getEmail(), user.getPassword()));
        } else {
            System.out.println("Invalid ID token.");
        }
    }
}
