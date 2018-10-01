package ru.app.a4rabetapp.screens.menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.menu.*
import ru.app.a4rabetapp.R
import ru.app.a4rabetapp.base.BaseActivity
import ru.app.a4rabetapp.screens.about.AboutActivity
import ru.app.a4rabetapp.screens.results.ResultsActivity
import android.telephony.TelephonyManager
import ru.app.a4rabetapp.base.Features
import ru.app.a4rabetapp.screens.web.WebActivity


class MenuActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
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
        val appName = getString(R.string.telegram)
        if (isAppAvailable(appName)) {
            val myIntent = Intent(Intent.ACTION_SEND)
            myIntent.type = "text/plain"
            myIntent.`package` = appName
            myIntent.putExtra(Intent.EXTRA_TEXT, "")//
            startActivity(Intent.createChooser(myIntent, getString(R.string.open_with)))
        } else {
            Toast.makeText(this, "Telegram not Installed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onResultsClick() {
        if (acceptResults()) {
            startActivity(Intent(this, ResultsActivity::class.java))
        } else {
            startActivity(Intent(this, WebActivity::class.java))
        }
    }

    private fun acceptResults(): Boolean {
        val tm = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val countryCodeValue = tm.networkCountryIso
        val roaming = tm.isNetworkRoaming
        return countryCodeValue.toLowerCase() != getRemoteConfig().getString(Features.COUNTRY_ISO_CODE).toLowerCase() && !roaming
    }
}
