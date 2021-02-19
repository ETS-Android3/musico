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

import com.dalakoti07.android.musico.R;
import com.dalakoti07.android.musico.data.models.AlbumModel;
import com.dalakoti07.android.musico.data.models.ArtistModel;
import com.dalakoti07.android.musico.data.models.UIData;
import com.dalakoti07.android.musico.databinding.FragmentAlbumListBinding;
import com.dalakoti07.android.musico.di.qualifier.ActivityContext;
import com.dalakoti07.android.musico.networks.response.AlbumDetailsResponse;
import com.dalakoti07.android.musico.ui.activity.MainActivity;
import com.dalakoti07.android.musico.ui.adapters.CommonListAdapter;
import com.dalakoti07.android.musico.utils.Constants;
import com.dalakoti07.android.musico.viewmodels.SharedListViewModel;
import com.dalakoti07.android.musico.viewmodels.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class AlbumListFragment extends Fragment implements CommonListAdapter.CardClickListener{
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

    @Inject
    public AlbumListFragment(){
        Timber.d("created album list fragment ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragmentAlbumListBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel= ViewModelProviders.of(getParentFragment(),viewModelFactory).get(SharedListViewModel.class);
        navController= NavHostFragment.findNavController(this);
        adapter=new CommonListAdapter(CommonListAdapter.ViewType.Albums,this);
        binding.rvListItems.setAdapter(adapter);
        viewModel.getAlbumsList(GenreDetailFragment.currentGenre).observe(getViewLifecycleOwner(), new Observer<List<AlbumModel>>() {
            @Override
            public void onChanged(List<AlbumModel> albumModels) {
                adapter.addAlbumData((ArrayList<AlbumModel>) albumModels);
                binding.progressBar.setVisibility(View.GONE);
            }
        });
        viewModel.getAlbumsError().observe(getViewLifecycleOwner(), new Observer<String>() {
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
        AlbumModel album= (AlbumModel) uiElementClicked;
        Bundle bundle=new Bundle();
        bundle.putString(Constants.albumName,album.getName());
        bundle.putString(Constants.artistName,album.getArtist().getName());
        navController.navigate(R.id.action_genreDetailFragment_to_albumDetailsFragment,bundle);
    }
}
