package com.dalakoti07.android.musico.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.dalakoti07.android.musico.R;
import com.dalakoti07.android.musico.data.models.AlbumModel;
import com.dalakoti07.android.musico.data.models.ArtistModel;
import com.dalakoti07.android.musico.data.models.TrackModel;
import com.dalakoti07.android.musico.databinding.FragmentArtistDetailBinding;
import com.dalakoti07.android.musico.networks.response.AlbumDetailsResponse;
import com.dalakoti07.android.musico.networks.response.ArtistDetailsResponse;
import com.dalakoti07.android.musico.networks.response.GenreDetailsResponse;
import com.dalakoti07.android.musico.ui.activity.MainActivity;
import com.dalakoti07.android.musico.ui.adapters.AlbumsAdapter;
import com.dalakoti07.android.musico.ui.adapters.SimilarArtistAdapter;
import com.dalakoti07.android.musico.ui.adapters.SongTrackAdapter;
import com.dalakoti07.android.musico.utils.ChromeCustomTabs;
import com.dalakoti07.android.musico.utils.CommonUIUtils;
import com.dalakoti07.android.musico.utils.Constants;
import com.dalakoti07.android.musico.viewmodels.AlbumDetailsViewModel;
import com.dalakoti07.android.musico.viewmodels.ArtistDetailsViewModel;
import com.dalakoti07.android.musico.viewmodels.ViewModelProviderFactory;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;
import timber.log.Timber;

public class ArtistDetailFragment extends Fragment implements SimilarArtistAdapter.cardItemListener
        ,AlbumsAdapter.cardClickListener,SongTrackAdapter.cardItemListener{
    private FragmentArtistDetailBinding binding;
    private NavController navController;
    private SimilarArtistAdapter similarArtistsAdapter;
    private AlbumsAdapter albumsAdapter;
    private SongTrackAdapter songTrackAdapter;
    private String artistName;
    private ChromeCustomTabs chromeTab;

    Context context;

    @Inject
    ViewModelProviderFactory viewModelFactory;

    private ArtistDetailsViewModel viewModel;

    public ArtistDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
        if (getActivity() != null) {
            ((MainActivity) getActivity()).mainComponent.inject(this);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentArtistDetailBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chromeTab = new ChromeCustomTabs(context);
        navController = NavHostFragment.findNavController(this);
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(ArtistDetailsViewModel.class);
        artistName=getArguments().getString(Constants.artistName);
        setUpObservables();
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolbar.setOnClickListener(v->{navController.navigateUp();});
        binding.toolbar.setTitle(artistName);
        similarArtistsAdapter= new SimilarArtistAdapter(this);
        albumsAdapter= new AlbumsAdapter(this);
        songTrackAdapter= new SongTrackAdapter(this);
        binding.contentWrapper.rvSimilarArtist.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        binding.contentWrapper.rvSimilarArtist.setAdapter(similarArtistsAdapter);
        binding.contentWrapper.rvTopAlbums.setAdapter(albumsAdapter);
        binding.contentWrapper.rvTopTracks.setAdapter(songTrackAdapter);
    }

    private void setUpObservables() {
        viewModel.getArtistDetails(artistName)
                .observe(getViewLifecycleOwner(), new Observer<ArtistDetailsResponse>() {
                    @Override
                    public void onChanged(ArtistDetailsResponse artistDetailsResponse) {
                        fillTheDataInUI(artistDetailsResponse);
                    }
                });
        viewModel.getApiError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toasty.error(context,s,Toasty.LENGTH_LONG,false).show();
            }
        });
        viewModel.getAlbums(artistName).observe(getViewLifecycleOwner(), new Observer<List<AlbumModel>>() {
            @Override
            public void onChanged(List<AlbumModel> albumModels) {
                albumsAdapter.addData((ArrayList<AlbumModel>) albumModels);
            }
        });
        viewModel.getTracks(artistName).observe(getViewLifecycleOwner(), new Observer<List<TrackModel>>() {
            @Override
            public void onChanged(List<TrackModel> trackModels) {
                Timber.d("got "+trackModels.size()+" tracks ");
                songTrackAdapter.addTracksData((ArrayList<TrackModel>) trackModels);
            }
        });
    }

    private void fillTheDataInUI(ArtistDetailsResponse artistDetailsResponse) {
        int index = -1;
        // if possible get the high quality image
        switch (artistDetailsResponse.getArtist().getImage().size()) {
            case 6:
            case 5:
            case 4:
                index = 3;break;
            case 3:
                index = 2;break;
            case 2:
                index = 1;break;
            default:
                index = 0;
        }
        // sometime data is null from server for certain fields so putting it in try and catch block
        try{
            Glide.with(binding.ivCover.getContext())
                    .load("")
                    .load(CommonUIUtils.getArtistImage(artistName,
                            artistDetailsResponse.getArtist().getImage().get(index).getText())).fitCenter()
                    .into(binding.ivCover);
            binding.contentWrapper.tvPlayCount.setText(CommonUIUtils.getCountInThousands(
                    artistDetailsResponse.getArtist().getArtistStats().playCount
            ));
            binding.contentWrapper.tvFollowers.setText(CommonUIUtils.getCountInThousands(
                    artistDetailsResponse.getArtist().getArtistStats().listeners
            ));
            index=0;
            similarArtistsAdapter.addData(artistDetailsResponse.getArtist().similar.similarArtist);
            for(AlbumDetailsResponse.MusicTags tag: artistDetailsResponse.getArtist().getTags().getPublished()){
                Chip chip=new Chip(context);
                chip.setText(tag.getName());
                chip.setTag(tag.getName());
                chip.setOnClickListener(this::chipClicked);
                binding.contentWrapper.chipGroup.addView(chip,index);
                index++;
            }
            binding.contentWrapper.tvBioSummary.setText(artistDetailsResponse.getArtist().getBio().summary);
        }catch (Exception e){
            Timber.d("error occurred: "+e.getLocalizedMessage()+" and stack trace ");
            e.printStackTrace();
        }
    }

    private void chipClicked(View v) {
        String genreName=(String)v.getTag();
        Bundle bundle= new Bundle();
        bundle.putString(Constants.genreName,genreName);
        navController.navigate(R.id.action_artistDetailFragment_to_genreDetailFragment,bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        chromeTab.destroy();
        binding=null;
    }

    @Override
    public void artistCardItemClicked(ArtistModel artist) {
        chromeTab.launchUrl(artist.getUrl());
    }

    @Override
    public void albumCardClicked(AlbumModel album) {
        Bundle bundle= new Bundle();
        bundle.putString(Constants.albumName,album.getName());
        bundle.putString(Constants.artistName,album.getArtist().getName());
        navController.navigate(R.id.action_artistDetailFragment_to_albumDetailsFragment,bundle);
    }

    @Override
    public void cardItemClicked(TrackModel track) {
        chromeTab.launchUrl(track.getUrl());
    }
}