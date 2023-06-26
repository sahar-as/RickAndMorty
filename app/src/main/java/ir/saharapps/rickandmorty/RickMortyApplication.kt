package ir.saharapps.rickandmorty

import android.app.Application
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import dagger.hilt.android.HiltAndroidApp
import ir.saharapps.commons.general.tracking.Analytics
import ir.saharapps.rickandmorty.ui.screen.utility.InternetState
import ir.saharapps.rickandmorty.utils.Constants


@HiltAndroidApp
class RickMortyApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        InternetState.initialize(this)

        Analytics.build(this)
//        val config = YandexMetricaConfig.newConfigBuilder(Constants.APPMETRICA_API)
//            .withSessionTimeout(15)
//            .build()
//        YandexMetrica.activate(applicationContext, config)
//        YandexMetrica.enableActivityAutoTracking(this)
    }
}