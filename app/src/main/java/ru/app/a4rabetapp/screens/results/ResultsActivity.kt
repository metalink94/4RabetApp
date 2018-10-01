package ru.app.a4rabetapp.screens.results

import android.os.Bundle
import android.view.MenuItem
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.apiService = apiService
        presenter.setView(this)
        presenter.onCreate()
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun showProgress() {
        showProgressView()
    }

    override fun hideProgress() {
        hideProgressView()
    }
}
