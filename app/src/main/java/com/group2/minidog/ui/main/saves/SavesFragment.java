package com.group2.minidog.ui.main.saves;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group2.minidog.R;
import com.group2.minidog.databinding.FragmentSavesBinding;
import com.group2.minidog.model.DogSQLiteModel;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class SavesFragment extends Fragment implements SavesFragmentI {

    private FragmentSavesBinding binding;
    private SavesPresenterI savesPresenterI;
    private ArrayList<DogSQLiteModel> dogSQLiteModels;
    private SavesAdapter adapter;
    private ItemTouchHelper itemTouchHelper;
    private ImageButton ibSearch;
    private EditText etSearch;
    private RecyclerView rvDog;
    private ProgressBar pbLoading;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSavesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        savesPresenterI = new SavesPresenter(this);

        ibSearch.setOnClickListener(v -> {
            savesPresenterI.onSearch(etSearch.getText().toString());
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void initView() {
        dogSQLiteModels = new ArrayList<>();
        ibSearch = binding.ibSearchSaves;
        etSearch = binding.etSearchSaves;
        pbLoading = binding.pbLoadingSaves;
        rvDog = binding.rvDogSaves;
        rvDog.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDog.setHasFixedSize(true);
        itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvDog);
    }

    @Override
    public void setDataToRecyclerview(ArrayList<DogSQLiteModel> dogSQLiteModels) {
        this.dogSQLiteModels = dogSQLiteModels;
        adapter = new SavesAdapter(getContext(), this.dogSQLiteModels);
        rvDog.setAdapter(adapter);
    }

    @Override
    public void search(String name){
        adapter.getFilter().filter(name);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void notifyAdapterUpdate(DogSQLiteModel dogSQLiteModel, int position) {
        dogSQLiteModels.set(position, dogSQLiteModel);
        adapter.notifyItemChanged(position);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void notifyAdapterRemove(int position) {
        dogSQLiteModels.remove(position);
        adapter.notifyItemRemoved(position);
        //adapter.notifyItemRemoved(position);
    }

    @Override
    public void showSavesEditBSDF(DogSQLiteModel dogSQLiteModel, int position) {
        SavesDogEditBSDF savesDogEditBSDF = new SavesDogEditBSDF(getContext(), this, savesPresenterI, dogSQLiteModel, position);
        savesDogEditBSDF.show(getParentFragmentManager(), savesDogEditBSDF.getTag());
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {pbLoading.setVisibility(View.VISIBLE);}

    @Override
    public void hideProgressBar() {pbLoading.setVisibility(View.GONE);}

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback (0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getBindingAdapterPosition();
            switch (direction){
                case ItemTouchHelper.LEFT:
                    savesPresenterI.deleteDog(dogSQLiteModels.get(position), position);
                    break;
                case ItemTouchHelper.RIGHT:
                    //goToAddEditCar(viewHolder.getBindingAdapterPosition(), "EditCar");
                    savesPresenterI.showSavesEditBSDF(dogSQLiteModels.get(position), position);
                    break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftLabel("Delete")
                    .setSwipeLeftLabelColor(getResources().getColor(R.color.white))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                    .setSwipeLeftActionIconTint(getResources().getColor(R.color.white))
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
                    .addSwipeRightLabel("Edit")
                    .setSwipeRightLabelColor(getResources().getColor(R.color.white))
                    .addSwipeRightActionIcon(R.drawable.ic_baseline_edit_24)
                    .setSwipeRightActionIconTint(getResources().getColor(R.color.white))
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };
}