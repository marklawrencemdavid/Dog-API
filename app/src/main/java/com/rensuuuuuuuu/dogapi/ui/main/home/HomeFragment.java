package com.rensuuuuuuuu.dogapi.ui.main.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rensuuuuuuuu.dogapi.R;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        //menu.clear();
        inflater.inflate(R.menu.action_bar_search, menu);

        MenuItem searchItem = menu.findItem(R.id.ab_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
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