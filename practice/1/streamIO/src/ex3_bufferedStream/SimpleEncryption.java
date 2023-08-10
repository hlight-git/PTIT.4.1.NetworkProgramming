package ex3_bufferedStream;

import java.io.*;

public class SimpleEncryption {
    void encrypt(String source, String dest, int shiftSize){
        BufferedReader reader;
        BufferedWriter writer;

        try{
            reader = new BufferedReader(new FileReader(source));
            writer = new BufferedWriter(new FileWriter(dest));
            int data;
            String line;
            while ((line = reader.readLine()) != null){
                for (int i=0; i<line.length(); i++){
                    data = (int) line.charAt(i) + shiftSize;
                    writer.write(data);
                }
                writer.write((int)'\n');
            }
            reader.close();
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    void viewFileContent(String filename){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
