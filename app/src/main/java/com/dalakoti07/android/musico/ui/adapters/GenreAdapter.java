package com.dalakoti07.android.musico.ui.adapters;

import android.net.sip.SipSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dalakoti07.android.musico.R;
import com.dalakoti07.android.musico.data.models.SongGenre;
import com.dalakoti07.android.musico.databinding.RvItemGenreBinding;

import java.util.ArrayList;

import timber.log.Timber;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreHolder> {
    private ArrayList<SongGenre> allSongGenre= new ArrayList<>();

    private genreCardClickListener listener;

    public GenreAdapter(genreCardClickListener listener){
        this.listener=listener;
    }

    public interface genreCardClickListener{
        void cardClicked(SongGenre model);
    }

    @NonNull
    @Override
    public GenreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_genre,parent,false);
        return new GenreHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreHolder holder, int position) {
        holder.bindData(allSongGenre.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return allSongGenre.size();
    }

    public void addAllData(ArrayList<SongGenre> songGenres){
        Timber.d("data added");
        if(songGenres!=null)
            allSongGenre.addAll(songGenres);
    }

    //does making it static improve performance
    public static class GenreHolder extends RecyclerView.ViewHolder{
        private RvItemGenreBinding binding;
        public GenreHolder(@NonNull View itemView) {
            super(itemView);
            binding=RvItemGenreBinding.bind(itemView);
        }
        public void bindData(SongGenre songGenre, genreCardClickListener listener){
            binding.tvGenreName.setText(songGenre.getName());
            binding.tvCount.setText(songGenre.getCount()+"");
            binding.getRoot().setOnClickListener(v->{
                listener.cardClicked(songGenre);
            });
        }
    }
}
