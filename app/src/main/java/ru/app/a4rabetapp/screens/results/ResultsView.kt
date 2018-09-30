package ru.app.a4rabetapp.screens.results

import ru.app.a4rabetapp.mvp.IView

interface ResultsView: IView {
    fun showProgress()
    fun hideProgress()

}
