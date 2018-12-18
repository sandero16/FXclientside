package sample;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Counter extends Remote {
    int[]getAndereGok(String sessionToken) throws  RemoteException;
    int[]getGameGok(int i, int viewerId) throws RemoteException;
    void testConnectie() throws  RemoteException;
    boolean vindtTegenspeler(String sessionToken) throws RemoteException;
    void addToGame(String sessionToken, int aantalspelers, boolean host) throws RemoteException;
    boolean setGame(String sessionToken) throws RemoteException;
    int getZet(int i, String sessionToken) throws RemoteException;
    void changeBeurt(String sessionToken) throws RemoteException;
    boolean checkBeurt(String sessionToken) throws RemoteException;
    int getResult(String sessionToken) throws  RemoteException;
    int getScore(String sessionToken) throws RemoteException;
    int getGame(int i) throws RemoteException;
    ArrayList<ArrayList<Integer>>getReedsGezet(int i) throws RemoteException;
    boolean getEnd(int game) throws  RemoteException;
    int getViewerId(int game) throws  RemoteException;
    void geefNotify(String sessionToken) throws RemoteException;
    int[]getGameInhaalGok(int i, int viewerId) throws RemoteException;
    int getPunten(String sessionToken) throws RemoteException;

}