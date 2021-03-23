package Client.core;

import Client.ViewModel.Viewmodel;
import Client.ViewModel.ViewmodelSettings;

import java.io.IOException;

public class ViewModelFactory {

    private Viewmodel viewModel;
    private ViewmodelSettings viewModelSettings;

    public ViewModelFactory(ModelFactory modelFactory) throws IOException {
        viewModel = new Viewmodel();
        viewModelSettings = new ViewmodelSettings();
    }

    public Viewmodel getViewModel() {
        return viewModel;
    }

    public ViewmodelSettings getViewModelSettings(){
        return viewModelSettings;
    }
}


