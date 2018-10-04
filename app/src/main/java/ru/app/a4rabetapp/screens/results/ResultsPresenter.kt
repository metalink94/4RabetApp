package ru.app.a4rabetapp.screens.results

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.app.a4rabetapp.mvp.ViewPresenter
import ru.app.a4rabetapp.network.ApiService

class ResultsPresenter : ViewPresenter<ResultsView>() {

    lateinit var apiService: ApiService

    fun onCreate(isNeedProgress: Boolean = true) {
        unsubscribeOnDestroy(apiService.getResults()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { if (isNeedProgress) getView()?.showProgress() }
                .doOnTerminate { if (isNeedProgress) getView()?.hideProgress() }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    getView()?.clearAdapter()
                    getView()?.addTitle()
                    getView()?.addItems(it)
                    getView()?.hideIndicator()
                }, {
                    getView()?.hideIndicator()
                    getView()?.showError()
                    Log.e("Presenter", "Some error with request", it)
                }))
    }

    fun onRefresh() {
        onCreate(false)
    }
}
