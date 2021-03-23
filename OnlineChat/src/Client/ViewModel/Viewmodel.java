package Client.ViewModel;

import External.MessageClient;
import External.MessageServer;
import Test.ClientSocketHandler;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Viewmodel {

    StringProperty textField;
    StringProperty ReciveText;
    StringProperty chatters;
    StringProperty connections;
    private MessageClient client;
    private int check = 0;

    public Viewmodel(){
        textField = new SimpleStringProperty();
        ReciveText = new SimpleStringProperty();
        ReciveText.setValue("SERVER: You can use: !chatters to se chatters or !exit to exit" + "\n");
        chatters = new SimpleStringProperty();
        chatters.setValue("");
        connections = new SimpleStringProperty();
        connections.setValue("Currently 1 connected!");
    }

    public void updateArea(PropertyChangeEvent evt){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ReciveText.setValue(ReciveText.getValue() + evt.getNewValue().toString().replace("null:", "") + "\n");
            }
        });
    }

    public void updateConnected(PropertyChangeEvent evt){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                connections.setValue("Currently "+ ((ArrayList<MessageClient>) evt.getNewValue()).size() +" connected!");
            }
        });
    }

    public void updateConnectedNicks(PropertyChangeEvent evt){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < ((ArrayList<MessageClient>) evt.getNewValue()).size(); i++){
                    try {
                        if(chatters.getValue().contains(((ArrayList<MessageClient>) evt.getNewValue()).get(i).getNickname())) {
                            continue;
                        }
                        chatters.setValue(chatters.getValue() + ((ArrayList<MessageClient>) evt.getNewValue()).get(i).getNickname() + "\n");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        System.out.println("empty");
                    }
                }
            }
        });
    }

    public void setClient(MessageClient client) throws RemoteException {
        this.client = client;
        this.client.addPropertyChangeListerner("Msg", this::updateArea);
        this.client.addPropertyChangeListerner("NicksConnected", this::updateConnected);
        this.client.addPropertyChangeListerner("NicksConnected", this::updateConnectedNicks);
    }

    public StringProperty getChatters(){
        return chatters;
    }

    public StringProperty getConnections(){
        return connections;
    }

    public StringProperty getReciveText() {
        return ReciveText;
    }

    public void send(String msg) throws RemoteException {
        client.sendMsg(msg);
    }
}
