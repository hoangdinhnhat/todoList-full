/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hdn.model;

/**
 *
 * @author Lenovo
 */
public class ToDos {
    private String title;
    private boolean completed;

    public ToDos(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
