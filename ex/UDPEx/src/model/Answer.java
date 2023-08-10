/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class Answer implements Serializable{
    public static final long serialVersionUID = 2L;
    Student student;

    public Answer(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Object Answer with " + student.toString();
    }
    
}
