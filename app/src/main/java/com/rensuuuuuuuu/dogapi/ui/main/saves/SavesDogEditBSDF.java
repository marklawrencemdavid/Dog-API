package com.rensuuuuuuuu.dogapi.ui.main.saves;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rensuuuuuuuu.dogapi.R;
import com.rensuuuuuuuu.dogapi.model.DogAPIModel;
import com.rensuuuuuuuu.dogapi.model.DogSQLiteModel;

public class SavesDogEditBSDF extends BottomSheetDialogFragment {

    private final SavesFragmentI savesFragmentI;
    private final SavesPresenterI savesPresenterI;
    private final DogSQLiteModel dogSQLiteModel;
    private final int position;
    private BottomSheetDialog bottomSheetDialog;
    private LinearLayout linearLayout;
    private ImageButton ibClose;
    private Button btnUpdate;
    private EditText etImageURL, etName, etBreedGroup, etOrigin, etLifeSpan, etBredFor, etTemperament;

    public SavesDogEditBSDF(SavesFragmentI savesFragmentI, SavesPresenterI savesPresenterI, DogSQLiteModel dogSQLiteModel, int position) {
        this.savesFragmentI = savesFragmentI;
        this.savesPresenterI = savesPresenterI;
        this.dogSQLiteModel = dogSQLiteModel;
        this.position = position;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        return bottomSheetDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_saves_dog_edit_bsdf, container, false);

        ibClose = view.findViewById(R.id.ib_close_bottomSheetSaves);
        btnUpdate = view.findViewById(R.id.btn_update_bottomSheetSaves);
        etImageURL = view.findViewById(R.id.et_imageURL_bottomSheetSaves);
        etName = view.findViewById(R.id.et_name_bottomSheetSaves);
        etBreedGroup = view.findViewById(R.id.et_breedGroup_bottomSheetSaves);
        etOrigin = view.findViewById(R.id.et_origin_bottomSheetSaves);
        etLifeSpan = view.findViewById(R.id.et_lifeSpan_bottomSheetSaves);
        etBredFor = view.findViewById(R.id.et_bredFor_bottomSheetSaves);
        etTemperament = view.findViewById(R.id.et_temperament_bottomSheetSaves);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String undefined = "Undefined";

        BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        linearLayout = bottomSheetDialog.findViewById(R.id.fragmentSavesDogItemBSDF);
        if (linearLayout != null) {
            linearLayout.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
            linearLayout.setMinimumWidth(Resources.getSystem().getDisplayMetrics().widthPixels);
        }

        etImageURL.setText(dogSQLiteModel.getImageURL());
        etName.setText(dogSQLiteModel.getName());
        etBreedGroup.setText(dogSQLiteModel.getBreedGroup());
        etOrigin.setText(dogSQLiteModel.getOrigin());
        etLifeSpan.setText(dogSQLiteModel.getLifeSpan());
        etBredFor.setText(dogSQLiteModel.getBredFor());
        etTemperament.setText(dogSQLiteModel.getTemperament());

        btnUpdate.setOnClickListener(v -> {
            DogSQLiteModel model = new DogSQLiteModel.Builder()
                    .setDogAPIModel(new DogAPIModel(dogSQLiteModel.getId(),
                            etName.getText().toString(),
                            etBredFor.getText().toString(),
                            etBreedGroup.getText().toString(),
                            etLifeSpan.getText().toString(),
                            etTemperament.getText().toString(),
                            etOrigin.getText().toString(),
                            etImageURL.getText().toString()
                    )).setSqliteId(dogSQLiteModel.getSqliteId())
                    .setUserEmail(dogSQLiteModel.getUserEmail())
                    .setTimeCreated(dogSQLiteModel.getTimeCreated())
                    .build();
            savesPresenterI.updateDog(model, position);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        });

        ibClose.setOnClickListener(v -> bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN));
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        savesFragmentI.notifyAdapter();
    }
}
