package ru.app.a4rabetapp.screens.about

import android.os.Bundle
import kotlinx.android.synthetic.main.about.*
import ru.app.a4rabetapp.R
import ru.app.a4rabetapp.base.BaseActivity

class AboutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
    }
}