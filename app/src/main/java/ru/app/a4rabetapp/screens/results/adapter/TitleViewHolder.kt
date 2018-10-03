package ru.app.a4rabetapp.screens.results.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import ru.app.a4rabetapp.models.TitleModel
import kotlinx.android.synthetic.main.row_title.view.title

class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(model: TitleModel) {
        itemView.title.text = model.string
    }

}
