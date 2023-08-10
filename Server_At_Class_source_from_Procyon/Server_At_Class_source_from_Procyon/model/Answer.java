// 
// Decompiled by Procyon v0.5.36
// 

package model;

import java.io.Serializable;

public class Answer implements Serializable
{
    static final long serialVersionUID = 23L;
    Student student;
    boolean alreadyRegistration;
    
    public boolean isAlreadyRegistration() {
        return this.alreadyRegistration;
    }
    
    public void setAlreadyRegistration(final boolean alreadyRegistration) {
        this.alreadyRegistration = alreadyRegistration;
    }
    
    public Answer() {
        this.alreadyRegistration = false;
    }
    
    public Answer(final Student student) {
        this.alreadyRegistration = false;
        this.student = student;
    }
    
    public Student getStudent() {
        return this.student;
    }
    
    public void setStudent(final Student student) {
        this.student = student;
    }
    
    public boolean isMyAnswer(final String maSV) {
        if (this.student == null || this.student.getMaSV() == null) {
            System.err.println("Chua co sinh vien nay !");
            return false;
        }
        return this.student.getMaSV().equalsIgnoreCase(maSV);
    }
}
