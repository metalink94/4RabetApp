package ru.app.a4rabetapp.screens.results.adapter

import android.support.v7.widget.RecyclerView

interface ResultsAdapterImpl {

    fun removeItems(items: List<*>)

    fun addItems(items: List<*>)

    fun clear()

    fun removeItem(item: Any)

    fun addItems(pos: Int, items: List<*>)

    fun addItem(pos: Int, item: Any)

    fun size(): Int

    fun getItem(pos: Int): Any

    fun getRecyclerViewAdapter() : RecyclerView.Adapter<*>
}
