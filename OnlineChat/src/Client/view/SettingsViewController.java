package Client.view;

        import javafx.fxml.FXML;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.Region;
        import Client.ViewModel.ViewmodelSettings;

public class SettingsViewController
{
    private ViewHandler viewHandler;
    private Region root;
    private ViewmodelSettings viewmodelSettings;

    @FXML TextField nickname;

    public SettingsViewController()
    {
    }

    public void init(ViewHandler viewHandler,  ViewmodelSettings viewmodel, Region root)
    {
        this.viewHandler = viewHandler;
        this.viewmodelSettings = viewmodel;
        this.root = root;
    }

    public void reset()
    {
        // empty
    }

    public Region getRoot()
    {
        return root;
    }

    public void SaveBTN(){
        viewmodelSettings.saveBTN(nickname.getText());
        viewHandler.openView("chatbox");
    }


}
