// 
// Decompiled by Procyon v0.5.36
// 

package control;

import java.io.ObjectInput;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import model.Student;
import model.Answer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPClientThread extends Thread
{
    DatagramSocket clientSocket;
    UDPServer serverControl;
    DatagramPacket dp;
    Answer answer;
    Student student;
    
    public UDPClientThread(final DatagramSocket s, final DatagramPacket dp, final UDPServer serverControl) {
        this.answer = null;
        this.student = null;
        this.clientSocket = s;
        this.dp = dp;
        this.serverControl = serverControl;
        this.answer = new Answer();
    }
    
    @Override
    public void run() {
        try {
            final ByteArrayInputStream bis = new ByteArrayInputStream(this.dp.getData());
            ObjectInput in = null;
            in = new ObjectInputStream(bis);
            this.student = (Student)in.readObject();
            if (this.student == null || this.student.getMaSV().equalsIgnoreCase("")) {
                return;
            }
            this.answer = this.serverControl.getAnswer(this.student);
            if (this.answer == null) {
                this.answer = new Answer(this.student);
            }
            this.answer.setAlreadyRegistration(true);
            final String ip = this.dp.getAddress().toString();
            this.student.setIP(ip);
            this.serverControl.updateAnswerList(this.answer);
            this.serverControl.updateView(this.student);
        }
        catch (IOException ex) {
            Logger.getLogger(UDPClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex2) {
            Logger.getLogger(UDPClientThread.class.getName()).log(Level.SEVERE, null, ex2);
        }
    }
}
