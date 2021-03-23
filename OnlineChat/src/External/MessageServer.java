package External;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MessageServer extends Remote {
    String sendMsg(String str, MessageClient senderClient) throws RemoteException;
    void updateClients(String result, MessageClient senderClient) throws RemoteException;

    void registerClient(MessageClient client) throws RemoteException;
    void removeClient(MessageClient client) throws RemoteException;

    int getNoClients() throws RemoteException;
    void updateClientList() throws RemoteException;

}
