package com.dalakoti07.android.musico.viewmodels;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.dalakoti07.android.musico.data.models.SongGenre;
import com.dalakoti07.android.musico.data.repository.FakeMainRepository;
import com.dalakoti07.android.musico.utils.LiveDataTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.ArrayList;

import static com.google.common.truth.Truth.assertThat;


public class HomeScreenViewModelTest {
    @Rule
    public
    TestRule rule = new InstantTaskExecutorRule();

    private HomeScreenViewModel homeScreenViewModel;
    private FakeMainRepository fakeMainRepository;

    @Before
    public void setUp(){
        fakeMainRepository=new FakeMainRepository();
        homeScreenViewModel= new HomeScreenViewModel(fakeMainRepository);
    }

    @Test
    public void fetchTheGenresFromServer_validateData() throws Exception{
        ArrayList<SongGenre> songGenres= LiveDataTestUtil.getOrAwaitValue(homeScreenViewModel.getAllSongGenres()) ;
        assertThat(songGenres.size()).isEqualTo(1);
        assertThat(songGenres.get(0).getName()).isEqualTo("pop");
    }

    @Test
    public void errorInFetchingData_validateErrorMsg() throws Exception{
        fakeMainRepository.setThrowGenreError(true);
        homeScreenViewModel.getAllSongGenres();
        String errorMsg= LiveDataTestUtil.getOrAwaitValue(homeScreenViewModel.getErrorMessage());
        assertThat(errorMsg).isEqualTo("Unknown Error occurred");
    }

}