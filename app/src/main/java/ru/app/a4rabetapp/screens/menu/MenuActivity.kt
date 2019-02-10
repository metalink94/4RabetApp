package ru.app.a4rabetapp.screens.menu

import android.content.Context
import android.content.Intent
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


class MenuActivity: BaseActivity() {

    private var url: String? = Features.URL
    private var isNeedStub: Boolean = true
    private var countryIso: String? = Features.COUNTRY_ISO_CODE

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
        checkDatabase()
        results.setOnClickListener { onResultsClick() }
        chat.setOnClickListener { onChatClick() }
        about.setOnClickListener { onAboutClick() }
    }

    private fun checkDatabase() {
        showProgressView()
        val database = FirebaseDatabase.getInstance()
        database.reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.value != null) {
                    url = p0.child(getString(R.string.key_first)).value as String?
                    isNeedStub = p0.child(getString(R.string.key_two)).value as Boolean
                    countryIso = p0.child(getString(R.string.key_country)).value as String?
                }
                Log.d("DataBase", "get database ${p0.value}")
                hideProgressView()
                showAlertDialog()
                Log.d("CheckCountry", "User Country ${checkCountry()}")
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.e("URL", p0.message)
                hideProgressView()
            }
        })
    }

    private fun showAlertDialog() {
        // only for debug
        val tm = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val countryCodeValue = tm.networkCountryIso
        val roaming = tm.isNetworkRoaming
        Log.d("Menu", " countryCodeValue: $countryCodeValue, " +
                "roaming $roaming, remote ${countryIso?.toLowerCase()}, user ip Adress ${Utils.getIPAddress(true)}")
        val presenter = MenuPresenter(ipService)
        presenter.checkIp()
        AlertDialog.Builder(this)
                .setTitle("Check Country")
                .setMessage("Your phoneCountry is ${tm.simCountryIso} your networkCountry is $countryCodeValue and you in roaming $roaming, get ip: ${Utils.getIPAddress(true)}")
                .setPositiveButton("Ok") { dialog, which ->  }
                .show()
    }

    private fun onAboutClick() {
        startActivity(Intent(this, AboutActivity::class.java))
    }

    private fun onChatClick() {
        val telegram = Intent(Intent.ACTION_VIEW, Uri.parse(getRemoteConfig().getString(Features.CHAT_URL)))
        startActivity(telegram)
    }

    private fun onResultsClick() {
        if (!isNeedStub && checkCountry()?.toLowerCase() == countryIso?.toLowerCase()) {
            startActivity(WebActivity.getInstance(this, url))
        } else {
            startActivity(Intent(this, ResultsActivity::class.java))
        }
    }

    private fun checkCountry(): String? {
        try {
            val tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val simCountry = tm.simCountryIso
            if (simCountry != null && simCountry.length == 2) { // SIM country code is available
                return simCountry.toLowerCase(Locale.US)
            } else if (tm.phoneType != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                val networkCountry = tm.networkCountryIso
                if (networkCountry != null && networkCountry.length == 2) { // network country code is available
                    return networkCountry.toLowerCase(Locale.US)
                }
            }
        } catch (e: Exception) {
            Log.e("Error", e.localizedMessage)
        }
        return null
    }
}
