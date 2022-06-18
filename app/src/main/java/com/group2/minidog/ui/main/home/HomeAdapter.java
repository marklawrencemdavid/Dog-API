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
import com.group2.minidog.model.DogModel;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
    private final Context context;
    private final FragmentManager fragmentManager;
    private final ArrayList<DogModel> dogModels;
    private final ArrayList<DogModel> dogModelsOriginalUtil;

    public HomeAdapter(Context context, FragmentManager fragmentManager, ArrayList<DogModel> dogModels) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.dogModels = dogModels;
        this.dogModelsOriginalUtil = dogModels;
    }

    @NonNull
    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog_fragment_home, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvName.setText(dogModels.get(position).getName());
        holder.tvLifeSpan.setText(dogModels.get(position).getLifeSpan());
        holder.tvOrigin.setText(dogModels.get(position).getOrigin());
        Glide.with(context).load(dogModels.get(position).getImageURL()).into(holder.ivImage);

        holder.itemView.setOnClickListener(view -> {
            HomeBottomSheetDialogFragment homeBottomSheetDialogFragment = new HomeBottomSheetDialogFragment(context, dogModels.get(position));
            homeBottomSheetDialogFragment.show(fragmentManager, homeBottomSheetDialogFragment.getTag());
        });
    }

    @Override
    public int getItemCount() {
        return dogModels.size();
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
            bottomSheetContainer = itemView.findViewById(R.id.bottomSheetContainer);
        }
    }
}
