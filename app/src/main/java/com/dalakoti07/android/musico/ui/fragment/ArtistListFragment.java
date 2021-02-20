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
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;
import timber.log.Timber;

import com.dalakoti07.android.musico.R;
import com.dalakoti07.android.musico.data.models.AlbumModel;
import com.dalakoti07.android.musico.data.models.ArtistModel;
import com.dalakoti07.android.musico.data.models.UIData;
import com.dalakoti07.android.musico.databinding.FragmentAlbumListBinding;
import com.dalakoti07.android.musico.di.qualifier.ActivityContext;
import com.dalakoti07.android.musico.ui.activity.MainActivity;
import com.dalakoti07.android.musico.ui.adapters.CommonListAdapter;
import com.dalakoti07.android.musico.utils.Constants;
import com.dalakoti07.android.musico.viewmodels.SharedListViewModel;
import com.dalakoti07.android.musico.viewmodels.ViewModelProviderFactory;
import com.dalakoti07.android.musico.databinding.FragmentAlbumListBinding;

import java.util.ArrayList;
import java.util.List;

public class ArtistListFragment extends Fragment implements CommonListAdapter.CardClickListener{
    //reusing the same layout file
    private FragmentAlbumListBinding binding;
    private CommonListAdapter adapter;
    private NavController navController;

    Context context;

    @Inject
    ViewModelProviderFactory viewModelFactory;

    private SharedListViewModel viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
        if(getActivity()!=null){
            GenreDetailFragment.fragmentComponent.inject(this);
        }
    }

    public ArtistListFragment(){
        Timber.d("created artist list fragment ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= FragmentAlbumListBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel= ViewModelProviders.of(getParentFragment(),viewModelFactory).get(SharedListViewModel.class);
        navController= NavHostFragment.findNavController(this);
        adapter=new CommonListAdapter(CommonListAdapter.ViewType.Artist,this);
        binding.rvListItems.setAdapter(adapter);
        String currentGenre=getArguments().getString(Constants.genreName);
        viewModel.getArtists(currentGenre).observe(getViewLifecycleOwner(), new Observer<List<ArtistModel>>() {
            @Override
            public void onChanged(List<ArtistModel> artistModels) {
                Timber.d("artists "+artistModels.size()+" fetched from server");
                adapter.addArtistData((ArrayList<ArtistModel>) artistModels);
                binding.progressBar.setVisibility(View.GONE);
            }
        });
        viewModel.getArtistErrors().observe(getViewLifecycleOwner(), new Observer<String>() {
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
        binding=null;
    }

    @Override
    public void cardClicked(UIData uiElementClicked) {
        Bundle bundle= new Bundle();
        ArtistModel artist=(ArtistModel) uiElementClicked;
        bundle.putString(Constants.artistName,artist.getName());
        navController.navigate(R.id.action_genreDetailFragment_to_artistDetailFragment,bundle);
    }
}
