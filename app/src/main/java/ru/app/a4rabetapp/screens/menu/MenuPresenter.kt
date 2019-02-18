package ru.app.a4rabetapp.screens.menu

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.app.a4rabetapp.base.Features
import ru.app.a4rabetapp.base.Utils
import ru.app.a4rabetapp.mvp.ViewPresenter
import ru.app.a4rabetapp.network.IpService


class MenuPresenter(private val ipService: IpService) : ViewPresenter<MenuScreenView>() {

    var countryISO: String? = null
    var isNeedStub: Boolean = true
    var countryDatabaseIso: String? = Features.COUNTRY_ISO_CODE
    var inCorrectCountry = false
    private var list: MutableList<String> = mutableListOf()

    fun checkIp() {
        stringToList()
        inCorrectCountry = isCorrectCountry()
        if (inCorrectCountry) {
            showNeededScreen()
            return
        }
        val ip = Utils.getIPAddress(true)
        unsubscribeOnDestroy(ipService.getIpLocation(ip, Features.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if (!it.countryCode.isNullOrEmpty()) {
                        countryISO = it.countryCode
                    }
                    showNeededScreen()
                }, {
                    Log.e("Error", it.localizedMessage)
                    showNeededScreen()
                }))
    }

    private fun stringToList() {
        val countries = countryDatabaseIso ?: ""
        list = countries.split(",").map { it.trim() }.toMutableList()
    }

    private fun showNeededScreen() {
        if (!isNeedStub && inCorrectCountry) {
            getView()?.showWeb()
        }
        getView()?.hideProgress()
    }

    private fun isCorrectCountry(): Boolean {
        var isCorrect = false
        val countryIso = countryISO ?: ""
        for (country in list) {
            if (country.toLowerCase() == countryIso.toLowerCase()) {
                isCorrect = true
                break
            }
        }
        return isCorrect
    }

    fun onResultsClick() {
        getView()?.showStub()
    }

    fun onChatClick() {
        getView()?.onChatClick()
    }

    fun onAboutClick() {
        getView()?.onAboutClick()
    }

    fun onCreate() {
        getView()?.checkCountry()
    }
}
