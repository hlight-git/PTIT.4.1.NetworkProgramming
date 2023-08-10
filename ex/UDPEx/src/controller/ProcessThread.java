package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Student;

/**
 *
 * @author ADMIN
 */
public class ProcessThread extends Thread{
    Controller controller;
    DatagramPacket dataPacket;
    String name;

    public ProcessThread(Controller controller, DatagramPacket dataPacket) {
        this.name = "ST" + dataPacket.getPort();
        this.dataPacket = dataPacket;
        this.controller = controller;
        controller.serverLog("SERVER CREATED THREAD " + this.name + " FOR CLIENT AT PORT " + dataPacket.getPort());
    }

    @Override
    public void run() {
        try {
            Student stu = (Student) controller.deserialize(dataPacket.getData());
            Answer ans = new Answer(stu);
            byte[] ansBytes = controller.serialize(ans);
            DatagramPacket response = new DatagramPacket(ansBytes, ansBytes.length, dataPacket.getAddress(), dataPacket.getPort());
            try {
                controller.getServer().getSocket().send(response);
            } catch (IOException ex) {
                Logger.getLogger(ProcessThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            controller.serverLog(this.name + " SENDBACK TO CLIENT: " + ans);
        } catch (IOException ex) {
            Logger.getLogger(ProcessThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProcessThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
