package ru.app.a4rabetapp.screens.menu

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.location.Address
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import kotlinx.android.synthetic.main.menu.*
import ru.app.a4rabetapp.R
import ru.app.a4rabetapp.base.BaseActivity
import ru.app.a4rabetapp.screens.about.AboutActivity
import ru.app.a4rabetapp.screens.results.ResultsActivity
import android.telephony.TelephonyManager
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import ru.app.a4rabetapp.base.Features
import ru.app.a4rabetapp.screens.web.WebActivity
import java.util.*
import android.location.Geocoder
import ru.app.a4rabetapp.base.Utils
import java.io.IOException


class MenuActivity: BaseActivity(), MenuScreenView {

    private var url: String? = Features.URL

    lateinit var presenter: MenuPresenter

    private lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
        sharedPreference = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        checkIntent()
        presenter = MenuPresenter(ipService)
        presenter.setView(this)
        presenter.onCreate()
        checkDatabase()
        results.setOnClickListener { presenter.onResultsClick() }
        chat.setOnClickListener { presenter.onChatClick() }
        about.setOnClickListener { presenter.onAboutClick() }
    }

    private fun checkIntent() {
        if (intent.hasExtra(KEY_URL)) {
            sharedPreference.edit().putString(KEY_URL, intent.getStringExtra(KEY_URL)).apply()
        }
    }

    override fun hideProgress() {
        hideProgressView()
    }

    private fun checkDatabase() {
        showProgressView()
        val database = FirebaseDatabase.getInstance()
        database.reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.value != null) {
                    url = p0.child(getString(R.string.key_first)).value as String?
                    presenter.isNeedStub = p0.child(getString(R.string.key_two)).value as Boolean
                    presenter.countryDatabaseIso = p0.child(getString(R.string.key_country)).value as String?
                }
                Log.d("DataBase", "get database ${p0.value}")
                presenter.checkIp()
//                showAlertDialog()
                Log.d("CheckCountry", "User Country ${checkCountry()}")
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.e("URL", p0.message)
                presenter.checkIp()
            }
        })
    }

    private fun showAlertDialog() {
        // only for debug
        val tm = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val countryCodeValue = tm.networkCountryIso
        val roaming = tm.isNetworkRoaming
        Log.d("Menu", " countryCodeValue: $countryCodeValue, " +
                "roaming $roaming, remote ${presenter.countryDatabaseIso?.toLowerCase()}, user ip Adress ${Utils.getIPAddress(true)}")
        AlertDialog.Builder(this)
                .setTitle("Check Country")
                .setMessage("Your phoneCountry is ${tm.simCountryIso} your networkCountry is $countryCodeValue and you in roaming $roaming, get ip: ${Utils.getIPAddress(true)}")
                .setPositiveButton("Ok") { dialog, which ->  }
                .show()
    }

    override fun onAboutClick() {
        startActivity(Intent(this, AboutActivity::class.java))
    }

    override fun onChatClick() {
        val telegram = Intent(Intent.ACTION_VIEW, Uri.parse(getRemoteConfig().getString(Features.CHAT_URL)))
        startActivity(telegram)
    }

    override fun showWeb() {
        if (sharedPreference.contains(KEY_URL)) {
            url = sharedPreference.getString(KEY_URL, url)
        }
        startActivity(WebActivity.getInstance(this, url))
        finish()
    }

    override fun showStub() {
        startActivity(Intent(this, ResultsActivity::class.java))
    }

    override fun checkCountry() {
        try {
            val tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val simCountry = tm.simCountryIso
            if (simCountry != null && simCountry.length == 2) { // SIM country code is available
                presenter.countryISO = simCountry.toLowerCase(Locale.US)
            } else if (tm.phoneType != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                val networkCountry = tm.networkCountryIso
                if (networkCountry != null && networkCountry.length == 2) { // network country code is available
                    presenter.countryISO = networkCountry.toLowerCase(Locale.US)
                }
            }
        } catch (e: Exception) {
            Log.e("Error", e.localizedMessage)
            presenter.countryISO = null
        }
    }


    companion object {

        private val KEY_URL = "deepLink"

        private val APP_PREFERENCES = "mysettings"

        fun getInstance(context: Context, url: String): Intent {
            return Intent(context, MenuActivity::class.java)
                    .putExtra(KEY_URL, url)
        }
    }
}
