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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.dalakoti07.android.musico.R;
import com.dalakoti07.android.musico.data.models.TrackModel;
import com.dalakoti07.android.musico.data.models.UIData;
import com.dalakoti07.android.musico.databinding.FragmentAlbumDetailsBinding;
import com.dalakoti07.android.musico.networks.response.AlbumDetailsResponse;
import com.dalakoti07.android.musico.ui.activity.MainActivity;
import com.dalakoti07.android.musico.ui.adapters.CommonListAdapter;
import com.dalakoti07.android.musico.utils.Constants;
import com.dalakoti07.android.musico.viewmodels.AlbumDetailsViewModel;
import com.dalakoti07.android.musico.viewmodels.ViewModelProviderFactory;

import java.util.ArrayList;

import javax.inject.Inject;

public class AlbumDetailsFragment extends Fragment implements CommonListAdapter.CardClickListener {
    private FragmentAlbumDetailsBinding binding;
    private NavController navController;
    private CommonListAdapter adapter;

    //todo add shimmer
    Context context;

    @Inject
    ViewModelProviderFactory viewModelFactory;

    private AlbumDetailsViewModel viewModel;

    public AlbumDetailsFragment() {
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
        binding = FragmentAlbumDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(AlbumDetailsViewModel.class);
        String artistName = getArguments().getString(Constants.artistName);
        String albumName = getArguments().getString(Constants.albumName);
        adapter = new CommonListAdapter(CommonListAdapter.ViewType.Tracks, this);
        adapter.setSkipImages(true);
        binding.rvTracks.setAdapter(adapter);
        viewModel.getAlbumDetails(artistName, albumName).observe(getViewLifecycleOwner(), new Observer<AlbumDetailsResponse>() {
            @Override
            public void onChanged(AlbumDetailsResponse albumDetailsResponse) {
                fillDataInUI(albumDetailsResponse);
            }
        });
    }

    private void fillDataInUI(AlbumDetailsResponse albumDetailsResponse) {
        int index = -1;
        // if possible get the high quality image
        switch (albumDetailsResponse.getAlbum().getImage().size()) {
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
        Glide.with(binding.ivAlbumCover.getContext())
                .load(albumDetailsResponse.getAlbum().getImage().get(index).getText()).fitCenter()
                .into(binding.ivAlbumCover);
        binding.tvAlbumName.setText(albumDetailsResponse.getAlbum().getName());
        binding.tvAlbumName.setText(albumDetailsResponse.getAlbum().getArtist());
        binding.tvPublishedOnVal.setText(albumDetailsResponse.getAlbum().getWiki().getPublished());
        binding.tvSummary.setText(albumDetailsResponse.getAlbum().getWiki().getSummary());
        adapter.addTracksData((ArrayList<TrackModel>) albumDetailsResponse.getAlbum().getTracks().getTrack());
    }

    @Override
    public void cardClicked(UIData uiElementClicked) {
        // do nothing
    }
}