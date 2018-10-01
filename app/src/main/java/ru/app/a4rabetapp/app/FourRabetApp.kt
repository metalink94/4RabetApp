package ru.app.a4rabetapp.app

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import retrofit2.Retrofit
import ru.app.a4rabetapp.base.Features
import ru.app.a4rabetapp.network.ApiService
import ru.app.a4rabetapp.network.RetrofitBuilder

class FourRabetApp: Application() {

    lateinit var remoteConfig: FirebaseRemoteConfig
    private val retrofit: Retrofit = RetrofitBuilder.build()
    lateinit var apiService: ApiService

    override fun onCreate() {
        FirebaseApp.initializeApp(this)
        remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig.setDefaults(getDefaults())
        remoteConfig.activateFetched()
        fetch()
        super.onCreate()
        apiService = retrofit.create(ApiService::class.java)
    }

    private fun fetch() {
        remoteConfig.fetch(60)
                .addOnSuccessListener {
                    Log.d("FourRabetApp App", "onSuccessLoaded RemoteConfig")
                    Log.d("FourRabetApp App", remoteConfig.getString("url"))
                }
                .addOnCompleteListener {
                    Log.d("FourRabetApp App", "onCompleteLoaded RemoteConfig")
                }
                .addOnFailureListener {
                    Log.e("FourRabetApp App", "onOnFailure RemoteConfig", it)
                }
    }

    private fun getDefaults(): Map<String, Any> {
        return mapOf(
                Features.URL to "http://4rabet.com",
                Features.COUNTRY_ISO_CODE to "IO"
        )
    }
}
