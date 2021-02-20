package com.dalakoti07.android.musico.ui.adapters;

import android.graphics.Color;
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
import java.util.List;
import java.util.Random;

import timber.log.Timber;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreHolder> {
    private ArrayList<SongGenre> allSongGenre= new ArrayList<>(); // contains visible items
    private ArrayList<SongGenre> temporaryList= new ArrayList<>();// contains invisible items

    private genreCardClickListener listener;
    private boolean isCollapsed=true;

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

    public void addAllData(List<SongGenre> songGenres){
        Timber.d("data added");
        if(songGenres!=null){
            allSongGenre.addAll(songGenres.subList(0,4));
            temporaryList.addAll(songGenres.subList(4,songGenres.size()));
            notifyDataSetChanged();
        }
    }

    public void divideDataToggleHideAndUnHide(){
        if(!isCollapsed){
            isCollapsed=true;
            temporaryList=new ArrayList<>(allSongGenre.subList(4,allSongGenre.size())) ;
            allSongGenre=new ArrayList<> (allSongGenre.subList(0,4));
            notifyItemRangeRemoved(allSongGenre.size(),allSongGenre.size()+temporaryList.size());
            notifyDataSetChanged();
        }else{
            isCollapsed=false;
            allSongGenre.addAll(temporaryList);
            notifyItemRangeInserted(allSongGenre.size(),temporaryList.size());
            temporaryList.clear();
        }
    }

    public static class GenreHolder extends RecyclerView.ViewHolder{
        private RvItemGenreBinding binding;
        public GenreHolder(@NonNull View itemView) {
            super(itemView);
            binding=RvItemGenreBinding.bind(itemView);
        }
        public void bindData(SongGenre songGenre, genreCardClickListener listener){
            binding.tvGenreName.setText(songGenre.getName());
            double counts=songGenre.getCount()/100000.0;
            binding.tvCount.setText(String.format("%.1f M views",counts));
            binding.getRoot().setOnClickListener(v->{
                listener.cardClicked(songGenre);
            });
            /*Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            binding.cardView.setCardBackgroundColor(color);*/

        }
    }
}
