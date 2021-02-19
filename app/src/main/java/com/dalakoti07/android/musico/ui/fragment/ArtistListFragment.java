package com.dalakoti07.android.musico.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import timber.log.Timber;
import com.dalakoti07.android.musico.databinding.FragmentAlbumListBinding;
import com.dalakoti07.android.musico.di.qualifier.ActivityContext;
import com.dalakoti07.android.musico.ui.activity.MainActivity;
import com.dalakoti07.android.musico.viewmodels.SharedListViewModel;
import com.dalakoti07.android.musico.viewmodels.ViewModelProviderFactory;

public class ArtistListFragment extends Fragment {
    //reusing the same layout file
    private FragmentAlbumListBinding binding;

    Context context;

    @Inject
    ViewModelProviderFactory viewModelFactory;

    private SharedListViewModel viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
        if(getActivity()!=null){
            //((MainActivity)getActivity()).mainComponent.inject(this);
            GenreDetailFragment.fragmentComponent.inject(this);
        }
    }

    @Inject
    public ArtistListFragment(){
        Timber.d("created artist list fragment ");
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}
