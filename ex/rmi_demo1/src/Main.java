import rmi.chat.IChat;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    private static String address = "rmi://10.20.149.76:1099/chat";
    private static String ipAddress = "10.20.149.76";
    private static String text = "Nguyen Hoang Tuan Anh - B19DCCN025";

    public static void main(String[] args) {

        try {
            IChat iChat = (IChat) Naming.lookup(Naming.list(address)[0]);
            iChat.showText(text);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}