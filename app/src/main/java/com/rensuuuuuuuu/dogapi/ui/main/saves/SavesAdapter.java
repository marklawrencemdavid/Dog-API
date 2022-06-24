package com.rensuuuuuuuu.dogapi.ui.main.saves;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rensuuuuuuuu.dogapi.R;
import com.rensuuuuuuuu.dogapi.model.DogSQLiteModel;

import java.util.ArrayList;

public class SavesAdapter extends RecyclerView.Adapter<SavesAdapter.MyViewHolder> implements Filterable {
    private final Context context;
    private ArrayList<DogSQLiteModel> dogSQLiteModels;
    private final ArrayList<DogSQLiteModel> dogSQLiteModelsAll;

    public SavesAdapter(Context context, ArrayList<DogSQLiteModel> dogSQLiteModels) {
        this.context = context;
        this.dogSQLiteModels = dogSQLiteModels;
        this.dogSQLiteModelsAll = dogSQLiteModels;
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
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions()
                .placeholder(R.drawable.sample_dog)
                .error(R.drawable.sample_dog))
            .load(dogSQLiteModels.get(position).getImageURL()).into(holder.ivImage);
    }

    @Override
    public int getItemCount() {return dogSQLiteModels.size();}

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                if(charSequence == null || charSequence.length() == 0) {
                    dogSQLiteModels = dogSQLiteModelsAll;
                } else {
                    ArrayList<DogSQLiteModel> filteredResultsData = new ArrayList<>();

                    for(DogSQLiteModel dogModel : dogSQLiteModelsAll) {
                        if(dogModel.getName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                            filteredResultsData.add(dogModel);
                        }
                    }
                    dogSQLiteModels = filteredResultsData;
                }
                FilterResults searchResults = new FilterResults();
                searchResults.values = dogSQLiteModels;
                return searchResults;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dogSQLiteModels = (ArrayList<DogSQLiteModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

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
