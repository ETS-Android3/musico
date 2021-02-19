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
    public static String currentGenre;

    public GenreDetailFragment() {
        // Required empty public constructor
    }

    //todo collapse the summary when we are scrolling genres, alnumbs, items down
    Context context;

    @Inject
    ViewModelProviderFactory viewModelFactory;

    private GenreDetailsViewModel viewModel;

    @Inject
    AlbumListFragment albumsListFragment;

    @Inject
    ArtistListFragment artistListFragment;

    @Inject
    TracksListFragment tracksListFragment;

    @Override
    public void onAttach(@NonNull Context context) {
        this.context=context;
        super.onAttach(context);
        if(getActivity()!=null){
            ((MainActivity)getActivity()).mainComponent.inject(this);
             fragmentComponent= MusicApplication.get(getActivity()).getApplicationComponent().fragmentComponent().create();
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
        adapter.addFragment(albumsListFragment,"Albums");
        adapter.addFragment(artistListFragment,"Artists");
        adapter.addFragment(tracksListFragment,"Tracks");
        binding.tvDescription.setMovementMethod(new ScrollingMovementMethod());
        binding.ivBack.setOnClickListener(v->{
            navController.navigateUp();
        });
        viewModel.getMusicWiki(currentGenre).observe(getViewLifecycleOwner(), new Observer<GenreDetailsResponse.MusicTag>() {
            @Override
            public void onChanged(GenreDetailsResponse.MusicTag musicTag) {
                binding.progressBar.setVisibility(View.GONE);
                binding.tvGenreName.setText(musicTag.getName().toUpperCase());
                binding.tvDescription.setText(musicTag.getWiki().getSummary());
            }
        });
        viewModel.getErrorData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toasty.error(context,s).show();
                binding.progressBar.setVisibility(View.GONE);
            }
        });

        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}