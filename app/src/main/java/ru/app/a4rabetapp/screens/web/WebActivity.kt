package ru.app.a4rabetapp.screens.web

import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.main.*
import ru.app.a4rabetapp.R
import ru.app.a4rabetapp.app.FourRabetApp
import ru.app.a4rabetapp.base.BaseActivity
import ru.app.a4rabetapp.base.Features

class WebActivity: BaseActivity() {


    var currentUrl: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        initWebView()
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = true
            load()
        }
    }

    private fun getUrl(): String {
        return if (application is FourRabetApp) {
            Log.d("FourRabetApp", getRemoteConfig().getString(Features.URL))
            getRemoteConfig().getString(Features.URL)
        } else {
            getString(R.string.url)
        }
    }

    private fun initWebView() {
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                currentUrl = url
                return false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                swipeRefresh.isRefreshing = false
            }
        }
        webView.clearCache(true)
        webView.clearHistory()
        webView.setInitialScale(1)
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        webView.isScrollbarFadingEnabled = false
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false
        currentUrl = getUrl()
        load()
    }

    private fun load() {
        webView.loadUrl(currentUrl)
    }
}
