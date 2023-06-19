package ir.saharapps.rickandmorty.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.saharapps.rickandmorty.data.remote.RickAndMortyApi
import ir.saharapps.rickandmorty.data.utility.APIs
import ir.saharapps.rickandmorty.utils.*
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
            .baseUrl(APIs.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyApi::class.java)
    }
}