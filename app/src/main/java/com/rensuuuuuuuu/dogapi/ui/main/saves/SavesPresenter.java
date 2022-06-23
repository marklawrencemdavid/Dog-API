package com.rensuuuuuuuu.dogapi.ui.main.saves;

import com.rensuuuuuuuu.dogapi.model.DogSQLiteModel;
import com.rensuuuuuuuu.dogapi.network.App;
import com.rensuuuuuuuu.dogapi.network.sqlite.DogDatabase;

import javax.inject.Inject;

public class SavesPresenter implements SavesPresenterI{

    private final SavesFragmentI savesFragmentI;
    @Inject
    public DogDatabase dogDatabase;

    public SavesPresenter(SavesFragmentI savesFragmentI) {
        App.getAppComponent().inject(this);
        this.savesFragmentI = savesFragmentI;
        onPresenterCreated();
    }

    private void onPresenterCreated() {
        savesFragmentI.initView();
        savesFragmentI.showProgressBar();
        savesFragmentI.setDataToRecyclerview(dogDatabase.getAllDog());
        savesFragmentI.hideProgressBar();
    }

    @Override
    public void onSearch(String name) {
        savesFragmentI.showProgressBar();
        savesFragmentI.search(name);
        savesFragmentI.hideProgressBar();
    }

    @Override
    public void showSavesEditBSDF(DogSQLiteModel dogSQLiteModel, int position) {
        savesFragmentI.showSavesEditBSDF(dogSQLiteModel, position);
    }

    @Override
    public void updateDog(DogSQLiteModel dogSQLiteModel, int position) {
        if(dogDatabase.updateDog(dogSQLiteModel)){
            savesFragmentI.notifyAdapterUpdate(dogSQLiteModel, position);
            savesFragmentI.showToast("Dog updated successfully.");
        }else{
            savesFragmentI.showToast("Failed to update dog data.");
        }
    }

    @Override
    public void deleteDog(DogSQLiteModel dogSQLiteModel, int position) {
        savesFragmentI.showProgressBar();
        if(dogDatabase.deleteDog(dogSQLiteModel)){
            savesFragmentI.notifyAdapterRemove(position);
            savesFragmentI.showToast("Deleted");
        }else{
            savesFragmentI.showToast("Failed to delete dog.");
        }
        savesFragmentI.hideProgressBar();
    }
}
