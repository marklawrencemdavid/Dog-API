package com.group2.minidog.ui.main.saves;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group2.minidog.databinding.FragmentSavesBinding;
import com.group2.minidog.model.DogSQLiteModel;

import java.util.ArrayList;

public class SavesFragment extends Fragment implements SavesFragmentI {

    private FragmentSavesBinding binding;
    private SavesPresenterI savesPresenterI;
    private LinearLayoutManager layoutManager;
    private SavesAdapter adapter;
    private ArrayList<DogSQLiteModel> dogSQLiteModels;
    private ImageButton ibSearch;
    private EditText etSearch;
    private RecyclerView rvDog;
    private ProgressBar pbLoading;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSavesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initView();

        dogSQLiteModels = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getContext());
        rvDog.setLayoutManager(layoutManager);
        rvDog.setHasFixedSize(true);

        savesPresenterI = new SavesPresenter(this);
        savesPresenterI.getData();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void initView() {
        ibSearch = binding.ibSearchSaves;
        etSearch = binding.etSearchSaves;
        rvDog = binding.rvDogSaves;
        pbLoading = binding.pbLoadingSaves;
    }

    @Override
    public void setDataToRecyclerview(ArrayList<DogSQLiteModel> dogSQLiteModels) {
        this.dogSQLiteModels = dogSQLiteModels;
        adapter = new SavesAdapter(savesPresenterI, getContext(), this.dogSQLiteModels);
        rvDog.setAdapter(adapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void notifyAdapter(int position) {
        dogSQLiteModels.remove(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {pbLoading.setVisibility(View.VISIBLE);}

    @Override
    public void hideProgressBar() {pbLoading.setVisibility(View.GONE);}
}