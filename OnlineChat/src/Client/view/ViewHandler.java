package Client.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import Client.core.ViewModelFactory;

public class ViewHandler extends Application
{
  private Stage primaryStage;
  private Scene currentScene;

  private SettingsViewController settingsViewController;
  private ChatBoxController chatBoxController;
  private ViewModelFactory viewModelFactory;


  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    this.currentScene = new Scene(new Region());
    openView("settings");
  }

  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {
      case "chatbox":
        root = loadChatView("ChatBox.fxml");
        break;
      case "settings":
        root = loadSettingsView("SettingsView.fxml");
        break;
    }
    currentScene.setRoot(root);

    String title = "ChatBox";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

  private Region loadChatView(String fxmlFile)
  {
    if (chatBoxController == null)
    {
      try
      {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        chatBoxController = loader.getController();
        chatBoxController.init(this, viewModelFactory.getViewModel() , root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      chatBoxController.reset();
    }
    return chatBoxController.getRoot();
  }

  private Region loadSettingsView(String fxmlFile)
  {
    if (settingsViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        settingsViewController = loader.getController();
        settingsViewController.init(this, viewModelFactory.getViewModelSettings(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      settingsViewController.reset();
    }
    return settingsViewController.getRoot();
  }

}
