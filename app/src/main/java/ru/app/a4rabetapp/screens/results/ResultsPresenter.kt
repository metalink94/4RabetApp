package ru.app.a4rabetapp.screens.results

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.app.a4rabetapp.mvp.ViewPresenter
import ru.app.a4rabetapp.network.ApiService

class ResultsPresenter : ViewPresenter<ResultsView>() {

    lateinit var apiService: ApiService

    fun onCreate() {
        unsubscribeOnDestroy(apiService.getResults("bash", 50)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { getView()?.showProgress() }
                .doOnTerminate { getView()?.hideProgress() }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.d("Presenter", "get size of list ${it.size}")
                }, { Log.e("Presenter", "Some error with request", it) }))
    }
}