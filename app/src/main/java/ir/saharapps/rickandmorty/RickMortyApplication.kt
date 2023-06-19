package ir.saharapps.rickandmorty

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ir.saharapps.rickandmorty.ui.screen.utility.InternetState

@HiltAndroidApp
class RickMortyApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        InternetState.initialize(this)
    }
}