// 
// Decompiled by Procyon v0.5.36
// 

package control;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.net.UnknownHostException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.Remote;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import view.ClientList;
import model.Answer;
import model.Student;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.rmi.server.UnicastRemoteObject;

public class Registration extends UnicastRemoteObject implements IRegistration, ActionListener
{
    ArrayList<Student> studentLists;
    ArrayList<Answer> answerLists;
    ClientList view;
    
    public Registration(final ClientList view) throws RemoteException, MalformedURLException, UnknownHostException {
        this.studentLists = new ArrayList<Student>();
        this.answerLists = new ArrayList<Answer>();
        final Registry reg = LocateRegistry.createRegistry(1099);
        System.out.println("IP " + InetAddress.getLocalHost().getHostAddress());
        reg.rebind("Server", this);
        (this.view = view).setVisible(true);
    }
    
    @Override
    public synchronized Student register(final Student s) throws RemoteException {
        final Iterator<Student> it = this.studentLists.iterator();
        if (s.getMaSV() == null) {
            return null;
        }
        while (it.hasNext()) {
            final Student next = it.next();
            if (s.getMaSV().equalsIgnoreCase(next.getMaSV()) && s.getMaSV() != null) {
                return next;
            }
        }
        s.setIsAlreadyRegistration(true);
        if (s.getMaSV() != null) {
            this.studentLists.add(s);
            this.view.addNewRow(s);
        }
        return s;
    }
    
    @Override
    public synchronized Answer answer(final Answer answer) throws RemoteException {
        final Iterator<Student> it_s = this.studentLists.iterator();
        if (it_s.hasNext()) {
            final Student next = it_s.next();
            if (next.getMaSV().equalsIgnoreCase(answer.getStudent().getMaSV())) {
                answer.setIsAlreadyRegistration(true);
            }
        }
        final Iterator<Answer> it = this.answerLists.iterator();
        int index = 0;
        while (it.hasNext()) {
            final Answer next2 = it.next();
            if (next2.getStudent().getMaSV().equalsIgnoreCase(answer.getStudent().getMaSV())) {
                answer.setIsAlreadyRegistration(true);
                this.answerLists.set(index, answer);
                this.view.updateAnswerView(answer);
                return answer;
            }
            ++index;
        }
        System.out.println("Get answer from " + answer.getStudent().getMaSV() + " IP " + answer.getStudent().getIP());
        this.view.updateAnswerView(answer);
        this.answerLists.add(answer);
        return answer;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
