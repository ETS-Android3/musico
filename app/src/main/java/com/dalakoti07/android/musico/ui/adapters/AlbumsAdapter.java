package com.dalakoti07.android.musico.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dalakoti07.android.musico.R;
import com.dalakoti07.android.musico.data.models.AlbumModel;
import com.dalakoti07.android.musico.databinding.RvAlbumsBinding;

import java.util.ArrayList;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.SimpleHolder> {
    private ArrayList<AlbumModel> albumModelArrayList= new ArrayList<>();

    public interface cardClickListener{
        void albumCardClicked(AlbumModel album);
    }

    private cardClickListener listener;

    public AlbumsAdapter(cardClickListener listener){
        this.listener=listener;
    }

    @NonNull
    @Override
    public AlbumsAdapter.SimpleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_albums,parent,false);
        return new SimpleHolder(view);
    }

    public void addData(ArrayList<AlbumModel> list){
        albumModelArrayList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumsAdapter.SimpleHolder holder, int position) {
        holder.bindData(albumModelArrayList.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return albumModelArrayList.size();
    }

    public static class SimpleHolder extends RecyclerView.ViewHolder {
        RvAlbumsBinding binding;
        public SimpleHolder(@NonNull View itemView) {
            super(itemView);
            binding=RvAlbumsBinding.bind(itemView);
        }

        void bindData(AlbumModel album,cardClickListener listener){
            binding.getRoot().setOnClickListener(v->{
                listener.albumCardClicked(album);
            });
            binding.tvUpper.setText(album.getName());
            binding.tvLower.setVisibility(View.GONE);
            int index=-1;
            // if possible get the high quality image
            switch (album.getImage().size()){
                case 4: index=3;break;
                case 3:index=2;break;
                case 2:index=1;break;
                default:index=0;
            }
            Glide.with(binding.getRoot())
                    .load(album.getImage().get(index).getText())
                    .centerCrop()
                    .into(binding.ivItem);
        }
    }
}
