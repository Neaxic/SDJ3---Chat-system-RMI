package Client.Model;

import External.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientSocketHandler implements Runnable {

    private Client client;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String Nickname;
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    ArrayList<String> NicksConnected = new ArrayList<>();

    public ClientSocketHandler(Client client, Socket socket) throws IOException {
        this.client = client;
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message message = (Message) in.readObject();
                client.messageReceived(message.getText());
                if(message.getText().contains("#PEOPLE")) {
                    changeSupport.firePropertyChange("Connections", null, message.getText().replaceFirst("#PEOPLE:", ""));
                } else if (message.getText().contains("#NewNickConnected")){
                    changeSupport.firePropertyChange("NicksConnected", null, message.getText().replace("#NewNickConnected: ", "")); }
                else {
                    changeSupport.firePropertyChange("Msg", null, message.getWhoText());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setNickname(String nickname) {
        Nickname = nickname;
        sendMessage(nickname + " #NickNameUpdated!");
    }

    public void sendMessage(String message) {
        if(message.contains("!nick ")){
            Nickname = "";
            for(int i = 6; i < message.length(); i++){
                Nickname = Nickname + message.charAt(i);
            }
            changeSupport.firePropertyChange("Msg", null, "Nickname sat to: " +Nickname);
            return;
        }
        try {
            out.writeObject(new Message(Nickname, message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPropertyChangeListerner(String name, PropertyChangeListener listener){
        if(name == null){
            changeSupport.addPropertyChangeListener(listener);
        } else {
            changeSupport.addPropertyChangeListener(name, listener);
        }
    }
    public void addPropertyChangeListerner(PropertyChangeListener listener){
        changeSupport.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListerner(String name, PropertyChangeListener listener) {
        if (name == null) {
            changeSupport.removePropertyChangeListener(listener);
        } else {
            changeSupport.removePropertyChangeListener(name, listener);
        }
    }
    public void removePropertyChangeListerner(PropertyChangeListener listener){
        changeSupport.removePropertyChangeListener(listener);
    }
}
