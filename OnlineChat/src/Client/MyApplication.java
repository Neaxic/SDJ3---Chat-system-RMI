package Client;

import Client.Model.Client;
import javafx.application.Application;
import javafx.stage.Stage;
import Client.core.ModelFactory;
import Client.core.ViewModelFactory;
import Client.view.ViewHandler;

import java.io.IOException;

public class MyApplication extends Application
{
  public void start(Stage primaryStage) throws IOException {

    //Model
    ModelFactory modelFactory = new ModelFactory();
    ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);

    //Start Client
    Client c1 = new Client();

    //Start Threads
    ViewHandler view = new ViewHandler(viewModelFactory);
    Thread t1 = new Thread(c1);
    t1.start();

    // View
    view.start(primaryStage);
    viewModelFactory.getViewModel().setClientSocketHandler(c1.getHandler());
    viewModelFactory.getViewModelSettings().setClientSocketHandler(c1.getHandler());

  }
}
