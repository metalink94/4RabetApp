package ru.app.a4rabetapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import ru.app.a4rabetapp.R

class ProgressView
constructor(context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0):
        RelativeLayout(context, attributes, defStyleAttr) {

    init {
        View.inflate(getContext(), R.layout.progress_view, this)
    }
}