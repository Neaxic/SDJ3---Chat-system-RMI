package Client.ViewModel;

import Client.Model.ClientSocketHandler;

public class ViewmodelSettings {

    private ClientSocketHandler clientSocketHandler;

    public void setClientSocketHandler(ClientSocketHandler clientSocketHandler) {
        this.clientSocketHandler = clientSocketHandler;
    }

    public void saveBTN(String nickname){
        clientSocketHandler.setNickname(nickname);
    }

}
