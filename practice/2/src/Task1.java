/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import model.Student;
import model.Answer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author 503
 */
public class Task1 {
    public static void main(String[] args) {
        try{
            Socket mySocket = new Socket("10.170.46.188", 11310);
            
            DataOutputStream dos = new DataOutputStream(mySocket.getOutputStream());
            DataInputStream dis = new DataInputStream(mySocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            
            Student student = new Student("B19DCCN333", "Tran Quang Hung", "10.170.46.129", 2);
            
            // Client send maSV
            dos.writeUTF(student.getMaSV());
            // Client receive obj Student
            Student studentOnServer = (Student) 
               ois.readObject();
            System.out.println(studentOnServer.getMaSV());
            // Client send Student
            oos.writeObject(student);
            // Client send hovaten
            dos.writeUTF(student.getHovaten());
            // Client send group
            dos.writeInt(student.getGroup());
            // Client send Answer
            oos.writeObject(new Answer(student));
            
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
