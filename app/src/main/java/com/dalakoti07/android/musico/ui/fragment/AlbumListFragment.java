package com.dalakoti07.android.musico.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dalakoti07.android.musico.databinding.FragmentAlbumListBinding;

import javax.inject.Inject;

import timber.log.Timber;

public class AlbumListFragment extends Fragment {
    private FragmentAlbumListBinding binding;

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

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}
