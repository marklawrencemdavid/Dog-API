package com.rensuuuuuuuu.dogapi.ui.main.home;

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
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rensuuuuuuuu.dogapi.R;
import com.rensuuuuuuuu.dogapi.model.DogAPIModel;
import com.rensuuuuuuuu.dogapi.model.DogSQLiteModel;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> implements Filterable {
    private final Context context;
    private final HomePresenterI homePresenterI;
    private final FragmentManager fragmentManager;
    private ArrayList<DogAPIModel> dogAPIModels;
    private final ArrayList<DogAPIModel> dogAPIModelsAll;

    public HomeAdapter(Context context, HomePresenterI homePresenterI, FragmentManager fragmentManager, ArrayList<DogAPIModel> dogAPIModels) {
        this.context = context;
        this.homePresenterI = homePresenterI;
        this.fragmentManager = fragmentManager;
        this.dogAPIModels = dogAPIModels;
        this.dogAPIModelsAll = dogAPIModels;
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
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions()
                .placeholder(R.drawable.sample_dog)
                .error(R.drawable.sample_dog))
            .load(dogAPIModels.get(position).getImageURL()).into(holder.ivImage);

        holder.itemView.setOnClickListener(view -> {
            HomeDogItemBSDF homeDogItemBSDF = new HomeDogItemBSDF(context, homePresenterI, dogAPIModels.get(position));
            homeDogItemBSDF.show(fragmentManager, homeDogItemBSDF.getTag());
        });
    }

    @Override
    public int getItemCount() {
        return dogAPIModels.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                if(charSequence == null || charSequence.length() == 0) {
                    dogAPIModels = dogAPIModelsAll;
                } else {
                    ArrayList<DogAPIModel> filteredResultsData = new ArrayList<>();

                    for(DogAPIModel dogModel : dogAPIModelsAll) {
                        if(dogModel.getName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                            filteredResultsData.add(dogModel);
                        }
                    }
                    dogAPIModels = filteredResultsData;
                }
                FilterResults searchResults = new FilterResults();
                searchResults.values = dogAPIModels;
                return searchResults;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dogAPIModels = (ArrayList<DogAPIModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
