// 
// Decompiled by Procyon v0.5.36
// 

package rmiserver_class;

import java.net.UnknownHostException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import control.IRegistration;
import control.Registration;
import view.ClientList;

public class RMIServer_Class
{
    public static void main(final String[] args) throws RemoteException, MalformedURLException, UnknownHostException {
        final ClientList view = new ClientList();
        final IRegistration reg = new Registration(view);
    }
}
