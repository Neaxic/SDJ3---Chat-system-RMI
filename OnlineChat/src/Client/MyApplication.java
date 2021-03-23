package Client;

import Client.Model.RMIClient;
import javafx.application.Application;
import javafx.stage.Stage;
import Client.core.ModelFactory;
import Client.core.ViewModelFactory;
import Client.view.ViewHandler;

import java.io.IOException;
import java.rmi.NotBoundException;

public class MyApplication extends Application
{
  public void start(Stage primaryStage) throws IOException, NotBoundException {

    //Model
    ModelFactory modelFactory = new ModelFactory();
    ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);

    //Start Client
    RMIClient client = new RMIClient();
    client.startClient();

    //Start Threads
    ViewHandler view = new ViewHandler(viewModelFactory);

    // View
    view.start(primaryStage);
    viewModelFactory.getViewModel().setClient(client);
    viewModelFactory.getViewModelSettings().setClient(client);

  }
}
