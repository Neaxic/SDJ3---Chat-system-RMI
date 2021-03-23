
package Client.Model;

import External.MessageClient;
import External.MessageServer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIClient implements MessageClient {

    private MessageServer server;
    private String Nickname;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public RMIClient() throws RemoteException{
        UnicastRemoteObject.exportObject(this,0);
    }

    public void startClient() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", 2000);
        server = (MessageServer) registry.lookup("Server");
        server.registerClient(this);
    }

    @Override
    public void sendMsg(String str){
        try{
            if(str.equals("!exit")){
                server.removeClient(this);
            } else if (str.equals("!chatters")){
                propertyChangeSupport.firePropertyChange("Msg", null, "Currently: " + server.getNoClients() + " active clients!");
            } else {
                propertyChangeSupport.firePropertyChange("Msg", null, getNickname() + " > " + str);
                server.sendMsg(str, this);
            }
        } catch (RemoteException e){
            e.printStackTrace();
            throw new RuntimeException("Could not contact server");
        }
    }

    public String update(String msg, MessageClient senderClient) throws RemoteException {
        String result = senderClient.getNickname() +" > " +msg;
        propertyChangeSupport.firePropertyChange("Msg", null, result);
        return result;
    }

    @Override
    public void updateClientList(ArrayList<MessageClient> clientsList) throws RemoteException {
        propertyChangeSupport.firePropertyChange("NicksConnected", null, clientsList);
    }

    @Override
    public void setNickname(String nickname) throws RemoteException {
        this.Nickname = nickname;
        server.updateClientList();
    }

    public String getNickname() {
        return Nickname;
    }


    public void addPropertyChangeListerner(String name, PropertyChangeListener listener){
        if(name == null){
            propertyChangeSupport.addPropertyChangeListener(listener);
        } else {
            propertyChangeSupport.addPropertyChangeListener(name, listener);
        }
    }


}
