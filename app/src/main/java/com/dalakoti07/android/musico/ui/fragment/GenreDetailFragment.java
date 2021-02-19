package com.dalakoti07.android.musico.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalakoti07.android.musico.R;
import com.dalakoti07.android.musico.databinding.FragmentGenreDetailBinding;
import com.dalakoti07.android.musico.ui.activity.MainActivity;
import com.dalakoti07.android.musico.ui.adapters.GenreTabAdapter;

import javax.inject.Inject;

public class GenreDetailFragment extends Fragment {
    private FragmentGenreDetailBinding binding;

    public GenreDetailFragment() {
        // Required empty public constructor
    }

    @Inject
    AlbumListFragment albumsListFragment;

    @Inject
    ArtistListFragment artistListFragment;

    @Inject
    TracksListFragment tracksListFragment;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(getActivity()!=null){
            ((MainActivity)getActivity()).mainComponent.inject(this);
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
        GenreTabAdapter adapter=new GenreTabAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(albumsListFragment,"Albums");
        adapter.addFragment(artistListFragment,"Artists");
        adapter.addFragment(tracksListFragment,"Tracks");

        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.tabLayout.getTabAt(1).select();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}