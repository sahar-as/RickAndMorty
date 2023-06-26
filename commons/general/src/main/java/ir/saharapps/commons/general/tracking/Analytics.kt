package ir.saharapps.commons.general.tracking

import android.app.Activity
import android.app.Application
import com.yandex.metrica.IReporter
import com.yandex.metrica.ReporterConfig
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import ir.saharapps.commons.general.BuildConfig
import ir.saharapps.commons.general.utils.Constants


class Analytics {

    private var iReporter: IReporter? = null

    private fun initialize(application: Application){
        YandexMetrica.activate(
            application,
            YandexMetricaConfig.newConfigBuilder(Constants.APPMETRICA_API).withLogs().build()
        )
        YandexMetrica.activateReporter(
            application,
            ReporterConfig.newConfigBuilder(Constants.APPMETRICA_API).withLogs().build()
        )
        iReporter = YandexMetrica.getReporter(application, Constants.APPMETRICA_API)
    }

    fun sendEvent(eventName: String, eventValue: String) {
        iReporter?.reportEvent(eventName, "{\"value\":\"$eventValue\"}")
    }

    fun sendEvent(eventName: String) {
        iReporter?.reportEvent(eventName)
    }

    fun pause(activity: Activity) {
        YandexMetrica.pauseSession(activity)
    }

    fun resume(activity: Activity) {
        YandexMetrica.resumeSession(activity)
    }

    companion object {
        private val analytics: Analytics = Analytics()
        fun getInstance() = analytics
        fun build(application: Application) {
            getInstance().initialize(application)
        }
    }

}

fun sendEvent(eventName: String, eventValue: String) {
    Analytics.getInstance().sendEvent(eventName, eventValue)
}

fun sendEvent(eventName: String) {
    Analytics.getInstance().sendEvent(eventName)
}

fun Activity.pauseSession() = Analytics.getInstance().pause(this)
fun Activity.resumeSession() = Analytics.getInstance().resume(this)
