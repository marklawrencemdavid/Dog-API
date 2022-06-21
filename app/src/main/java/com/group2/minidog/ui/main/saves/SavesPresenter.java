package com.group2.minidog.ui.main.saves;

import com.group2.minidog.model.DogSQLiteModel;
import com.group2.minidog.network.App;
import com.group2.minidog.network.sqlite.DogDatabase;

import javax.inject.Inject;

public class SavesPresenter implements SavesPresenterI{

    private final SavesFragmentI savesFragmentI;
    @Inject
    public DogDatabase dogDatabase;

    public SavesPresenter(SavesFragmentI savesFragmentI) {
        this.savesFragmentI = savesFragmentI;
        App.getAppComponent().inject(this);
    }

    @Override
    public void getData() {
        savesFragmentI.showProgressBar();
        savesFragmentI.setDataToRecyclerview(dogDatabase.getAllDog());
        savesFragmentI.hideProgressBar();
    }

    @Override
    public void deleteData(DogSQLiteModel dogSQLiteModels, int position) {
        savesFragmentI.showProgressBar();
        if(dogDatabase.deleteDog(dogSQLiteModels.getSqliteId())){
            savesFragmentI.notifyAdapter(position);
            savesFragmentI.showToast("Deleted");
        }else{
            savesFragmentI.showToast("Failed to delete dog.");
        }
        savesFragmentI.hideProgressBar();
    }
}
