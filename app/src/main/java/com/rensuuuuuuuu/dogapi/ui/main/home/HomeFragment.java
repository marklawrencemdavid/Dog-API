package com.rensuuuuuuuu.dogapi.ui.main.home;

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

import com.rensuuuuuuuu.dogapi.databinding.FragmentHomeBinding;
import com.rensuuuuuuuu.dogapi.model.DogAPIModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements HomeFragmentI {

    private FragmentHomeBinding binding;
    private HomePresenterI homePresenterI;
    private HomeAdapter adapter;
    private ArrayList<DogAPIModel> dogAPIModels;
    private RecyclerView rvDog;
    private ProgressBar pbLoading;
    private EditText etSearch;
    private ImageButton ibSearch;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        homePresenterI = new HomePresenter(this);

        ibSearch.setOnClickListener(v -> homePresenterI.search(etSearch.getText().toString()));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void initView() {
        dogAPIModels = new ArrayList<>();
        ibSearch = binding.ibSearchHome;
        etSearch = binding.etSearchHome;
        pbLoading = binding.pbLoadingHome;
        rvDog =  binding.rvDogHome;
        rvDog.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDog.setHasFixedSize(true);
    }

    @Override
    public void setDataToRecyclerview(ArrayList<DogAPIModel> dogAPIModels) {
        this.dogAPIModels = dogAPIModels;
        adapter = new HomeAdapter(getContext(), homePresenterI, getParentFragmentManager(), this.dogAPIModels);
        rvDog.setAdapter(adapter);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() { pbLoading.setVisibility(View.VISIBLE); }

    @Override
    public void hideProgressBar() { pbLoading.setVisibility(View.GONE); }
}