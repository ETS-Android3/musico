package com.dalakoti07.android.musico.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dalakoti07.android.musico.R;
import com.dalakoti07.android.musico.data.models.AlbumModel;
import com.dalakoti07.android.musico.databinding.RvSimpleCardBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Common adapter used by AlbumListFragment,ArtistListFragment,TracksListFragment
 */
public class CommonListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<AlbumModel> albumsArrayList= new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_simple_card,parent,false);
        return new AlbumsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((AlbumsHolder)holder).bindData(albumsArrayList.get(position));
    }

    public void addData(ArrayList<AlbumModel> albumModels){
        albumsArrayList.addAll(albumModels);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return albumsArrayList.size();
    }

    public static class AlbumsHolder extends RecyclerView.ViewHolder{
        RvSimpleCardBinding rvSimpleCardBinding;
        public AlbumsHolder(@NonNull View itemView) {
            super(itemView);
            rvSimpleCardBinding=RvSimpleCardBinding.bind(itemView);
        }
        //todo add gradient to the card so that both text appear clearly
        public void bindData(AlbumModel albumModel){
            int index=-1;
            // if possible get the high quality image
            switch (albumModel.getImage().size()){
                case 4: index=3;break;
                case 3:index=2;break;
                case 2:index=1;break;
                default:index=0;
            }
            Glide.with(rvSimpleCardBinding.getRoot())
                    .load(albumModel.getImage().get(index).getText())
                    .centerCrop()
                    .into(rvSimpleCardBinding.ivItem);
            rvSimpleCardBinding.tvUpper.setText(albumModel.getName());
            rvSimpleCardBinding.tvLower.setText(albumModel.getArtist().getName());
        }
    }

}
