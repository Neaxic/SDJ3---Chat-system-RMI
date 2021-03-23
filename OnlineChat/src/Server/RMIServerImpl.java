package Server;

import External.MessageClient;
import External.MessageServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIServerImpl implements MessageServer{

    private ArrayList<MessageClient> clientsList;

    public RMIServerImpl() throws RemoteException {
        UnicastRemoteObject.exportObject(this,0);
        clientsList = new ArrayList<>();
    }

    @Override
    public String sendMsg(String str, MessageClient client) throws RemoteException {
        updateClients(str, client);
        return str;
    }

    @Override
    public void registerClient(MessageClient client) throws RemoteException {
        clientsList.add(client);
    }

    public void removeClient(MessageClient client) throws RemoteException {
        clientsList.remove(client);
    }

    public void updateClients(String result, MessageClient senderClient) throws RemoteException {
        for(MessageClient client : clientsList){
            if(client.equals(senderClient)) continue;
            try{
                client.update(result, senderClient);
            } catch (RemoteException e){
                e.printStackTrace();
            }
        }
    }

    public int getNoClients(){
        return clientsList.size();
    }

    @Override
    public void updateClientList() throws RemoteException {
        for(MessageClient client : clientsList){
            client.updateClientList(clientsList);
        }
    }


}
