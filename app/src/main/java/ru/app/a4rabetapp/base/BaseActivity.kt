package ru.app.a4rabetapp.base

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import ru.app.a4rabetapp.app.FourRabetApp
import ru.app.a4rabetapp.network.ApiService

open class BaseActivity: AppCompatActivity() {

    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        apiService = (application as FourRabetApp).apiService
        super.onCreate(savedInstanceState)
    }

    fun getRemoteConfig(): FirebaseRemoteConfig {
        return (application as FourRabetApp).remoteConfig
    }
    fun isAppAvailable(appName: String): Boolean {
        val pm = packageManager
        return try {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}