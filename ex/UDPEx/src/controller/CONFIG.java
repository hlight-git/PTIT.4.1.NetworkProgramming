package controller;


import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public final class CONFIG {
    public static String SERVER_ADDRESS_NAME;
    public static int SERVER_PORT;
    public static int DEFAULT_CLIENT_PORT;
    public static int curentClientsNumber;
    
    
    public static void init() throws UnknownHostException{
        SERVER_ADDRESS_NAME = InetAddress.getLocalHost().getHostName();
        SERVER_PORT = 9999;
        DEFAULT_CLIENT_PORT = 9000;
        curentClientsNumber = 0;
    }
}
