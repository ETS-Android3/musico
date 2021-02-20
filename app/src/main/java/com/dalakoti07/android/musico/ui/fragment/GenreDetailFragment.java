package com.dalakoti07.android.musico.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.provider.SyncStateContract;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalakoti07.android.musico.MusicApplication;
import com.dalakoti07.android.musico.R;
import com.dalakoti07.android.musico.databinding.FragmentGenreDetailBinding;
import com.dalakoti07.android.musico.di.components.FragmentComponent;
import com.dalakoti07.android.musico.di.qualifier.ActivityContext;
import com.dalakoti07.android.musico.networks.response.GenreDetailsResponse;
import com.dalakoti07.android.musico.ui.activity.MainActivity;
import com.dalakoti07.android.musico.ui.adapters.GenreTabAdapter;
import com.dalakoti07.android.musico.utils.Constants;
import com.dalakoti07.android.musico.viewmodels.GenreDetailsViewModel;
import com.dalakoti07.android.musico.viewmodels.HomeScreenViewModel;
import com.dalakoti07.android.musico.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;

public class GenreDetailFragment extends Fragment {
    public static FragmentComponent fragmentComponent;
    private FragmentGenreDetailBinding binding;
    private NavController navController;
    public String currentGenre;

    public GenreDetailFragment() {

    }

    @ActivityContext
    @Inject
    Context context;

    @Inject
    ViewModelProviderFactory viewModelFactory;

    private GenreDetailsViewModel viewModel;

    AlbumListFragment albumsListFragment;
    ArtistListFragment artistListFragment;
    TracksListFragment tracksListFragment;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(getActivity()!=null){
             fragmentComponent= MusicApplication.get(getActivity()).getApplicationComponent().fragmentComponent()
                     .create(context);
             fragmentComponent.inject(this);
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentGenreDetailBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        currentGenre=getArguments().getString(Constants.genreName);
        navController= NavHostFragment.findNavController(this);
        viewModel= ViewModelProviders.of(this,viewModelFactory).get(GenreDetailsViewModel.class);
        GenreTabAdapter adapter=new GenreTabAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        Bundle bundle= new Bundle();
        bundle.putString(Constants.genreName,currentGenre);
        albumsListFragment= new AlbumListFragment();
        albumsListFragment.setArguments(bundle);
        artistListFragment= new ArtistListFragment();
        artistListFragment.setArguments(bundle);
        tracksListFragment= new TracksListFragment();
        tracksListFragment.setArguments(bundle);
        adapter.addFragment(albumsListFragment,"Albums");
        adapter.addFragment(artistListFragment,"Artists");
        adapter.addFragment(tracksListFragment,"Tracks");
        binding.toolbar.setTitle(currentGenre.toUpperCase());
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolbar.setNavigationOnClickListener(v->{
            navController.navigateUp();
        });
        setUpObservables();
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    private void setUpObservables() {
        viewModel.getMusicWiki(currentGenre).observe(getViewLifecycleOwner(), musicTag -> {
            //binding.progressBar.setVisibility(View.GONE);
            binding.tvDescription.setText(musicTag.getWiki().getSummary());
        });
        viewModel.getErrorData().observe(getViewLifecycleOwner(), s -> {
            Toasty.error(context,s,Toasty.LENGTH_LONG,false).show();
            //binding.progressBar.setVisibility(View.GONE);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}