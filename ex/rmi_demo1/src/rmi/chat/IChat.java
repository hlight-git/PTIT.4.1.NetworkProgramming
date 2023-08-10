package rmi.chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChat extends Remote {
    String showText(String text) throws RemoteException;
}
