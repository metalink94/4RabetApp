package ru.app.a4rabetapp.screens.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.airbnb.deeplinkdispatch.DeepLink
import ru.app.a4rabetapp.base.BaseActivity
import ru.app.a4rabetapp.screens.menu.MenuActivity

class SplashActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            openMenu()
        }, 500)
    }

    private fun openMenu() {
        if (intent.getBooleanExtra(DeepLink.IS_DEEP_LINK, false)) {
            startActivity(MenuActivity.getInstance(this, intent.getStringExtra(DeepLink.URI)))
        } else {
            startActivity(Intent(this, MenuActivity::class.java))
        }
        finish()
    }
}
