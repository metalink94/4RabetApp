package ru.app.a4rabetapp.screens.menu

import android.support.v7.view.menu.MenuView
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.app.a4rabetapp.base.Features
import ru.app.a4rabetapp.base.Utils
import ru.app.a4rabetapp.mvp.ViewPresenter
import ru.app.a4rabetapp.network.IpService

class MenuPresenter(private val ipService: IpService): ViewPresenter<MenuScreenView>() {

    fun checkIp() {
        val ip = Utils.getIPAddress(true)
        unsubscribeOnDestroy(ipService.getIpLocation(ip, Features.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("IPCHECK", "get IpInfo: $it")
                }, { Log.e("Error", it.localizedMessage)}))
    }
}