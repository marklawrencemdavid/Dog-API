package com.rensuuuuuuuu.dogapi.ui.main.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rensuuuuuuuu.dogapi.R;
import com.rensuuuuuuuu.dogapi.model.DogSQLiteModel;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<DogSQLiteModel> dogSQLiteModels;

    public ProfileAdapter(Context cotext, ArrayList<DogSQLiteModel>  dogSQLiteModels) {
        this.context = cotext;
        this.dogSQLiteModels = dogSQLiteModels;
    }

    @NonNull
    @Override
    public ProfileAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog_fragment_profile, parent, false);
        return new ProfileAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.MyViewHolder holder, int position) {
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions()
                        .placeholder(R.drawable.sample_dog)
                        .error(R.drawable.sample_dog))
                .load(dogSQLiteModels.get(position).getImageURL()).into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return dogSQLiteModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.iv_image_profile);
        }
    }
}
