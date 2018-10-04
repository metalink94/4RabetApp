package ru.app.a4rabetapp.screens.results

import ru.app.a4rabetapp.models.ResultModel
import ru.app.a4rabetapp.mvp.IView

interface ResultsView: IView {
    fun showProgress()
    fun hideProgress()
    fun addItems(it: List<Any>)
    fun addTitle()
    fun hideIndicator()
    fun clearAdapter()
    fun showError()

}
