// 
// Decompiled by Procyon v0.5.36
// 

package model;

import java.io.Serializable;

public class Student implements Serializable
{
    static final long serialVersionUID = 1L;
    private String maSV;
    private String hovaten;
    private String IP;
    private int group;
    boolean isAlreadyRegistration;
    
    public boolean isIsAlreadyRegistration() {
        return this.isAlreadyRegistration;
    }
    
    public void setIsAlreadyRegistration(final boolean isAlreadyRegistration) {
        this.isAlreadyRegistration = isAlreadyRegistration;
    }
    
    public int getGroup() {
        return this.group;
    }
    
    public Student(final String maSV, final String hovaten, final String IP, final int group) {
        this.isAlreadyRegistration = false;
        this.maSV = maSV;
        this.hovaten = hovaten;
        this.IP = IP;
        this.group = group;
    }
    
    public String getMaSV() {
        return this.maSV;
    }
    
    public String getHovaten() {
        return this.hovaten;
    }
    
    public String getIP() {
        return this.IP;
    }
    
    public void setMaSV(final String maSV) {
        this.maSV = maSV;
    }
    
    public void setHovaten(final String hovaten) {
        this.hovaten = hovaten;
    }
    
    public void setIP(final String IP) {
        this.IP = IP;
    }
    
    public void setNhom(final int nhom) {
        this.group = nhom;
    }
}
