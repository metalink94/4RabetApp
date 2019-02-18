package ru.app.a4rabetapp.screens.menu

import ru.app.a4rabetapp.mvp.IView

interface MenuScreenView: IView {
    fun onAboutClick()
    fun onChatClick()
    fun checkCountry()
    fun showStub()
    fun showWeb()
    fun hideProgress()

}
