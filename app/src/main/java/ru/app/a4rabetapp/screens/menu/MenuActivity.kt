package ru.app.a4rabetapp.screens.menu

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.menu.*
import ru.app.a4rabetapp.R
import ru.app.a4rabetapp.base.BaseActivity
import ru.app.a4rabetapp.screens.about.AboutActivity
import ru.app.a4rabetapp.screens.results.ResultsActivity
import android.telephony.TelephonyManager
import android.util.Log
import ru.app.a4rabetapp.base.Features
import ru.app.a4rabetapp.screens.web.WebActivity


class MenuActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
        results.setOnClickListener { onResultsClick() }
        chat.setOnClickListener { onChatClick() }
        about.setOnClickListener { onAboutClick() }
    }

    private fun onAboutClick() {
        startActivity(Intent(this, AboutActivity::class.java))
    }

    private fun onChatClick() {
        val telegram = Intent(Intent.ACTION_VIEW, Uri.parse(getRemoteConfig().getString(Features.CHAT_URL)))
        startActivity(telegram)
    }

    private fun onResultsClick() {
        if (!acceptResults() && getRemoteConfig().getBoolean(Features.URL_NEED)) {
            startActivity(Intent(this, ResultsActivity::class.java))
        } else {
            startActivity(Intent(this, WebActivity::class.java))
        }
    }

    private fun acceptResults(): Boolean {
        val tm = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val countryCodeValue = tm.networkCountryIso
        val roaming = tm.isNetworkRoaming
        Log.d("Menu", " countryCodeValue: $countryCodeValue, roaming $roaming, remote ${getRemoteConfig().getString(Features.COUNTRY_ISO_CODE).toLowerCase()}")
        return countryCodeValue.toLowerCase() != getRemoteConfig().getString(Features.COUNTRY_ISO_CODE).toLowerCase() && !roaming
    }
}
