package com.example.mobileiklanku.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileiklanku.R;
import com.example.mobileiklanku.api.helper.URLAPI;
import com.example.mobileiklanku.api.models.LokerModels;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LokerAdaptor extends RecyclerView.Adapter<LokerAdaptor.ViewHolder> {

    public interface ListenerAdapter{
        void onClickItem(int id, LokerModels lokerModels);
    }

    private List<LokerModels> lokerModelsList;
    private ListenerAdapter listenerAdapter;
    protected URLAPI urlapi = new URLAPI();

    public LokerAdaptor(List<LokerModels> lokerModelsList, ListenerAdapter listenerAdapter) {
        this.lokerModelsList = lokerModelsList;
        this.listenerAdapter = listenerAdapter;
    }

    @NonNull
    @NotNull
    @Override
    public LokerAdaptor.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loker_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LokerAdaptor.ViewHolder holder, int position) {
        LokerModels lokerModels = lokerModelsList.get(position);
        holder.bind(position, lokerModels);
    }

    @Override
    public int getItemCount() {
        return (lokerModelsList != null) ? lokerModelsList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTV, descTV, dateTV;
        ImageView imageV;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.titleLKTV);
            descTV = itemView.findViewById(R.id.descLKTV);
            dateTV = itemView.findViewById(R.id.dateLKTV);
            imageV = itemView.findViewById(R.id.posterLKIV);
        }
        public  void bind(final int index, final LokerModels lokerModels){
            titleTV.setText(lokerModels.getJudul_loker());
            descTV.setText(lokerModels.getDeskripsi());
            dateTV.setText(lokerModels.getDeadline());
            Picasso.get().load(urlapi.getURI()+"/images/loker/" + lokerModels.getPamflet_loker())
                    .centerCrop()
                    .resize(500, 500)
                    .into(imageV);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenerAdapter.onClickItem(index, lokerModels);
                }
            });
        }
    }
}
