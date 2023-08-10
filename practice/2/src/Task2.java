/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import model.Student;
import model.Answer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author 503
 */
public class Task2 {
    public static void main(String[] args) {
        try{
            Socket mySocket = new Socket("10.170.46.188", 11300);
            
            DataOutputStream dos = new DataOutputStream(mySocket.getOutputStream());
            DataInputStream dis = new DataInputStream(mySocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            
            Student student = new Student("B19DCCN333", "Tran Quang Hung", "10.170.46.129", 2);
            
            // Client receive data
            String[] data = dis.readUTF().trim().split("\\;");
            for(String s : data){
                System.out.println(s);
            }
            /* Data:
                Client,Server,nhom-int
                Client,Server,Student-Object
                Client,Server,hovaten-String
                Client,Server,masv-String
                Server,Client,"OK"-String
                Server,Client,Answer-Object
            */
            
            // Client send data
            dos.writeInt(student.getGroup());
            oos.writeObject(student);
            dos.writeUTF(student.getHovaten());
            dos.writeUTF(student.getMaSV());
            
            String ok = dis.readUTF();
            Answer answer = (Answer) ois.readObject();
            
            dos.close();
            dis.close();
            oos.close();
            ois.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
