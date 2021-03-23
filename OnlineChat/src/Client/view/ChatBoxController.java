package Client.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import Client.ViewModel.Viewmodel;

public class ChatBoxController {
    private ViewHandler viewHandler;
    private Region root;
    private Viewmodel viewmodel;

    @FXML private TextField inputText;
    @FXML private TextArea ReciveText;
    @FXML private TextArea chatters;
    @FXML private Label connections;

    public ChatBoxController() {
    }

    public void init(ViewHandler viewHandler, Viewmodel viewmodel, Region root)
    {
        this.viewHandler = viewHandler;
        this.viewmodel = viewmodel;
        this.root = root;
        ReciveText.textProperty().bind(viewmodel.getReciveText());
        chatters.textProperty().bind(viewmodel.getChatters());
        connections.textProperty().bind(viewmodel.getConnections());

        //chatters.textProperty().bind(viewmodel.getChatters());
    }

    @FXML public void Settings()
    {
        viewHandler.openView("settings");
    }

    public void send(){
        viewmodel.send(inputText.getText());
    }

    public void reset()
    {
        // empty
    }

    public Region getRoot()
    {
        return root;
    }
}
