// 
// Decompiled by Procyon v0.5.36
// 

package server;

import java.io.IOException;
import control.UDPServer;
import view.ClientList;

public class FinalTest
{
    public static void main(final String[] args) throws ClassNotFoundException, IOException {
        final ClientList view = new ClientList();
        final UDPServer udpServer = new UDPServer(view);
        udpServer.start();
    }
}
