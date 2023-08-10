package rmi.exercise;


import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class Main {
    static String studentAddress = "rmi://10.20.103.1:2309/student";
    static String teacherAddress = "rmi://10.20.86.128:1099/teacher";
    
    public static String getDeclare(){
        try{
            Registry reg = LocateRegistry.getRegistry("10.20.86.128", 1099);
            ITeacher remoteTeacher = (ITeacher) reg.lookup(teacherAddress);
            return remoteTeacher.declare("10.20.103.1", 2309, studentAddress);
        } catch (RemoteException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static void startMyServer(){
//        IStudent s = new Student("B19DCCN333", "Trần Quang Hưng");
        IStudent s = new Student();
        ((Student)s).setMaSV("B19DCCN333");
        ((Student)s).setHovaten("Trần Quang Hưng");
        try {
            LocateRegistry.createRegistry(2309);
            Naming.rebind(studentAddress, s);
        } catch (RemoteException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(getDeclare());
        System.out.println("done");
    }
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        startMyServer();
    }
    
}
