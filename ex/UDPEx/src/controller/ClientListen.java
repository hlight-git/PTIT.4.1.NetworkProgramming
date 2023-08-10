package controller;


import model.Client;
import java.io.IOException;
import java.net.DatagramPacket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ADMIN
 */
public class ClientListen extends Thread{
    Controller controller;
    Client client;

    public ClientListen(Controller controller, Client client) {
        this.controller = controller;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            client.getSocket().receive(receivePacket);
            try {
                controller.clientsLog("CLIENT PORT " + client.getPort() + " RECEIVE: " + (Answer) controller.deserialize(receivePacket.getData()));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientListen.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }    
}
