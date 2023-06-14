package ir.saharapps.rickandmorty.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.saharapps.rickandmorty.data.remote.RickAndMortyApi
import ir.saharapps.rickandmorty.utils.Constants.RICK_AND_MORTY_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRickMortyRetrofit(): RickAndMortyApi{
        return Retrofit.Builder()
            .baseUrl(RICK_AND_MORTY_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyApi::class.java)
    }

}