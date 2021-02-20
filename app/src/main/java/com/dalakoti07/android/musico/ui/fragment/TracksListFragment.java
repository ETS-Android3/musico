package com.dalakoti07.android.musico.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;
import timber.log.Timber;

import com.dalakoti07.android.musico.data.models.ArtistModel;
import com.dalakoti07.android.musico.data.models.TrackModel;
import com.dalakoti07.android.musico.data.models.UIData;
import com.dalakoti07.android.musico.databinding.FragmentAlbumListBinding;
import com.dalakoti07.android.musico.di.qualifier.ActivityContext;
import com.dalakoti07.android.musico.ui.activity.MainActivity;
import com.dalakoti07.android.musico.ui.adapters.CommonListAdapter;
import com.dalakoti07.android.musico.ui.adapters.SongTrackAdapter;
import com.dalakoti07.android.musico.utils.ChromeCustomTabs;
import com.dalakoti07.android.musico.utils.Constants;
import com.dalakoti07.android.musico.viewmodels.SharedListViewModel;
import com.dalakoti07.android.musico.viewmodels.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

public class TracksListFragment extends Fragment implements SongTrackAdapter.cardItemListener{
    //reusing the same layout file
    private FragmentAlbumListBinding binding;
    private SongTrackAdapter adapter;
    private ChromeCustomTabs chromeTab;
    private String currentGenre;

    @ActivityContext
    @Inject
    Context context;

    @Inject
    ViewModelProviderFactory viewModelFactory;

    private SharedListViewModel viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(getActivity()!=null){
            GenreDetailFragment.fragmentComponent.inject(this);
        }
    }

    public TracksListFragment(){
        Timber.d("created tracks list fragment ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= com.dalakoti07.android.musico.databinding.FragmentAlbumListBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel= ViewModelProviders.of(getParentFragment(),viewModelFactory).get(SharedListViewModel.class);
        adapter=new SongTrackAdapter(this);
        chromeTab = new ChromeCustomTabs(context);
        binding.rvListItems.setAdapter(adapter);
        binding.rvListItems.setLayoutManager(new LinearLayoutManager(context));
        currentGenre=getArguments().getString(Constants.genreName);
        setUpObservables();
    }

    private void setUpObservables() {
        viewModel.getTheTracks(currentGenre).observe(getViewLifecycleOwner(), new Observer<List<TrackModel>>() {
            @Override
            public void onChanged(List<TrackModel> artistModels) {
                Timber.d("artists "+artistModels.size()+" fetched from server");
                adapter.addTracksData((ArrayList<TrackModel>) artistModels);
                binding.progressBar.setVisibility(View.GONE);
            }
        });

        viewModel.getTheTracksError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.progressBar.setVisibility(View.GONE);
                binding.errorMsg.setText(s);
                binding.errorLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        chromeTab.destroy();
        binding=null;
    }

    @Override
    public void cardItemClicked(TrackModel track) {
        chromeTab.launchUrl(track.getUrl());
    }
}
