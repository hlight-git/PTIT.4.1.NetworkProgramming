package ex4_dataStream;

import java.io.*;

public class CustomDataInputOutput {
    public static void main(String[] args) throws IOException {
        Adler32 inChecker = new Adler32();
        Adler32 outChecker = new Adler32();
        CheckedDataInput in = null;
        CheckedDataOutput out = null;
        try {
            in = new CheckedDataInput(
                    new DataInputStream(
                            new FileInputStream("src/ex4_dataStream/in.txt")),
                    inChecker);
            out = new CheckedDataOutput(
                    new DataOutputStream(
                            new FileOutputStream("src/ex4_dataStream/out.txt")),
                    outChecker);
        } catch (FileNotFoundException e) {
            System.err.println("CheckedIOTest: " + e);

            System.exit(-1);
        } catch (IOException e) {
            System.err.println("CheckedIOTest: " + e);
            System.exit(-1);
        }
        boolean EOF = false;
        while (!EOF) {
            try {
                int c = in.readByte();
                out.write(c);
            } catch (EOFException e) {
                EOF = true;
            }
        }
        System.out.println("Input stream check sum: " +
                in.getChecksum().getValue());
        System.out.println("Output stream check sum: " +
                out.getChecksum().getValue());
    }
}
