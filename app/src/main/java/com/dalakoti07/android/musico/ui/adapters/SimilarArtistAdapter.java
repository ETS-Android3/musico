package com.dalakoti07.android.musico.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dalakoti07.android.musico.R;
import com.dalakoti07.android.musico.data.models.ArtistModel;
import com.dalakoti07.android.musico.databinding.RvSimilarArtistBinding;
import com.dalakoti07.android.musico.utils.CommonUIUtils;

import java.util.ArrayList;

public class SimilarArtistAdapter extends RecyclerView.Adapter<SimilarArtistAdapter.ArtistViewHolder> {
    private ArrayList<ArtistModel> artistModelArrayList= new ArrayList<>();

    public interface cardItemListener{
        void cardItemClicked(ArtistModel artist);
    }
    private cardItemListener listener;

    public SimilarArtistAdapter(cardItemListener listener){
        this.listener=listener;
    }

    @NonNull
    @Override
    public SimilarArtistAdapter.ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_similar_artist,parent,false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarArtistAdapter.ArtistViewHolder holder, int position) {
        holder.bindData(artistModelArrayList.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return artistModelArrayList.size();
    }

    public void addData(ArrayList<ArtistModel> artistModelsList){
        artistModelArrayList.addAll(artistModelsList);
        notifyDataSetChanged();
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder {
        RvSimilarArtistBinding rvSimilarArtistBinding;
        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            rvSimilarArtistBinding=RvSimilarArtistBinding.bind(itemView);
        }

        public void bindData(ArtistModel artist,cardItemListener listener){
            int index=-1;
            // if possible get the high quality image
            switch (artist.getImage().size()){
                case 4: index=3;break;
                case 3:index=2;break;
                case 2:index=1;break;
                default:index=0;
            }
            rvSimilarArtistBinding.getRoot().setOnClickListener(v->{
                listener.cardItemClicked(artist);
            });
            Glide.with(rvSimilarArtistBinding.getRoot())
                    .load("")
                    .load(CommonUIUtils.getArtistImage(artist.getName(),
                            artist.getImage().get(index).getText()))
                    .centerCrop()
                    .into(rvSimilarArtistBinding.ivArtistAvatar);
            rvSimilarArtistBinding.tvArtistName.setText(artist.getName());
            rvSimilarArtistBinding.tvArtistUrl.setText(artist.getUrl());
        }
    }
}
