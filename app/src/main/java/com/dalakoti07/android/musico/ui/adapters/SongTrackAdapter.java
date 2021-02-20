package com.dalakoti07.android.musico.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dalakoti07.android.musico.R;
import com.dalakoti07.android.musico.data.models.ArtistModel;
import com.dalakoti07.android.musico.data.models.TrackModel;
import com.dalakoti07.android.musico.databinding.RvTrackItemBinding;

import java.util.ArrayList;


public class SongTrackAdapter extends RecyclerView.Adapter<SongTrackAdapter.TrackHolder> {
    private ArrayList<TrackModel> trackModelArrayList= new ArrayList<>();

    public interface cardItemListener{
        void cardItemClicked(TrackModel track);
    }
    private cardItemListener listener;

    public SongTrackAdapter(cardItemListener listener){
        this.listener=listener;
    }

    @NonNull
    @Override
    public SongTrackAdapter.TrackHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_track_item,parent,false);
        return new TrackHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongTrackAdapter.TrackHolder holder, int position) {
        holder.binData(trackModelArrayList.get(position),listener);
    }

    public void addTracksData(ArrayList<TrackModel> trackModels){
        trackModelArrayList.addAll(trackModels);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return trackModelArrayList.size();
    }

    public static class TrackHolder extends RecyclerView.ViewHolder {
        RvTrackItemBinding rvTrackItemBinding;
        public TrackHolder(@NonNull View itemView) {
            super(itemView);
            rvTrackItemBinding=RvTrackItemBinding.bind(itemView);
        }

        public void binData(TrackModel track,cardItemListener listener){
            rvTrackItemBinding.trackName.setText(track.getName());
            rvTrackItemBinding.tvTrackUrl.setText(track.getUrl());
            rvTrackItemBinding.rippleLayout.setOnClickListener(v->{
                listener.cardItemClicked(track);
            });
        }
    }
}
