/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author 503
 */
public class Answer implements Serializable{
    // Task 1:
    // public static final long serialVersionUID = 22L;
    // Task 2:
    public static final long serialVersionUID = 32L;
    private Student student;

    public Answer(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    
}
