package com.dalakoti07.android.musico.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import timber.log.Timber;
import com.dalakoti07.android.musico.databinding.FragmentAlbumListBinding;

public class ArtistListFragment extends Fragment {
    //reusing the same layout file
    private FragmentAlbumListBinding binding;

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

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}
