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

import com.dalakoti07.android.musico.data.models.AlbumModel;
import com.dalakoti07.android.musico.databinding.FragmentAlbumListBinding;
import com.dalakoti07.android.musico.di.qualifier.ActivityContext;
import com.dalakoti07.android.musico.ui.activity.MainActivity;
import com.dalakoti07.android.musico.ui.adapters.CommonListAdapter;
import com.dalakoti07.android.musico.viewmodels.SharedListViewModel;
import com.dalakoti07.android.musico.viewmodels.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class AlbumListFragment extends Fragment {
    private FragmentAlbumListBinding binding;
    private CommonListAdapter adapter;


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
//            ((MainActivity)getActivity()).mainComponent.inject(this);
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
        adapter=new CommonListAdapter();
        binding.rvListItems.setAdapter(adapter);
        viewModel.getAlbumsList(GenreDetailFragment.currentGenre).observe(getViewLifecycleOwner(), new Observer<List<AlbumModel>>() {
            @Override
            public void onChanged(List<AlbumModel> albumModels) {
                adapter.addData((ArrayList<AlbumModel>) albumModels);
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}
