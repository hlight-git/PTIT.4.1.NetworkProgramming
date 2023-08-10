package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import controller.CONFIG;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author ADMIN
 */
public class Client {
    private DatagramSocket socket;
    private int port;

    public DatagramSocket getSocket() {
        return socket;
    }

    public int getPort() {
        return port;
    }

    public Client() {
        port = CONFIG.DEFAULT_CLIENT_PORT + CONFIG.curentClientsNumber++;;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
    }
}
