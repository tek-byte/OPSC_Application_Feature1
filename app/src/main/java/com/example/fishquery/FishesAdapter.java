package com.example.fishquery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FishesAdapter extends RecyclerView.Adapter<FishesAdapter.FishesViewHolder> {

    private Context mCtx;
    private List<Fishes> fishesList;

    public FishesAdapter(Context mCtx, List<Fishes> FishesList) {
        this.mCtx = mCtx;
        this.fishesList = FishesList;
    }

    @NonNull
    @Override
    public FishesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_fishes, parent, false);
        return new FishesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FishesViewHolder holder, int position) {
        Fishes fishes = fishesList.get(position);
        holder.textViewCom.setText(fishes.CommonName);
        holder.textViewBio.setText("Bio Name: "+fishes.BioName);
        holder.textViewDiet.setText("Fish Diet: "+fishes.Diet);
        holder.textViewHabitat.setText("Fish habitat: "+fishes.Habitat);
        holder.textViewLength.setText("Min Size Limit: "+fishes.MinLength);
        holder.textViewQty.setText("Daily Bag Limit: "+fishes.QtyAllowed);
        Glide.with(mCtx).load(fishesList.get(position).getImageLink()).into(holder.imageFish);
    }

    @Override
    public int getItemCount() {

        return fishesList.size();
    }

    class FishesViewHolder extends RecyclerView.ViewHolder {

        TextView textViewBio, textViewCom, textViewDiet, textViewHabitat, textViewLength,textViewQty;
        ImageView imageFish;

        public FishesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCom = itemView.findViewById(R.id.text_view_common);
            textViewBio = itemView.findViewById(R.id.text_view_bio);
            textViewDiet = itemView.findViewById(R.id.text_view_diet);
            textViewHabitat = itemView.findViewById(R.id.text_view_habitat);
            textViewLength = itemView.findViewById(R.id.text_view_min_length);
            textViewQty = itemView.findViewById(R.id.text_view_qty);
            imageFish = itemView.findViewById(R.id.IV_Fish);
        }
    }
}

