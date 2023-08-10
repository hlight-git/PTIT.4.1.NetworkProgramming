package ex4_dataStream;

import java.io.*;

public class DataInputOutputStream {
    public static void main(String[] args) throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/ex4_dataStream/out.txt"));
        double[] prices = { 19.99, 9.99, 15.99, 3.99, 4.99 };
        int[] units = { 12, 8, 13, 29, 50 };
        String[] descs = { "Java T-shirt",
                "Java Mug",
                "Duke Juggling Dolls",
                "Java Pin",
                "Java Key Chain" };
        for (int i=0; i<prices.length; i++){
            dos.writeDouble(prices[i]);
            dos.writeChar('\t');
            dos.writeInt(units[i]);
            dos.writeChar('\t');
            dos.writeChars(descs[i]);
            dos.writeChar('\n');
        }
        dos.close();
        DataInputStream dis = new DataInputStream(new FileInputStream("src/ex4_dataStream/out.txt"));
        double price;
        int unit;
        StringBuffer desc;
        double total = 0.0;

        String lineSepString = System.getProperty("line.separator");
        char lineSep = lineSepString.charAt(lineSepString.length() - 1);

        try{
            while (true){
                price = dis.readDouble();
                dis.readChar();
                unit = dis.readInt();
                dis.readChar();
                desc = new StringBuffer(20);
                char chr;
                while ((chr = dis.readChar()) != lineSep){
                    desc.append(chr);
                }
                System.out.println("" + price + "|" + unit + "|" + desc);
                total += unit * price;
            }
        } catch (EOFException e){

        }
        System.out.println(total);
        dis.close();
    }
}
