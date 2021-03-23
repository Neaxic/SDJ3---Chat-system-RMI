package Client.core;

import Client.Model.RMIClient;

import java.io.IOException;

public class ModelFactory {

        //private TemperatureModel dataModel;
        private RMIClient dataModel;

    public void setDataModel(RMIClient dataModel) {
        this.dataModel = dataModel;
    }

    public RMIClient getDataModel() throws IOException {
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