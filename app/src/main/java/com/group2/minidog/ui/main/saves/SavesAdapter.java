package com.group2.minidog.ui.main.saves;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.group2.minidog.R;
import com.group2.minidog.model.DogSQLiteModel;

import java.util.ArrayList;

public class SavesAdapter extends RecyclerView.Adapter<SavesAdapter.MyViewHolder>{
    private SavesPresenterI savesPresenterI;
    private final Context context;
    private final ArrayList<DogSQLiteModel> dogSQLiteModels;
    private final ArrayList<DogSQLiteModel> dogSQLiteModelsOriginalUtil;

    public SavesAdapter(SavesPresenterI savesPresenterI, Context context, ArrayList<DogSQLiteModel> dogSQLiteModels) {
        this.savesPresenterI = savesPresenterI;
        this.context = context;
        this.dogSQLiteModels = dogSQLiteModels;
        this.dogSQLiteModelsOriginalUtil = dogSQLiteModels;
    }

    @NonNull
    @Override
    public SavesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog_fragment_home, parent, false);
        return new SavesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavesAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(dogSQLiteModels.get(position).getName());
        holder.tvLifeSpan.setText(dogSQLiteModels.get(position).getLifeSpan());
        holder.tvOrigin.setText(dogSQLiteModels.get(position).getOrigin());
        Glide.with(context).load(dogSQLiteModels.get(position).getImageURL()).into(holder.ivImage);

        holder.itemView.setOnClickListener(view -> {
            savesPresenterI.deleteData(dogSQLiteModels.get(position), position);
        });
    }

    @Override
    public int getItemCount() {return dogSQLiteModels.size();}

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvName, tvLifeSpan, tvOrigin;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.iv_image_home);
            tvName = itemView.findViewById(R.id.tv_name_home);
            tvLifeSpan = itemView.findViewById(R.id.tv_lifespan_home);
            tvOrigin = itemView.findViewById(R.id.tv_origin_home);
        }
    }
}
