package com.group2.minidog.ui.main.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.group2.minidog.R;
import com.group2.minidog.model.DogAPIModel;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
    private final Context context;
    private final HomePresenterI homePresenterI;
    private final FragmentManager fragmentManager;
    private final ArrayList<DogAPIModel> dogAPIModels;
    private final ArrayList<DogAPIModel> dogAPIModelsOriginalUtil;

    public HomeAdapter(Context context, HomePresenterI homePresenterI, FragmentManager fragmentManager, ArrayList<DogAPIModel> dogAPIModels) {
        this.context = context;
        this.homePresenterI = homePresenterI;
        this.fragmentManager = fragmentManager;
        this.dogAPIModels = dogAPIModels;
        this.dogAPIModelsOriginalUtil = dogAPIModels;
    }

    @NonNull
    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog_fragment_home, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvName.setText(dogAPIModels.get(position).getName());
        holder.tvLifeSpan.setText(dogAPIModels.get(position).getLifeSpan());
        holder.tvOrigin.setText(dogAPIModels.get(position).getOrigin());
        Glide.with(context).load(dogAPIModels.get(position).getImageURL()).into(holder.ivImage);

        holder.itemView.setOnClickListener(view -> {
            HomeDogItemBSDF homeDogItemBSDF = new HomeDogItemBSDF(context, homePresenterI, dogAPIModels.get(position));
            homeDogItemBSDF.show(fragmentManager, homeDogItemBSDF.getTag());
        });
    }

    @Override
    public int getItemCount() {
        return dogAPIModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvName, tvLifeSpan, tvOrigin;
        View bottomSheetContainer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.iv_image_home);
            tvName = itemView.findViewById(R.id.tv_name_home);
            tvLifeSpan = itemView.findViewById(R.id.tv_lifespan_home);
            tvOrigin = itemView.findViewById(R.id.tv_origin_home);
            bottomSheetContainer = itemView.findViewById(R.id.bottomSheetHome);
        }
    }
}
