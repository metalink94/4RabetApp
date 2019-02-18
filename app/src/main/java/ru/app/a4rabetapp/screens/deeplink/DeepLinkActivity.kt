package ru.app.a4rabetapp.screens.deeplink

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.airbnb.deeplinkdispatch.DeepLink
import com.airbnb.deeplinkdispatch.DeepLinkEntry
import com.airbnb.deeplinkdispatch.DeepLinkUri
import ru.app.a4rabetapp.screens.splash.SplashActivity

class DeepLinkActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.data != null) {
            parseUrl(intent.data.toString())
            openSplash()
        }
    }

    private fun openSplash() {
        startActivity(
                Intent(this, SplashActivity::class.java)
                        .putExtras(intent.extras))
        finish()
    }

    private fun parseUrl(stringUrl: String?) {
        if (stringUrl.isNullOrEmpty()) return
        val deepLinkUri = DeepLinkUri.parse(stringUrl)
        val parameterMap = DeepLinkEntry(stringUrl, DeepLinkEntry.Type.METHOD, null, null)
                .getParameters(stringUrl)
        for (queryParameter in deepLinkUri.queryParameterNames()) {
            for (queryParameterValue in deepLinkUri.queryParameterValues(queryParameter)) {
                if (parameterMap.containsKey(queryParameter)) {
                    Log.w("DEEPLINK","Duplicate parameter name in path and query param: $queryParameter")
                }
                parameterMap[queryParameter] = queryParameterValue
            }
        }
        parameterMap[DeepLink.URI] = stringUrl
        val parameters = Bundle()
        for (parameterEntry in parameterMap.entries) {
            parameters.putString(parameterEntry.key, parameterEntry.value)
        }
        val intent = Intent()
        intent.putExtras(parameters)
        intent.putExtra(DeepLink.IS_DEEP_LINK, true)
        intent.putExtra(DeepLink.REFERRER_URI, stringUrl)
        this.intent.putExtras(intent)
    }
}
