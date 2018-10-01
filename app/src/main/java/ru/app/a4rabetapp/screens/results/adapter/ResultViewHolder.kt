package ru.app.a4rabetapp.screens.results.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import ru.app.a4rabetapp.models.PostModel
import kotlinx.android.synthetic.main.row_result.view.*

class ResultViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


    fun bind(model: PostModel) {
        itemView.commands.text = String.format("%s\n%s", model.name, model.desc)
    }
}
