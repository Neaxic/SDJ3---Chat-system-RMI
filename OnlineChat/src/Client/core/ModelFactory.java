package Client.core;

import Client.Model.ClientSocketHandler;

import java.io.IOException;

public class ModelFactory {

        //private TemperatureModel dataModel;
        private ClientSocketHandler dataModel;

    public void setDataModel(ClientSocketHandler dataModel) {
        this.dataModel = dataModel;
    }

    public ClientSocketHandler getDataModel() throws IOException {
            if(dataModel == null) {
                //dataModel = new ClientSocketHandler() {
            }
            return dataModel;
        }

    /*public TemperatureModel getDataModel() {
        if(dataModel == null) {
            dataModel = new TemperatureModelManager();
        }
        return dataModel;
    }*/

}