// 
// Decompiled by Procyon v0.5.36
// 

package control;

import java.net.Socket;
import java.net.SocketException;
import java.net.InetAddress;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;
import view.ClientList;
import model.Answer;
import java.util.ArrayList;
import model.Student;
import java.net.ServerSocket;

public class TCPServer extends Thread
{
    ServerSocket myServer;
    Student input;
    ArrayList<Student> svList;
    ArrayList<Answer> answerList;
    ClientList view;
    
    public TCPServer(final ClientList view) {
        this.myServer = null;
        this.svList = new ArrayList<Student>();
        this.answerList = new ArrayList<Answer>();
        (this.view = view).setVisible(true);
    }
    
    public synchronized void addStudent(final Student s) {
        if (this.svList == null) {
            (this.svList = new ArrayList<Student>()).add(s);
        }
        else {
            final Iterator<Student> it = this.svList.iterator();
            boolean isNew = true;
            while (it.hasNext()) {
                final Student next = it.next();
                if (s.getMaSV() != null && next.getMaSV() != null && next.getMaSV().equalsIgnoreCase(s.getMaSV())) {
                    isNew = false;
                    break;
                }
            }
            if (isNew) {
                this.svList.add(s);
            }
        }
    }
    
    public Student getStudent(final String maSV) {
        for (final Student next : this.svList) {
            if (next.getMaSV().equalsIgnoreCase(maSV)) {
                return next;
            }
        }
        return null;
    }
    
    public Answer getAnswer(final Student student) {
        for (final Answer next : this.answerList) {
            if (next.getStudent().getMaSV().equalsIgnoreCase(student.getMaSV())) {
                return next;
            }
        }
        return null;
    }
    
    public synchronized void updateAnswerList(final Answer answer) {
        System.out.println("--- answer.isAlreadyRegistration() ---" + answer.isAlreadyRegistration());
        final Iterator<Answer> it = this.answerList.iterator();
        boolean isUpdate = false;
        while (it.hasNext()) {
            final Answer next = it.next();
            if (next.getStudent().getMaSV().equalsIgnoreCase(answer.getStudent().getMaSV())) {
                isUpdate = true;
                next.getStudent().setGroup(answer.getStudent().getGroup());
                next.getStudent().setHovaten(answer.getStudent().getHovaten());
            }
        }
        if (!isUpdate) {
            this.answerList.add(answer);
        }
    }
    
    public synchronized void updateView(final Student student) {
        for (final Answer next : this.answerList) {
            if (next.getStudent().getMaSV().equalsIgnoreCase(student.getMaSV())) {
                this.view.updateAnswerView(next);
                break;
            }
        }
    }
    
    @Override
    public void run() {
        try {
            super.run();
            this.openServer();
            this.listening();
        }
        catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void openServer() {
        try {
            System.out.println("Server chay tren IP " + InetAddress.getLocalHost());
            this.myServer = new ServerSocket(11310);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public void listening() throws IOException {
        while (true) {
            try {
                while (true) {
                    final Socket clientSocket = this.myServer.accept();
                    final TCPClientThread clientThread = new TCPClientThread(clientSocket, this);
                    clientThread.start();
                }
            }
            catch (SocketException so) {
                System.out.println(" ------- Loi client ------- ");
                so.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    public void close() {
        try {
            this.myServer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
