package ru.app.a4rabetapp.screens.results

import android.os.Bundle
import kotlinx.android.synthetic.main.results.*
import ru.app.a4rabetapp.R
import ru.app.a4rabetapp.base.BaseActivity

class ResultsActivity : BaseActivity(), ResultsView {

    val presenter: ResultsPresenter = ResultsPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        presenter.apiService = apiService
        presenter.setView(this)
        presenter.onCreate()
    }

    override fun showProgress() {
        showProgressView()
    }

    override fun hideProgress() {
        hideProgressView()
    }
}