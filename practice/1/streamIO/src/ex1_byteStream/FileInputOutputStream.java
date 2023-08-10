package ex1_byteStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputOutputStream {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("src/ex1_byteStream/in.txt");
        File outputFile = new File("src/ex1_byteStream/out.txt");

        FileInputStream fis = new FileInputStream(inputFile);
        FileOutputStream fos = new FileOutputStream(outputFile);

        int asciiCode;
        while ((asciiCode = fis.read()) != -1){
            System.out.print((char)asciiCode);
            fos.write(asciiCode);
        }
        System.out.println("Done");
        fis.close();
        fos.close();
    }
}