package com.group2.minidog.ui.main.home;

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

import com.group2.minidog.databinding.FragmentHomeBinding;
import com.group2.minidog.model.DogModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements HomeFragmentI {

    private FragmentHomeBinding binding;
    private HomePresenterI homePresenterI;
    private LinearLayoutManager layoutManager;
    private HomeAdapter adapter;
    private ArrayList<DogModel> dogModels;
    private RecyclerView rvDog;
    private ProgressBar pbLoading;
    private EditText etSearch;
    private ImageButton ibSearch;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initView();

        dogModels = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getContext());
        rvDog.setLayoutManager(layoutManager);
        rvDog.setHasFixedSize(true);

        homePresenterI = new HomePresenter(this);
        homePresenterI.requestData();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void initView() {
        ibSearch = binding.ibSearchHome;
        etSearch = binding.etSearchHome;
        rvDog =  binding.rvDogHome;
        pbLoading = binding.pbLoadingHome;
    }

    @Override
    public void setDataToRecyclerview(ArrayList<DogModel> dogModels) {
        this.dogModels = dogModels;
        adapter = new HomeAdapter(getContext(), getParentFragmentManager(), this.dogModels);
        rvDog.setAdapter(adapter);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), "Error in getting data.\n"+message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() { pbLoading.setVisibility(View.VISIBLE); }

    @Override
    public void hideProgressBar() { pbLoading.setVisibility(View.GONE); }
}