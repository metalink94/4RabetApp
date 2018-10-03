package ru.app.a4rabetapp.screens.about

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.about.*
import ru.app.a4rabetapp.R
import ru.app.a4rabetapp.base.BaseActivity
import ru.app.a4rabetapp.base.Features


class AboutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setRemoteData()
        telegramUrl.setOnClickListener { onTelegramClick() }
        whatsUrl.setOnClickListener { onWhatsAppClick() }
        mailUrl.setOnClickListener { onMailClick() }
    }

    private fun setRemoteData() {
        telegramUrl.text = getRemoteConfig().getString(Features.SUPPORT)
        whatsUrl.text = getRemoteConfig().getString(Features.WHATS_APP)
        mailUrl.text = getRemoteConfig().getString(Features.MAIL)
    }

    private fun onMailClick() {
        try {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:" + mailUrl.text) // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(mailUrl.text))
            intent.putExtra(Intent.EXTRA_SUBJECT, "App feedback")
            startActivity(intent)
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(this, "There are no email client installed on your device.", Toast.LENGTH_LONG).show()
        }

    }

    private fun onWhatsAppClick() {
        try {
            val uri = Uri.parse("smsto:${whatsUrl.text}")
            val i = Intent(Intent.ACTION_SENDTO, uri)
            i.putExtra("sms_body", "")
            i.setPackage("com.whatsapp")
            startActivity(i)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show()
        }
    }

    private fun onTelegramClick() {
        val telegram = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.telegram_me) + telegramUrl.text.substring(1)))
        startActivity(telegram)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(menuItem)
    }
}
