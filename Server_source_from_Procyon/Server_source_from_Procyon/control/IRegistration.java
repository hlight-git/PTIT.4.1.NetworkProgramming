// 
// Decompiled by Procyon v0.5.36
// 

package control;

import model.Answer;
import java.rmi.RemoteException;
import model.Student;
import java.rmi.Remote;

public interface IRegistration extends Remote
{
    Student register(final Student p0) throws RemoteException;
    
    Answer answer(final Answer p0) throws RemoteException;
}
