package controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Client;
import model.Student;
import view.UDPApp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADMIN
 */
public class Controller {

    Server server;
    UDPApp view;

    public Controller(UDPApp view) {
        try {
            CONFIG.init();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        server = new Server(this);
        server.start();
        this.view = view;
    }

    public Server getServer() {
        return server;
    }

    public void createSample() {
        try {
            view.getMaSVF().setText("B19DCCN333");
            view.getHovatenF().setText("Tran Quang Hung");
            view.getIpF().setText(InetAddress.getLocalHost().getHostAddress());
            view.getGroupF().setText("2");
        } catch (UnknownHostException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void serverLog(String log) {
        view.getServerArea().append(log + "\n");
    }

    public void clientsLog(String log) {
        view.getClientArea().append(log + "\n");
    }

    public void clientSend(Client client, Student stu) {
        try {
            byte[] stuBytes = serialize(stu);
            DatagramPacket sendPacket = new DatagramPacket(
                    stuBytes,
                    stuBytes.length,
                    InetAddress.getByName(CONFIG.SERVER_ADDRESS_NAME),
                    CONFIG.SERVER_PORT
            );

            client.getSocket().send(sendPacket);
            clientsLog("CLIENT PORT " + client.getPort() + " SEND: " + stu);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ClientListen clientListen = new ClientListen(this, client);
        clientListen.start();
    }

    public void serverProcess(DatagramPacket receivePacket) {
        try {
            serverLog("SERVER RECEIVE: " + (Student) deserialize(receivePacket.getData()));
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        new ProcessThread(this, receivePacket).start();
    }

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }
    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
    
    public void clientsSendData(){
        Student stu = new Student(view.getMaSVF().getText(), view.getHovatenF().getText(), view.getIpF().getText(), Integer.parseInt(view.getGroupF().getText()));
        for (int i=0; i<Integer.parseInt(view.getClients().getValue().toString()); i++){
            Client client = new Client();
            clientSend(client, stu);
        }
    }
}