package External;


import java.beans.PropertyChangeListener;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

public interface MessageClient extends Remote {
    void sendMsg(String str) throws RemoteException;
    String update(String str, MessageClient senderClient) throws RemoteException;
    void updateClientList(ArrayList<MessageClient> list) throws RemoteException;

    void setNickname(String nickname) throws RemoteException;
    String getNickname() throws RemoteException;

    void addPropertyChangeListerner(String msg, PropertyChangeListener listener) throws RemoteException;
}
