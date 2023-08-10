/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.exercise;

import java.rmi.Remote;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author ADMIN
 */
public class Student extends UnicastRemoteObject implements IStudent{

//    public Student(String maSV, String hovaten) {
//        this.maSV = maSV;
//        this.hovaten = hovaten;
//    }
    private String maSV;
    private String hovaten;

    @Override
    public String maSV() {
        return maSV;
    }

    @Override
    public String hovaten() {
        return hovaten;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
    }
    
    
}
