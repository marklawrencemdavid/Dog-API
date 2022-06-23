package com.rensuuuuuuuu.dogapi.ui.main.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rensuuuuuuuu.dogapi.R;
import com.rensuuuuuuuu.dogapi.databinding.FragmentProfileBinding;
import com.rensuuuuuuuu.dogapi.ui.signin.SignInActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements ProfileFragmentI {

    private FragmentProfileBinding binding;
    private ProfilePresenterI profilePresenterI;
    private CircleImageView ivProfilePicture;
    private TextView tvDisplayName, tvEmail;
    private Button btnLogout;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        profilePresenterI = new ProfilePresenter(getActivity(),this);

        btnLogout.setOnClickListener(v -> profilePresenterI.signOut());

        return view;
    }

    @Override
    public void initView() {
        ivProfilePicture = binding.ivProfilePictureProfile;
        tvDisplayName = binding.tvDisplayNameProfile;
        tvEmail = binding.tvEmailProfile;
        btnLogout = binding.btnLogoutProfile;
    }

    @Override
    public void setValues(String profilePictureURL, String displayName, String email) {
        Glide.with(requireContext()).applyDefaultRequestOptions(new RequestOptions()
                .placeholder(R.drawable.ic_baseline_person_24)
                .error(R.drawable.ic_baseline_person_24))
            .load(profilePictureURL).into(ivProfilePicture);
        tvDisplayName.setText(displayName);
        tvEmail.setText(email);
    }

    @Override
    public void goToSignInActivity() {
        startActivity(new Intent(getActivity(), SignInActivity.class));
        requireActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}