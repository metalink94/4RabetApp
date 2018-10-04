package ru.app.a4rabetapp.screens.results

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.results.*
import ru.app.a4rabetapp.R
import ru.app.a4rabetapp.base.BaseActivity
import ru.app.a4rabetapp.models.TitleModel
import ru.app.a4rabetapp.screens.results.adapter.ResultsAdapterImpl
import ru.app.a4rabetapp.screens.results.adapter.ResultsBuilder
import ru.app.a4rabetapp.screens.results.adapter.ResultsBuilderImpl

class ResultsActivity : BaseActivity(), ResultsView, View.OnClickListener {

    override fun onClick(p0: View?) {

    }

    val presenter: ResultsPresenter = ResultsPresenter()
    lateinit var adapter: ResultsAdapterImpl
    val builder: ResultsBuilderImpl = ResultsBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.apiService = apiService
        presenter.setView(this)
        initRecycler()
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = true
            presenter.onRefresh()
        }
        presenter.onCreate()
    }

    private fun initRecycler() {
        recycler.layoutManager = LinearLayoutManager(this)
        builder.setRecycler(recycler)
        builder.setOnClickListener(this)
        adapter = builder.build()
        recycler.adapter = adapter.getRecyclerViewAdapter()
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

    override fun addItems(it: List<Any>) {
        adapter.addItems(it)
    }

    override fun clearAdapter() {
        adapter.clear()
    }

    override fun addTitle() {
        adapter.addItem(0, TitleModel(getString(R.string.results)))
    }

    override fun hideIndicator() {
        swipeRefresh.isRefreshing = false
    }

    override fun showError() {
        AlertDialog.Builder(this)
                .setTitle(R.string.error)
                .setMessage(R.string.error_message)
                .setNegativeButton(R.string.ok) { dialogInterface, i -> }
                .show()
    }
}
