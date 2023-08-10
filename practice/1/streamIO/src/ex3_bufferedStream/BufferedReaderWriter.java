package ex3_bufferedStream;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedReaderWriter {
    public static void main(String[] args) throws IOException {
        String a0, a1, a2;

        if (args.length != 3){
            a0 = "src/ex3_bufferedStream/in.txt";
            a1 = "src/ex3_bufferedStream/out.txt";
            a2 = "3";
        } else{
            a0 = args[0];
            a1 = args[1];
            a2 = args[2];
        }
        SimpleEncryption se = new SimpleEncryption();
        se.encrypt(a0, a1, Integer.parseInt(a2));
        se.viewFileContent(a1);
    }
}