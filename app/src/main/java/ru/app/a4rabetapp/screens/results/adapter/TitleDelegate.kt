package ru.app.a4rabetapp.screens.results.adapter

import android.view.ViewGroup
import org.jetbrains.anko.layoutInflater
import ru.app.a4rabetapp.R
import ru.app.a4rabetapp.base.DelegateAdapter
import ru.app.a4rabetapp.models.TitleModel

class TitleDelegate: DelegateAdapter.Delegate<TitleModel, TitleViewHolder>() {

    override fun createViewHolder(parent: ViewGroup): TitleViewHolder {
        val view = parent.context.layoutInflater.inflate(R.layout.row_title, parent, false)
        return TitleViewHolder(view)
    }

    override fun bind(viewHolder: TitleViewHolder, model: TitleModel) {
        viewHolder.bind(model)
    }

}
