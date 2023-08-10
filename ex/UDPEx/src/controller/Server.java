package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import controller.CONFIG;
import controller.CONFIG;
import controller.ProcessThread;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author ADMIN
 */
public class Server extends Thread{
    Controller controller;
    private DatagramSocket socket;

    public DatagramSocket getSocket() {
        return socket;
    }
    
    
    public Server(Controller controller){
        this.controller = controller;
        try {
            socket = new DatagramSocket(CONFIG.SERVER_PORT);
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
    }
    
    public void run(){
        controller.serverLog("SERVER START.");
        while (true){
            try {
                byte[] receiveData = new byte[5000];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                controller.serverProcess(receivePacket);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
