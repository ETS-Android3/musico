package com.dalakoti07.android.musico.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dalakoti07.android.musico.R;
import com.dalakoti07.android.musico.data.models.AlbumModel;
import com.dalakoti07.android.musico.data.models.ArtistModel;
import com.dalakoti07.android.musico.data.models.TrackModel;
import com.dalakoti07.android.musico.data.models.UIData;
import com.dalakoti07.android.musico.databinding.RvSimpleCardBinding;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Common adapter used by AlbumListFragment,ArtistListFragment,TracksListFragment
 */
public class CommonListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<UIData> albumsArrayList= new ArrayList<>();
    private ViewType adapterViewType;
    private Boolean skipImages=false;
    public static enum ViewType{
        Albums,
        Artist,
        Tracks
    }

    public interface CardClickListener{
        void cardClicked(UIData uiElementClicked);
    }
    private CardClickListener cardClickListener;

    public CommonListAdapter(ViewType viewType,CardClickListener listener){
        this.adapterViewType=viewType;
        this.cardClickListener=listener;
    }

    public void setSkipImages(boolean value){
        skipImages=value;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_simple_card,parent,false);
        return new SimpleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(adapterViewType==ViewType.Albums){
            ((SimpleHolder)holder).bindData((AlbumModel) albumsArrayList.get(position),this.adapterViewType,cardClickListener,skipImages);
        }else if(adapterViewType==ViewType.Artist){
            ((SimpleHolder)holder).bindData((ArtistModel) albumsArrayList.get(position),this.adapterViewType,cardClickListener,skipImages);
        }else
            ((SimpleHolder)holder).bindData((TrackModel) albumsArrayList.get(position),this.adapterViewType,cardClickListener,skipImages);
    }

    public void addAlbumData(ArrayList<AlbumModel> albumModels){
        Timber.d("adapter albums added ");
        albumsArrayList.addAll(albumModels);
        notifyDataSetChanged();
    }

    public void addArtistData(ArrayList<ArtistModel> albumModels){
        Timber.d("adapter artists added ");
        albumsArrayList.addAll(albumModels);
        notifyDataSetChanged();
    }

    public void addTracksData(ArrayList<TrackModel> trackModels){
        Timber.d("adapter tracks added ");
        albumsArrayList.addAll(trackModels);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return albumsArrayList.size();
    }

    public static class SimpleHolder extends RecyclerView.ViewHolder{
        RvSimpleCardBinding rvSimpleCardBinding;
        public SimpleHolder(@NonNull View itemView) {
            super(itemView);
            rvSimpleCardBinding=RvSimpleCardBinding.bind(itemView);
        }
        //todo add gradient to the card so that both text appear clearly

        public void bindData(UIData uiData,ViewType viewType,CardClickListener cardClickListener,boolean skipImages){
            switch (viewType){
                case Albums:
                    AlbumModel albumModel=(AlbumModel) uiData;
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
                    rvSimpleCardBinding.getRoot().setOnClickListener(v->{
                        cardClickListener.cardClicked(albumModel);
                    });
                    break;
                case Artist:
                    index=-1;
                    ArtistModel artist= (ArtistModel) uiData;
                    // if possible get the high quality image
                    switch (artist.getImage().size()){
                        case 4: index=3;break;
                        case 3:index=2;break;
                        case 2:index=1;break;
                        default:index=0;
                    }
                    Glide.with(rvSimpleCardBinding.getRoot())
                            .load(artist.getImage().get(index).getText())
                            .centerCrop()
                            .into(rvSimpleCardBinding.ivItem);
                    rvSimpleCardBinding.tvUpper.setText(artist.getName());
                    rvSimpleCardBinding.tvLower.setVisibility(View.GONE);
                    rvSimpleCardBinding.getRoot().setOnClickListener(v->{
                        cardClickListener.cardClicked(artist);
                    });
                    break;
                case Tracks:
                    index=-1;
                    TrackModel trackModel= (TrackModel) uiData;
                    if(!skipImages){
                        // if possible get the high quality image
                        switch (trackModel.getImage().size()){
                            case 4: index=3;break;
                            case 3:index=2;break;
                            case 2:index=1;break;
                            default:index=0;
                        }
                        Glide.with(rvSimpleCardBinding.getRoot())
                                .load(trackModel.getImage().get(index).getText())
                                .centerCrop()
                                .into(rvSimpleCardBinding.ivItem);
                    }
                    rvSimpleCardBinding.tvUpper.setText(trackModel.getName());
                    rvSimpleCardBinding.tvLower.setText(trackModel.getArtist().getName());
                    rvSimpleCardBinding.getRoot().setOnClickListener(v->{
                        cardClickListener.cardClicked(trackModel);
                    });
                    break;
            }

        }
    }

}
