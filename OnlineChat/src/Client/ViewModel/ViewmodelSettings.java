package Client.ViewModel;

import External.MessageClient;

import java.rmi.RemoteException;

public class ViewmodelSettings {

    private MessageClient client;

    public void setClient(MessageClient client) {
        this.client = client;
    }

    public void saveBTN(String nickname) throws RemoteException {
        client.setNickname(nickname);
    }

}
