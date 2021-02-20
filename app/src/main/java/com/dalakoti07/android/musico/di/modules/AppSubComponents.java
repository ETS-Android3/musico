package com.dalakoti07.android.musico.di.modules;

import com.dalakoti07.android.musico.di.components.FragmentComponent;
import com.dalakoti07.android.musico.di.components.MainComponent;

import dagger.Module;

/**
    Bringing all the sub-components in the app in one umbrella
    Bundling MainComponent, if their exist other components then they are added here
 */
@Module(subcomponents = { MainComponent.class,FragmentComponent.class})
public class AppSubComponents {


}
