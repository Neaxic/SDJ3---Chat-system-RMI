package Client.ViewModel;

import Client.Model.ClientSocketHandler;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class Viewmodel {

    StringProperty textField;
    StringProperty ReciveText;
    StringProperty chatters;
    StringProperty connections;
    private ClientSocketHandler clientSocketHandler;
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
                connections.setValue("Currently "+evt.getNewValue() +" connected!");
            }
        });
    }

    public void updateConnectedNicks(PropertyChangeEvent evt){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(!(chatters.getValue().contains(evt.getNewValue().toString())) || chatters.getValue().equals("")){
                    chatters.setValue(chatters.getValue()+ evt.getNewValue().toString() + "\n");
                }
            }
        });
    }

    public void setClientSocketHandler(ClientSocketHandler clientSocketHandler) {
        this.clientSocketHandler = clientSocketHandler;
        this.clientSocketHandler.addPropertyChangeListerner("Msg", this::updateArea);
        this.clientSocketHandler.addPropertyChangeListerner("Connections", this::updateConnected);
        this.clientSocketHandler.addPropertyChangeListerner("NicksConnected", this::updateConnectedNicks);
    }

    public StringProperty getTextField() {
        return textField;
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

    public void send(String msg){
        clientSocketHandler.sendMessage(msg);
    }
}
