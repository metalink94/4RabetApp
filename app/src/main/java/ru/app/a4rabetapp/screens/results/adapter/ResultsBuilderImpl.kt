package ru.app.a4rabetapp.screens.results.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

interface ResultsBuilderImpl {

    fun setRecycler(recyclerView: RecyclerView)
    fun setOnClickListener(onClickListener: View.OnClickListener)
    fun build(): ResultsAdapterImpl
}
