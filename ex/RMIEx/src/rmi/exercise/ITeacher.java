/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.exercise;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ADMIN
 */
public interface ITeacher extends Remote{
    public String declare(String studentIP, int studentPort, String fullURL) throws RemoteException;
}
