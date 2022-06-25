package com.rensuuuuuuuu.dogapi.ui.main.home;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rensuuuuuuuu.dogapi.R;
import com.rensuuuuuuuu.dogapi.model.DogAPIModel;

public class HomeDogItemBSDF extends BottomSheetDialogFragment {

    private final DogAPIModel dogAPIModel;
    private BottomSheetDialog bottomSheetDialog;
    private BottomSheetBehavior<View> bottomSheetBehavior;
    private ScrollView scrollView;
    private ImageView ivDogImage;
    private TextView tvDogName, tvDogBreedGroup, tvDogOrigin, tvDogLifeSpan, tvDogBredFor, tvDogTemperament;
    private ImageButton ibClose, ibSave;
    private final HomePresenterI homePresenterI;
    private final String undefined = "Undefined";

    public HomeDogItemBSDF(HomePresenterI homePresenterI, DogAPIModel dogAPIModel) {
        this.homePresenterI = homePresenterI;
        this.dogAPIModel = dogAPIModel;
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
        View view = inflater.inflate(R.layout.fragment_home_dog_item_bsdf, container, false);

        ivDogImage = view.findViewById(R.id.bottomSheet_dogImage);
        tvDogName = view.findViewById(R.id.bottomSheet_dogName);
        tvDogBreedGroup = view.findViewById(R.id.bottomSheet_dogBreedGroup);
        tvDogOrigin = view.findViewById(R.id.bottomSheet_dogOrigin);
        tvDogLifeSpan = view.findViewById(R.id.bottomSheet_dogLifeSpan);
        tvDogBredFor = view.findViewById(R.id.bottomSheet_dogBredFor);
        tvDogTemperament = view.findViewById(R.id.bottomSheet_dogTemperament);
        ibClose = view.findViewById(R.id.ib_Close_bottomSheetHome);
        ibSave = view.findViewById(R.id.ib_Save_bottomSheetHome);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        scrollView = bottomSheetDialog.findViewById(R.id.fragmentHomeDogItemBSDF);
        if (scrollView != null) {
            scrollView.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
            scrollView.setMinimumWidth(Resources.getSystem().getDisplayMetrics().widthPixels);
        }

        Glide.with(requireContext()).applyDefaultRequestOptions(new RequestOptions()
                .placeholder(R.drawable.sample_dog)
                .error(R.drawable.sample_dog))
            .load(dogAPIModel.getImageURL()).into(ivDogImage);

        tvDogName.setText(dogAPIModel.getName());

        if (dogAPIModel.getBreedGroup() == null) tvDogBreedGroup.setText(undefined);
        else tvDogBreedGroup.setText(dogAPIModel.getBreedGroup());

        if(dogAPIModel.getOrigin() == null) tvDogOrigin.setText(undefined);
        else tvDogOrigin.setText(dogAPIModel.getOrigin());

        if(dogAPIModel.getLifeSpan() == null) tvDogLifeSpan.setText(undefined);
        else tvDogLifeSpan.setText(dogAPIModel.getLifeSpan());

        if(dogAPIModel.getBredFor() == null) tvDogBredFor.setText(undefined);
        else tvDogBredFor.setText(dogAPIModel.getBredFor());

        if(dogAPIModel.getTemperament() == null) tvDogTemperament.setText(undefined);
        else tvDogTemperament.setText(dogAPIModel.getTemperament());

        ibSave.setOnClickListener(v -> {
            homePresenterI.addDog(dogAPIModel);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        });

        ibClose.setOnClickListener(v -> bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN));
    }
}
