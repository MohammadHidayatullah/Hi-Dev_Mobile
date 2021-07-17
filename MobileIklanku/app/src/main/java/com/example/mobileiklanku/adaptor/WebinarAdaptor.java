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
import com.example.mobileiklanku.api.models.WebinarModels;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WebinarAdaptor extends RecyclerView.Adapter<WebinarAdaptor.ViewHolder> {

    public interface ListennerAdaper{
        void onClickItem(int id, WebinarModels webinarModels);
    }
    private List<WebinarModels> webinarModelsList;
    private ListennerAdaper listennerAdaper;
    private URLAPI urlapi =new URLAPI();

    public WebinarAdaptor(List<WebinarModels> webinarModelsList, ListennerAdaper listennerAdaper) {
        this.webinarModelsList = webinarModelsList;
        this.listennerAdaper = listennerAdaper;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.webinar_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        WebinarModels webinarModels = webinarModelsList.get(position);
        holder.bind(position, webinarModels);
    }

    @Override
    public int getItemCount() {
        return (webinarModelsList != null) ? webinarModelsList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTV, descTV, dateTV;
        ImageView imageV;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.titleWBTV);
            descTV = itemView.findViewById(R.id.descWBTV);
            dateTV = itemView.findViewById(R.id.dateWBTV);
            imageV = itemView.findViewById(R.id.posterWBIV);
        }
        public void bind(final int index, final WebinarModels webinarModels){
            titleTV.setText(webinarModels.getJudul_webinar());
            descTV.setText(webinarModels.getDeskripsi());
            dateTV.setText(webinarModels.getDeadline());
            Picasso.get().load(urlapi.getURI()+"/images/webinar/" + webinarModels.getPamflet_webinar())
                    .centerCrop()
                    .resize(500, 500)
                    .into(imageV);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listennerAdaper.onClickItem(index, webinarModels);
                }
            });
        }
    }
}
