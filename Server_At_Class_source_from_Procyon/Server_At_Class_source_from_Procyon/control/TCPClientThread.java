// 
// Decompiled by Procyon v0.5.36
// 

package control;

import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import model.Student;
import model.Answer;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;

public class TCPClientThread extends Thread
{
    Socket clientSocket;
    TCPServer serverControl;
    DataInputStream is;
    DataOutputStream os;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    Answer answer;
    Student student;
    
    public TCPClientThread(final Socket s, final TCPServer serverControl) {
        this.answer = null;
        this.student = null;
        this.clientSocket = s;
        this.serverControl = serverControl;
        this.answer = new Answer();
    }
    
    private boolean initiateStudentAnswer() throws IOException, ClassNotFoundException {
        final String masv = this.is.readUTF();
        if (masv.equals(null) || masv.equalsIgnoreCase("")) {
            System.out.println("Ma SV null");
            return false;
        }
        (this.student = new Student()).setMaSV(masv);
        this.objectOutputStream.writeObject(this.student);
        this.student = (Student)this.objectInputStream.readObject();
        final String hovaten = this.is.readUTF();
        final int nhom = this.is.readInt();
        String ipClient = "";
        try {
            ipClient = this.clientSocket.getInetAddress().getHostAddress();
        }
        catch (Exception e) {
            System.out.println("Error while getting IP");
            e.printStackTrace();
        }
        if (!ipClient.equalsIgnoreCase("")) {
            this.student.setIP(ipClient);
        }
        this.answer = (Answer)this.objectInputStream.readObject();
        if (this.answer == null) {
            System.out.println("Nhan Answer loi");
            return false;
        }
        if (!this.answer.getStudent().getMaSV().equalsIgnoreCase(this.student.getMaSV()) || !this.answer.getStudent().getHovaten().equalsIgnoreCase(hovaten) || this.answer.getStudent().getGroup() != nhom) {
            System.out.println("Nhan Answer cac truong khong khop");
            return false;
        }
        System.out.println("NEW REGISTRATION");
        this.answer.setAlreadyRegistration(true);
        return true;
    }
    
    private boolean initiateStudentAnswerObject() throws IOException {
        this.student = new Student(this.is.readUTF(), this.is.readUTF(), this.clientSocket.getInetAddress().getHostAddress(), this.is.readInt());
        if (this.student == null) {
            return false;
        }
        if (this.student.getMaSV().trim().equalsIgnoreCase("")) {
            System.out.println("SV may " + this.student.getIP() + " Nhap khong dung ma sv");
            return false;
        }
        this.answer = this.serverControl.getAnswer(this.student);
        if (this.answer == null) {
            System.out.println("NEW REGISTRATION");
            (this.answer = new Answer(this.student)).setAlreadyRegistration(true);
        }
        else {
            this.answer.setStudent(this.student);
            this.answer.setAlreadyRegistration(true);
        }
        return true;
    }
    
    @Override
    public void run() {
        try {
            super.run();
            this.os = new DataOutputStream(this.clientSocket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(this.clientSocket.getInputStream());
            this.is = new DataInputStream(this.clientSocket.getInputStream());
            this.objectOutputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
            if (!this.initiateStudentAnswer()) {
                System.out.println("Client loi");
                return;
            }
            this.serverControl.updateAnswerList(this.answer);
            this.serverControl.updateView(this.student);
        }
        catch (SocketException so) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            so.printStackTrace();
            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                }
                catch (IOException ex) {
                    Logger.getLogger(TCPClientThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        catch (IOException e) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            e.printStackTrace();
            if (this.is != null) {
                try {
                    this.is.close();
                }
                catch (IOException ex) {
                    Logger.getLogger(TCPClientThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                }
                catch (IOException ex) {
                    Logger.getLogger(TCPClientThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        catch (Exception e2) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            e2.printStackTrace();
            if (this.is != null) {
                try {
                    this.is.close();
                }
                catch (IOException ex) {
                    Logger.getLogger(TCPClientThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                }
                catch (IOException ex) {
                    Logger.getLogger(TCPClientThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        finally {
            System.out.println(" finally Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            if (this.is != null) {
                try {
                    this.is.close();
                }
                catch (IOException ex2) {
                    Logger.getLogger(TCPClientThread.class.getName()).log(Level.SEVERE, null, ex2);
                }
            }
            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                }
                catch (IOException ex2) {
                    Logger.getLogger(TCPClientThread.class.getName()).log(Level.SEVERE, null, ex2);
                }
            }
        }
    }
}
