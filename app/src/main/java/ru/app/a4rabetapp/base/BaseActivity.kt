package ru.app.a4rabetapp.base

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import ru.app.a4rabetapp.R
import ru.app.a4rabetapp.app.FourRabetApp
import ru.app.a4rabetapp.network.ApiService
import ru.app.a4rabetapp.views.ProgressView

open class BaseActivity : AppCompatActivity() {

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

    fun showProgressView() {
        val progressView = findViewById<ProgressView>(R.id.progressView)
        if (progressView != null) {
            progressView.visibility = View.VISIBLE
        }
    }

    fun hideProgressView() {
        val progressView = findViewById<ProgressView>(R.id.progressView)
        if (progressView != null) {
            progressView.visibility = View.GONE
        }
    }
}