package ex2_characterStream;

import java.io.*;

public class FileReaderWriter {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("src/ex2_characterStream/in.txt");
        File outputFile = new File("src/ex2_characterStream/out.txt");

        FileReader fr = new FileReader(inputFile);
        FileWriter fw = new FileWriter(outputFile);

        int asciiCode;
        while ((asciiCode = fr.read()) != -1){
            System.out.print((char)asciiCode);
            fw.write(asciiCode);
        }
        System.out.println("Done");
        fr.close();
        fw.close();
    }
}