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
import android.widget.Toast;

import com.dalakoti07.android.musico.R;
import com.dalakoti07.android.musico.data.models.SongGenre;
import com.dalakoti07.android.musico.databinding.FragmentMainBinding;
import com.dalakoti07.android.musico.di.qualifier.ActivityContext;
import com.dalakoti07.android.musico.ui.activity.MainActivity;
import com.dalakoti07.android.musico.ui.adapters.GenreAdapter;
import com.dalakoti07.android.musico.utils.Constants;
import com.dalakoti07.android.musico.utils.TimelyGreetings;
import com.dalakoti07.android.musico.viewmodels.HomeScreenViewModel;
import com.dalakoti07.android.musico.viewmodels.ViewModelProviderFactory;

import java.util.ArrayList;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;
import timber.log.Timber;


public class MainFragment extends Fragment implements GenreAdapter.genreCardClickListener {
    private FragmentMainBinding mainBinding;
    private NavController navController;
    private GenreAdapter topFindingsGenreAdapter;
    private GenreAdapter allGenreAdapter;
    private ArrayList<SongGenre> topFinding= new ArrayList<>();
    private ArrayList<SongGenre> allGenres= new ArrayList<>();

    Context context;

    @Inject
    ViewModelProviderFactory viewModelFactory;

    private HomeScreenViewModel viewModel;

    public MainFragment() {
        Timber.d("created main fragment");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context=context;
        super.onAttach(context);
        if(getActivity()!=null){
            ((MainActivity)getActivity()).mainComponent.inject(this);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainBinding=FragmentMainBinding.inflate(inflater,container,false);
        return mainBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= NavHostFragment.findNavController(this);
        mainBinding.tvGreetings.setText(TimelyGreetings.getGreetings());
        viewModel= ViewModelProviders.of(getActivity(),viewModelFactory).get(HomeScreenViewModel.class);
        topFindingsGenreAdapter= new GenreAdapter(this);
        allGenreAdapter= new GenreAdapter(this);
        mainBinding.rvOtherGenres.setAdapter(allGenreAdapter);
        mainBinding.rvTopGenres.setAdapter(topFindingsGenreAdapter);
        setUpListeners();
    }

    private void setUpListeners() {
        mainBinding.ivToggleTops.setOnClickListener(v->{
            topFindingsGenreAdapter.divideDataToggleHideAndUnHide();

        });
        mainBinding.ivToggleAllGenre.setOnClickListener(v->{
            allGenreAdapter.divideDataToggleHideAndUnHide();
        });
        viewModel.getAllSongGenres().observe(getViewLifecycleOwner(), songGenres -> {
            mainBinding.progressBar.setVisibility(View.GONE);
            splitTheDataIntoTwoHalfsAndNotify(songGenres);
        });
        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), s -> {
            mainBinding.rootView.setVisibility(View.GONE);
            mainBinding.progressBar.setVisibility(View.GONE);
            mainBinding.errorLayout.setVisibility(View.VISIBLE);
            mainBinding.errorMsg.setText(s);
        });
    }

    private void splitTheDataIntoTwoHalfsAndNotify(ArrayList<SongGenre> songGenres) {
        topFindingsGenreAdapter.addAllData(songGenres.subList(0,11));
        allGenreAdapter.addAllData( songGenres.subList(11,songGenres.size()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mainBinding=null;
    }

    @Override
    public void cardClicked(SongGenre model) {
        Bundle bundle= new Bundle();
        bundle.putString(Constants.genreName,model.getName());
        navController.navigate(R.id.action_mainFragment_to_genreDetailFragment,bundle);
    }
}